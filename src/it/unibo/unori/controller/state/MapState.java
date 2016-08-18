package it.unibo.unori.controller.state;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.action.InteractAction;
import it.unibo.unori.controller.action.MoveAction;
import it.unibo.unori.controller.action.OpenMenuAction;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.factory.FoesFindable;
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
        final String[][] currentSpritesMap = this.party.getCurrentGameMap().getFrames();
        try {
            this.party.moveParty(direction);
        } catch (BlockedPathException e) {
            return false;
        }
        this.previousSpritesMap = currentSpritesMap;
        return true;
    }

    /**
     * Opens a dialogue from the model.
     * 
     * @return the dialogue from the model
     */
    public DialogueInterface interact() {
        return this.party.interact();
    }

    public GameMap getMap() {
        return this.party.getCurrentGameMap();
    }

    public Position getCurrentPosition() {
        return this.party.getCurrentPosition();

    }

    public boolean checkMapChanges() {
        return !Arrays.deepEquals(this.previousSpritesMap, party.getCurrentGameMap().getFrames());
    }

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
                IntStream.range(0, numberOfMonsters)
                                .forEach(i -> foes.add(new FoeImpl(
                                                this.random.nextInt(this.party.getHeroTeam().getAllHeroes().stream()
                                                                .mapToInt(h -> h.getLevel()).max().getAsInt())
                                                                - this.random.nextInt(numberOfMonsters),
                                                "Nemico " + Integer.valueOf(i + 1), /* TODO */"", foesTypes.get(i))));
                SingletonStateMachine.getController().startBattle(foes);
            }
        }
    }
}
