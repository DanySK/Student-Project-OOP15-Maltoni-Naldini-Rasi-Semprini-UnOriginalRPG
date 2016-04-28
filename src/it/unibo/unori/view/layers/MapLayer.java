package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapImpl;

import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.model.maps.exceptions.BlockedPathException;

import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellState;

import java.util.List;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * 
 * Game map.
 *
 */
public class MapLayer extends Layer {
    private final GameMap map;
    private final Party party = SingletonParty.getParty();

    private final Dimension size = new Dimension();
    private final Dimension cellSize = new Dimension(5, 5);

    /**
     * Creates the map layer.
     */
    public MapLayer() {
        super();

        map = party.getCurrentGameMap();
        size.width = map.getMapWidth() * cellSize.width;
        size.height = map.getMapLength() * cellSize.height;

        setPreferredSize(size);
        setBounds(0, 0, size.width, size.height);

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("UP", new MoveAction(Party.CardinalPoints.NORTH));
        actionMap.put("LEFT", new MoveAction(Party.CardinalPoints.WEST));
        actionMap.put("RIGHT", new MoveAction(Party.CardinalPoints.EST));
        actionMap.put("DOWN", new MoveAction(Party.CardinalPoints.SOUTH));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < map.getMapLength(); y++) {
            final List<Cell> row = map.getRow(y);

            for (int x = 0; x < row.size(); x++) {
                if (row.get(x).getState() == CellState.FREE) {
                    g.setColor(Color.GREEN);
                }
                if (row.get(x).getState() == CellState.BLOCKED) {
                    g.setColor(Color.RED);
                }

                g.fillRect(x * cellSize.width,
                           y * cellSize.height,
                           cellSize.width, cellSize.height);
            }
        }

        g.setColor(Color.BLUE);

        g.fillRect(party.getCurrentPosition().getPosX() * cellSize.width,
                   party.getCurrentPosition().getPosY() * cellSize.height,
                   cellSize.width, cellSize.height);
    }

    /**
     * 
     * Move the party around.
     *
     */
    protected class MoveAction extends AbstractAction {
        private final Party.CardinalPoints direction;

        /**
         * Move the party according to the cardinal direction.
         * @param direction direction the party will move to
         */
        public MoveAction(final Party.CardinalPoints direction) {
            super();
            this.direction = direction;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                party.moveParty(direction);
            } catch (BlockedPathException e1) {
                // TODO
            }
            MapLayer.this.repaint();
        }
    }

    /**
     * Tests this class.
     * @param args arguments
     */
    public static void main(final String... args) {
        final GameMap map = new GameMapImpl();
        SingletonParty.getParty().setCurrentMap(map);

        final View view = new View();
        final MapLayer mapLayer = new MapLayer();

        view.push(mapLayer);
        view.resizeTo(mapLayer);

        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                view.setVisible(true);
            }
        });

        view.center();
    }
}