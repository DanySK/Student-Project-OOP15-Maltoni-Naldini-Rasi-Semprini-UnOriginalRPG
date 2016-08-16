package it.unibo.unori.view.tests;

import it.unibo.unori.view.View;
import it.unibo.unori.view.layers.MapLayer;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;

import java.util.Map;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.SwingConstants;

import java.awt.Point;
import java.awt.event.ActionEvent;

/**
 *
 * This class is used to test the map.
 *
 */
public class MapLayerTest {
    private MapLayer mapLayer;
    public final View view = new View();

    /**
     * Tests the map layer.
     */
    public MapLayerTest() {
        final Map<Integer, Action> movement = new HashMap<Integer, Action>();

        movement.put(SwingConstants.EAST, new MoveAction(SwingConstants.EAST));
        movement.put(SwingConstants.WEST, new MoveAction(SwingConstants.WEST));
        movement.put(SwingConstants.NORTH, new MoveAction(SwingConstants.NORTH));
        movement.put(SwingConstants.SOUTH, new MoveAction(SwingConstants.SOUTH));

        final Action menu = new MenuAction();
        final Action interact = new InteractAction();

        final Point position = new Point(2, 2);
        final String spriteSheetPath = "res/sprites/cook.png";
        final String[][] map = createMap("res/sprites/map/grass.png", 12, 6);

        try {
            mapLayer = new MapLayer(movement, interact, menu, map, position, spriteSheetPath);

            view.push(mapLayer);
            view.resizeTo(mapLayer);
        } catch (final SpriteNotFoundException e) {
            System.out.println("Sprite not found");
        }
    }

    public void run() {
        view.run();
    }

    public static String[][] createMap(final String path,
                                 final int width, final int height) {
        final String[][] map = new String[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = path;
            }
        }

        return map;
    }

    private class MoveAction extends AbstractAction {
        private final int direction;

        MoveAction(final int direction) {
            super();
            this.direction = direction;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            mapLayer.move(direction);
        }
    }

    private class InteractAction extends AbstractAction {
        @Override
        public void actionPerformed(final ActionEvent e) {
            view.close();
        }
    }

    private class MenuAction extends AbstractAction {
        private boolean b;

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (!b) {
                b = true;
                mapLayer.showDialogue("Dialogue test");
            } else {
                b = false;
                mapLayer.hideDialogue();
            }
        }
    }

    public static void main(final String... args) {
        final MapLayerTest mapLayerTest = new MapLayerTest();
        mapLayerTest.run();
    }
}
