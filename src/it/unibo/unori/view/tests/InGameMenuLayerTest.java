package it.unibo.unori.view.tests;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;

import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.exceptions.MaxHeroException;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.view.View;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.layers.InGameMenuLayer;
import it.unibo.unori.view.layers.Layer;
import it.unibo.unori.view.layers.MapLayer;

/**
 *
 * This class is used to test the in-game menu.
 *
 */
public class InGameMenuLayerTest {
    private MapLayer mapLayer;
    private final View view = new View();

    /**
     * Tests the in-game menu.
     */
    public InGameMenuLayerTest() {
        final Map<Integer, Action> movement = new HashMap<Integer, Action>();

        final Action interact = null;
        final Action menu = new MenuAction();

        final Point position = new Point(2, 2);
        final String spriteSheetPath = "res/sprites/clown.png";
        final String[][] map = MapLayerTest.createMap("res/sprites/map/rocks.png", 26, 20);

        try {
            mapLayer = new MapLayer(movement, interact, menu, map, position, spriteSheetPath);

            view.push(mapLayer);
            view.resizeTo(mapLayer);
        } catch (final SpriteNotFoundException e) {
            System.out.println("Sprite not found");
        }
    }

    private class MenuAction extends AbstractAction {
        @Override
        public void actionPerformed(final ActionEvent e) {
            mapLayer.disable();

            try {
                createParty();
                final Layer inGameMenuLayer = new InGameMenuLayer(SingletonParty.getParty().getHeroTeam(),
                                                                  SingletonParty.getParty().getPartyBag());
                 view.push(inGameMenuLayer);

                 mapLayer.disable();
            } catch (IllegalArgumentException | MaxHeroException e1) {
                System.out.println("Party creation error");
            }
        }
    }

    /**
     * Runs this test.
     */
    public void run() {
        view.run();
    }

    private void createParty() throws IllegalArgumentException, MaxHeroException {
        SingletonParty.getParty().getHeroTeam().addHero(new HeroImpl("Cook", Jobs.COOK));
        SingletonParty.getParty().getHeroTeam().addHero(new HeroImpl("Clown", Jobs.CLOWN));
    }

    public static void main(final String... args) {
        final InGameMenuLayerTest inGameMenuTest = new InGameMenuLayerTest();
        inGameMenuTest.run();
    }
}