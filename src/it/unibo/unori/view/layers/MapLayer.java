package it.unibo.unori.view.layers;

import it.unibo.unori.view.sprites.JobSprite;

import java.util.Map;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.imageio.ImageIO;
import javax.swing.SwingConstants;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import it.unibo.unori.view.exceptions.SpriteNotFoundException;

/**
 *
 * The game map.
 *
 */
public class MapLayer extends JPanel {
    private BufferedImage sprite;
    private BufferedImage spriteSheet;
    private final BufferedImage[] frame = new BufferedImage[2];

    private Point position;
    private Dimension cellSize;
    private BufferedImage[][] map;

    /**
     * Creates the game map.
     * @param spriteSheet the sprite-sheet of the character moving
     * @throws SpriteNotFoundException if a game sprite is not found
     */
    public MapLayer(final Map<Integer, Action> movement,
                    final Action interact, final Action menu,
                    final String[][] map, final Point position,
                    final String spriteSheetPath) throws SpriteNotFoundException {
        super();

        this.map = readMap(map);
        this.position = position;

        try {
            spriteSheet = ImageIO.read(new File(spriteSheetPath));
        } catch (final IOException e) {
            spriteSheet = null;
            throw new SpriteNotFoundException(spriteSheetPath);
        }
        sprite = getSprite(spriteSheet, JobSprite.FRONT);

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        for (final Map.Entry<Integer, Action> entry : movement.entrySet()) {
            switch (entry.getKey()) {
                case SwingConstants.NORTH:
                    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
                    actionMap.put("UP", entry.getValue());
                    break;
                case SwingConstants.SOUTH:
                    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
                    actionMap.put("DOWN", entry.getValue());
                    break;
                case SwingConstants.EAST:
                    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
                    actionMap.put("LEFT", entry.getValue());
                    break;
                case SwingConstants.WEST:
                    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
                    actionMap.put("RIGHT", entry.getValue());
                    break;
            }
        }

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
        actionMap.put("ENTER", interact);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        actionMap.put("ESCAPE", menu);
    }

    public void move(final int direction) {
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

        new Thread() {
            @Override
            public void run() {
                sprite = frame[1]; repaint();
                try { sleep(50); } catch (final InterruptedException e) { }
                sprite = frame[0]; repaint();
            }
        }.start();
    }

    public void move(final Point position) {
        this.position = position;

        repaint();
    }

    public void changeMap(final String[][] map,
                          final Point position) throws SpriteNotFoundException {
        this.map = readMap(map);
        this.position = position;

        repaint();
    }

    public void showDialogue(final String dialogue) {

    }

    public void hideDialogue() {

    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                g.drawImage(map[x][y],
                            x * cellSize.width, y * cellSize.height,
                            cellSize.width, cellSize.height, null);
            }
        }

        g.drawImage(sprite,
                    position.x * cellSize.width,
                    position.y * cellSize.height,
                    cellSize.width, cellSize.height, null);
    }

    private BufferedImage[][] readMap(final String[][] map) throws SpriteNotFoundException {
        final BufferedImage[][] mapImage = new BufferedImage[map.length][map[0].length];

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                try {
                    mapImage[x][y] = ImageIO.read(new File(map[x][y]));
                } catch (final IOException e) {
                    mapImage[x][y] = null;
                    throw new SpriteNotFoundException(map[x][y]);
                }
            }
        }

        cellSize = new Dimension(mapImage[0][0].getWidth(), mapImage[0][0].getHeight());

        return mapImage;
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
}