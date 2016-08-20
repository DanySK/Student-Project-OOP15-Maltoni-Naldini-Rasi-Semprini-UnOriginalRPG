package it.unibo.unori.controller.state;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.action.InteractAction;
import it.unibo.unori.controller.action.MoveAction;
import it.unibo.unori.controller.action.OpenMenuAction;
import it.unibo.unori.controller.json.FoeSetup;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.factory.FoesFindable;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.model.maps.exceptions.BlockedPathException;
import it.unibo.unori.model.menu.DialogueInterface;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.layers.MapLayer;

/**
 * This GameState models the state of exploring a map (world, town or dungeon room).
 */
public class MapState extends AbstractGameState {
    private final Party party;
    private String[][] previousSpritesMap;
    private final Random random;

    /**
     * Default constructor.
     * 
     * @param map
     *            the map to start from
     * @throws SpriteNotFoundException
     *             if the sprite at the path provided does not exist
     */
    public MapState(final GameMap map) throws SpriteNotFoundException {
        super(new MapLayer(MoveAction.getSupportedMovementsMap(), new InteractAction(), new OpenMenuAction(),
                        map.getFrames(),
                        new Point(map.getInitialCellPosition().getPosX(), map.getInitialCellPosition().getPosY()),
                        SingletonParty.getParty().getCurrentFrame()));
        party = SingletonParty.getParty();
        party.setCurrentMap(map);
        previousSpritesMap = this.party.getCurrentGameMap().getFrames();
        this.random = new Random();
    }

    /**
     * Moves the party in the specified direction. Returns if it is possible.
     * 
     * @param direction
     *            the direction to move to
     * @return true if the movement was possible and was done
     */
    public boolean moveParty(final Party.CardinalPoints direction) {
        boolean canMove = true;
        final String[][] currentSpritesMap = this.party.getCurrentGameMap().getFrames();
        try {
            this.party.moveParty(direction);
        } catch (BlockedPathException e) {
            canMove = false;
        }
        if (canMove) {
            this.previousSpritesMap = currentSpritesMap;
        }
        return canMove;
    }

    /**
     * Opens a dialogue from the model.
     * 
     * @return the dialogue from the model
     */
    public DialogueInterface interact() {
        final Map<Armor, Integer> armors = this.party.getPartyBag().getArmors();
        final Map<Weapon, Integer> weapons = this.party.getPartyBag().getWeapons();
        // final Map<Potion, Integer> potions = this.party.getPartyBag().getPotions();
        // final Map<Item, Integer> miscellaneous = this.party.getPartyBag().getMiscellaneous();
        final DialogueInterface dialogue = this.party.interact();
        if (!armors.equals(this.party.getPartyBag().getArmors())) {
            SingletonStateMachine.getController().getStatistics().increaseArmorsAcquired(1);
        }
        if (!weapons.equals(this.party.getPartyBag().getWeapons())) {
            SingletonStateMachine.getController().getStatistics().increaseWeaponsAcquired(1);
        }
        //if (!potions.equals(this.party.getPartyBag().getPotions())) {
        //    SingletonStateMachine.getController().getStatistics().increaseArmorsAcquired(1);
        //}
        //if (!miscellaneous.equals(this.party.getPartyBag().getMiscellaneous())) {
        //    SingletonStateMachine.getController().getStatistics().increaseArmorsAcquired(1);
        //}

        return dialogue;
    }

    /**
     * Returns the current map.
     * 
     * @return the current map
     */
    public GameMap getMap() {
        return this.party.getCurrentGameMap();
    }

    /**
     * Returns the current position on the current map.
     * 
     * @return the current position
     */
    public Position getCurrentPosition() {
        return this.party.getCurrentPosition();
    }

    /**
     * Checks if the map in the model changed.
     * 
     * @return true if the the map changed after a movement
     */
    public boolean checkMapChanges() {
        return !Arrays.deepEquals(this.previousSpritesMap, party.getCurrentGameMap().getFrames());
    }

    /**
     * This method controls the random encounters when moving on a map that has that feature enabled.
     */
    public void randomEncounters() {
        if (this.getMap().isBattleState() && this.random.ints().limit(2).sum() % 2 != 0) {
            // If the number is odd (33%) the battle starts
            final int numberOfMonsters = this.random.nextInt(BattleState.MAX_NUMBER_OF_FOES + 1);
            if (numberOfMonsters != 0) {
                final List<FoesFindable> foesTypes = new ArrayList<>();
                /*
                 * Random generate the enemies' types. The "fallen hero" boss (EROE_CADUTO) can't appear in dungeon
                 * randomly (this explains the "-1")
                 */
                IntStream.range(0, FoesFindable.values().length - 1).limit(numberOfMonsters)
                                .forEach(i -> foesTypes.add(FoesFindable.values()[i]));
                final List<Foe> foes = new ArrayList<>();

                /*
                 * Random generate the IA of the monsters. The more monsters are generated, the less intelligent should
                 * be; the higher heroes' level is, the more intelligent monsters are.
                 */
                IntStream.range(0, numberOfMonsters).forEach(i -> {
                    final int ia = this.random.nextInt(this.party.getHeroTeam().getAllHeroes().stream()
                                    .mapToInt(h -> h.getLevel()).max().getAsInt())
                                    - this.random.nextInt(numberOfMonsters);
                    foes.add(new FoeImpl(ia <= FoeImpl.MAXIA ? (ia > 0 ? ia : 1)  : 10, foesTypes.get(i).toString() + " " + Integer.valueOf(i + 1),
                                    FoeSetup.getSpritePath(foesTypes.get(i), ia), foesTypes.get(i)));
                });

                SingletonStateMachine.getController().startBattle(foes);
            }
        }
    }
}
