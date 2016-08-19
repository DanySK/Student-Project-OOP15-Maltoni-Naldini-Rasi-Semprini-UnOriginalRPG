package it.unibo.unori.controller.state;

import java.util.Optional;
import java.util.Random;

import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.BattleImpl;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.battle.exceptions.NotDefendableException;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.exceptions.HeroDeadException;
import it.unibo.unori.model.items.exceptions.HeroNotDeadException;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.menu.Dialogue;
import it.unibo.unori.model.menu.DialogueInterface;
import it.unibo.unori.model.menu.FightInterface;
import it.unibo.unori.model.menu.FightMenu;
import it.unibo.unori.view.layers.BattleLayer;

/**
 * This GameState models the state of battle with some encountered monsters.
 */
public class BattleState extends AbstractGameState {
    /**
     * The maximum number of enemies that could take part of a battle.
     */
    public static final int MAX_NUMBER_OF_FOES = 4;
    private static final double LOW_LIFE_PERCENTAGE = 15;
    private final FightInterface fightModel;
    private Optional<DialogueInterface> currentDialogue;
    private boolean heroTurn;
    private int currentTurn; // Automatically initialized by Java to 0

    /**
     * Default constructor. It instantiates a new battle with given foes.
     * 
     * @param party
     *            the team of heroes in the party
     * @param foes
     *            the team of enemies that the heroes must defeat
     * @param bag
     *            the bag containing the items of the party
     */
    public BattleState(final HeroTeam party, final FoeSquad foes, final Bag bag) {
        super(new BattleLayer(party, foes, bag));
        final Battle battle = new BattleImpl(party, foes, bag);
        this.fightModel = new FightMenu(battle);
        this.heroTurn = BattleLogics.whosFirst(battle.getHeroOnTurn().getSpeed(), battle.getFoeOnTurn().getSpeed());
        this.currentDialogue = Optional.of(battle.getPresentation());
        this.scrollMessage();
        this.currentTurn++;
        if (this.heroTurn) {
            this.newTurn();
        } else {
            this.endTurn();
        }
    }

    /**
     * This constructor takes everything from a party instance instead of from separates objects.
     * 
     * @param party
     *            the party containing the team of heroes and the bag
     * @param foes
     *            the team of enemies that the heroes must defeat
     */
    public BattleState(final Party party, final FoeSquad foes) {
        this(party.getHeroTeam(), foes, party.getPartyBag());
    }

    /**
     * With this method, the current hero can do a basic attack to the current foe.
     */
    public void attack() {
        try {
            this.currentDialogue = Optional.of(this.fightModel.attack(true));
            this.endHeroTurn();
        } catch (NoWeaponException e) {
            // Also fists are a weapon, so no weapon means something wrong happened
            SingletonStateMachine.getController().showError(e.getMessage());
        }
    }

    /**
     * With this method, the current hero can do a special attack to the current foe. If it's not possible, this will be
     * shown to the player.
     */
    public void specialAttack() {
        try {
            this.currentDialogue = Optional.of(this.fightModel.specialAtk());
            this.endHeroTurn();
        } catch (BarNotFullException e) {
            this.showMessage(e.getMessage());
        }
    }

    /**
     * With this method, the current hero can throw a magic attack to a specified enemy.
     * 
     * @param magic
     *            the magic attack to throw
     * @param enemy
     *            the target of the attack
     */
    public void magicAttack(final MagicAttackInterface magic, final Foe enemy) {
        try {
            this.currentDialogue = Optional.of(this.fightModel.magic(magic, enemy, true));
            this.endHeroTurn();
        } catch (NotEnoughMPExcpetion | MagicNotFoundException e) {
            this.showMessage(e.getMessage());
        }
    }

    /**
     * With this method, the current hero can use a potion on a specified hero.
     * 
     * @param targetHero
     *            the hero to use the potion on
     * @param itemUsed
     *            the potion to use
     */
    public void usePotion(final Hero targetHero, final Potion itemUsed) {
        try {
            this.fightModel.getBattle().getItemBag().usePotion(targetHero, itemUsed);
            this.fightModel.getBattle().getItemBag().removeItem(itemUsed);
            this.currentDialogue = Optional
                            .of(new Dialogue("Used " + itemUsed.getName() + " on " + targetHero.getName()));
            this.endHeroTurn();
        } catch (ItemNotFoundException | HeroDeadException | HeroNotDeadException e) {
            this.showMessage(e.getMessage());
        }
    }

    /**
     * With this method, the current hero can defend another specified hero from an attack.
     * 
     * @param heroToDefend
     *            the hero to defend
     */
    public void defend(final Hero heroToDefend) {
        try {
            this.currentDialogue = Optional.of(this.fightModel.defend(heroToDefend));
            this.endHeroTurn();
        } catch (NotDefendableException e) {
            this.showMessage(e.getMessage());
        }
    }

    /**
     * With this method, the current hero can try to run away from battle.
     */
    public void runAway() {
        try {
            this.currentDialogue = Optional.of(this.fightModel.getBattle().runAway());
            this.endHeroTurn();
        } catch (CantEscapeException e) {
            this.showMessage(e.getMessage());
        }
    }

    /**
     * This method scrolls the dialog if open and closes it if ended. It is set final because it is used in constructor.
     */
    public final void scrollMessage() {
        if (this.currentDialogue.isPresent()) {
            if (this.currentDialogue.get().isOver()) {
                this.currentDialogue = Optional.empty();
            } else {
                try {
                    ((BattleLayer) this.getLayer()).showDialogue(this.currentDialogue.get().showNext());
                } catch (IndexOutOfBoundsException e) {
                    this.currentDialogue = Optional.empty(); // TODO check
                }
            }

            if (!this.currentDialogue.isPresent()) {
                if (this.fightModel.getBattle().isOver()) {
                    this.currentDialogue = Optional.of(new Dialogue(this.fightModel.getBattle().getOutCome()));
                } else {
                    ((BattleLayer) this.getLayer()).hideDialogue();
                    if (this.heroTurn) {
                        this.newTurn();
                    } else {
                        this.endTurn();
                    }
                }
            }
        }
    }

    /**
     * This method actually ends the user actions until the game has done the turn. This is called after the current
     * dialog is ended.
     */
    private void endTurn() {
        final BattleLayer currentLayer = (BattleLayer) this.getLayer();
        currentLayer.updateView();
        this.doTheTurn();
    }

    private void doTheTurn() {
        this.currentTurn++;
        final Foe foe = this.fightModel.getBattle().getFoeOnTurn();
        final Hero hero = this.fightModel.getBattle().getHeroOnTurn();
        if (foe.getRemainingHP() / foe.getTotalHP() * 100 < LOW_LIFE_PERCENTAGE
                        && BattleLogics.canFoeRestore(foe, currentTurn)) {
            this.currentDialogue = Optional.of(this.fightModel.getBattle().foeUsesRestore(Statistics.TOTALHP));
        } else if (!this.isLowLife(hero.getTotalHP(), hero.getRemainingHP()) && foe.getIA() > FoeImpl.MAXIA / 2
                        && !foe.getMagics().isEmpty()) {
            try {
                this.currentDialogue = Optional.of(this.fightModel
                                .magic(foe.getMagics().get(new Random().nextInt(foe.getMagics().size())), null, false));
            } catch (NotEnoughMPExcpetion | MagicNotFoundException e) {
                this.fightModel.getBattle().foeUsesRestore(Statistics.TOTALMP);
            }
        } else {
            try {
                this.currentDialogue = Optional.of(this.fightModel.attack(false));
            } catch (NoWeaponException e) {
                SingletonStateMachine.getController().showError(e.getMessage());
            }
        }
        this.heroTurn = true;
        this.scrollMessage();
    }

    /**
     * This method starts a new turn, enabling showing buttons to the player. Used also to re-show the buttons after a
     * dialog for a not allowed action.
     */
    private void newTurn() {
        this.currentTurn++;
        final BattleLayer currentLayer = (BattleLayer) this.getLayer();
        currentLayer.updateView();
        currentLayer.newTurn();
    }

    /**
     * This method sets the selection of what to do is ended. After the dialog ends, the hero and the foe do this turn's
     * moves.
     */
    private void endHeroTurn() {
        this.heroTurn = false;
        this.scrollMessage();
    }

    /**
     * This method shows a String message as a battle dialog onto the screen.
     * 
     * @param message
     *            the text to show to the user
     */
    private void showMessage(final String message) {
        this.currentDialogue = Optional.of(new Dialogue(message));
        this.scrollMessage();
    }

    private boolean isLowLife(final int totalHP, final int currentHP) {
        return currentHP / totalHP * 100 < LOW_LIFE_PERCENTAGE;
    }
}
