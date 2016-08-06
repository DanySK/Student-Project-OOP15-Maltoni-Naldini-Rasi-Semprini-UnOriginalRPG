package it.unibo.unori.view.layers;

import it.unibo.unori.view.sprites.JobSprite;

import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.SwingConstants;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import it.unibo.unori.view.View;

/**
 *
 * The game map.
 *
 */
public class MapLayer extends JPanel {
    private final BufferedImage sprite;
    private final BufferedImage spriteSheet;

    private final Point position = new Point(0, 0); // TODO
    private final Dimension cellSize = new Dimension(32, 32); // TODO

    /**
     * Creates the game map.
     * @param spriteSheet the sprite-sheet of the character moving
     */
    public MapLayer(final BufferedImage spriteSheet) {
        super();

        this.spriteSheet = spriteSheet;
        sprite = getSprite(spriteSheet, JobSprite.FRONT);

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        actionMap.put("UP", new MoveAction(SwingConstants.NORTH));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        actionMap.put("DOWN", new MoveAction(SwingConstants.SOUTH));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
        actionMap.put("LEFT", new MoveAction(SwingConstants.WEST));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
        actionMap.put("RIGHT", new MoveAction(SwingConstants.EAST));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        /* // TODO disegnare mappa
        for (int y = 0; y < map.getMapLength(); y++)
        {
            final List<Cell> row = map.getRow(y);

            for (int x = 0; x < row.size(); x++)
            {
                if (row.get(x).getState() == CellState.FREE) {
                    g.setColor(Color.GREEN);
                }
                if (row.get(x).getState() == CellState.BLOCKED) {
                    g.setColor(Color.RED);
                }

                g.fillRect(y * CELL_SIZE.height, x * CELL_SIZE.width,
                           CELL_SIZE.width, CELL_SIZE.height);
            }
        }
        */

        g.drawImage(sprite,
                    position.x * cellSize.width,
                    position.y * cellSize.height,
                    cellSize.width, cellSize.height, null);
    }

    private static class MoveAction extends AbstractAction {
        private final int direction;

        MoveAction(final int direction) {
            super();
            this.direction = direction;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            /* // TODO muovi party
            try {
                party.moveParty(direction);
            } catch (final BlockedPathException e1) {
                System.out.println("Blocked path!");
            }
            */

            new Thread() {
                @Override
                public void run() {
                    doAnimation(direction);
                }.start();
            }
        }
    }

    private void doAnimation(final int direction) {
        final BufferedImage[] frame = new BufferedImage[2];

        switch (direction) {
            case SwingConstants.NORTH:
                frame[0] = getSprite(spriteSheet, JobSprite.BACK);
                frame[1] = getSprite(spriteSheet, JobSprite.BACK2);
                break;
            case SwingConstants.SOUTH:
                frame[0] = getSprite(spriteSheet, JobSprite.FRONT);
                frame[1] = getSprite(spriteSheet, JobSprite.FRONT2);
                break;
            case SwingConstants.EAST:
                frame[0] = getSprite(spriteSheet, JobSprite.LEFT);
                frame[1] = getSprite(spriteSheet, JobSprite.LEFT2);
                break;
            case SwingConstants.WEST:
                frame[0] = flipImage(getSprite(spriteSheet, JobSprite.LEFT));
                frame[1] = flipImage(getSprite(spriteSheet, JobSprite.LEFT2));
                break;
            default: break;
        }

        sprite = frame[1]; repaint();
        try { sleep(50); } catch (final InterruptedException e) { }
        sprite = frame[0]; repaint();
    }

    private BufferedImage getSprite(final BufferedImage spriteSheet, final JobSprite view) {
        return spriteSheet.getSubimage(view.getPosition().x, view.getPosition().y,
                                       view.getDimension().width, view.getDimension().height);
    }

    private BufferedImage flipImage(final BufferedImage image)
    {
        final AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(- image.getWidth(null), 0);

        AffineTransformOp op;
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        return op.filter(image, null);
    }

    public static void main(final String... args) {
        final View view = new View();

        /* // TODO spriteSheet
        final JPanel mapLayer = new MapLayer();

        view.push(mapLayer);
        view.resizeTo(mapLayer);
        */

        view.run();

        view.centerToScreen();
    }
}