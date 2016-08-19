package it.unibo.unori.controller.state;

import java.util.Optional;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.BattleImpl;
import it.unibo.unori.model.battle.MagicAttack;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.NotDefendableException;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
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
    private final FightInterface fightModel;
    private Optional<DialogueInterface> currentDialogue;
    private boolean heroTurn;

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
        this.heroTurn = true;
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

    /*
     * TODO check This method return the battle model of the current state. Useful for actions and action listeners.
     * 
     * @return the battle model TODO check
     */
    public FightInterface getModel() {
        return this.fightModel;
    }

    /**
     * With this method, the current hero can do a basic attack to the current foe.
     */
    public void attack() {
        try {
            this.currentDialogue = Optional.of(this.fightModel.attack(true));
            this.endHeroTurn();
        } catch (NoWeaponException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            this.currentDialogue = Optional.of(new Dialogue(e.getMessage()));
            this.scrollDialog();
        }
    }

    public void magicAttack(MagicAttackInterface magic, Foe enemy) {
        try {
            this.currentDialogue = Optional.of(this.fightModel.magic((MagicAttack) magic, enemy, true)); // TODO remove cast
            this.endHeroTurn();
        } catch (NotEnoughMPExcpetion | MagicNotFoundException e) {
            this.currentDialogue = Optional.of(new Dialogue(e.getMessage()));
            this.scrollDialog();
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
            this.fightModel.getBag().usePotion(targetHero, itemUsed);
            this.fightModel.getBag().removeItem(itemUsed);
            this.endHeroTurn();
        } catch (ItemNotFoundException | HeroDeadException | HeroNotDeadException e) {
            this.currentDialogue = Optional.of(new Dialogue(e.getMessage()));
            this.scrollDialog();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This method scrolls the dialog if open and closes it if ended.
     */
    public void scrollDialog() {
        if (this.currentDialogue.isPresent()) {
            if (this.currentDialogue.get().isOver()) {
                this.currentDialogue = Optional.empty();
            } else {
                try {
                    ((BattleLayer) this.getLayer()).showDialogue(this.currentDialogue.get().showNext());
                } catch (IndexOutOfBoundsException e) {
                    this.currentDialogue = Optional.empty();
                }
            }

            if (!this.currentDialogue.isPresent()) {
                ((BattleLayer) this.getLayer()).hideDialogue();
                if (this.heroTurn) {
                    this.startHeroTurn();
                } else {
                    this.startFoeTurn();
                }
            }

        }
    }

    private void startFoeTurn() {
        BattleLayer currentLayer = (BattleLayer) this.getLayer();
        currentLayer.updateView();
    }

    private void startHeroTurn() {
        BattleLayer currentLayer = (BattleLayer) this.getLayer();
        currentLayer.updateView();
        currentLayer.newTurn();
    }

    private void endHeroTurn() {
        this.scrollDialog();
        this.heroTurn = false;
    }
}
