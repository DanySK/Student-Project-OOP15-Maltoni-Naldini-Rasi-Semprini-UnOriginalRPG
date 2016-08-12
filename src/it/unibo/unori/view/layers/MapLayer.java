package it.unibo.unori.view.layers;

import it.unibo.unori.view.sprites.JobSprite;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;

import java.util.Map;

import java.io.File;
import java.io.IOException;

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
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/**
 *
 * The game map.
 *
 */
public class MapLayer extends JPanel {
    private static final Dimension SIZE = new Dimension(640, 640); // TODO
    private static final Dimension CELL_SIZE = new Dimension(32, 32);

    private Point position;
    private BufferedImage[][] map;

    private boolean dialogueActive;
    private String dialogueText = "";

    private BufferedImage sprite;
    private BufferedImage spriteSheet;
    private final BufferedImage[] frame = new BufferedImage[2];

    /**
     * Creates the game map.
     *
     * @param menu the action that openes the in-game menu
     * @param movement the action that moves the character
     * @param interact the action that interacts with the map
     *
     * @param map the paths of the map's sprites
     * @param position the initial position of the character
     * @param spriteSheetPath the path of the character's sprite sheet
     *
     * @throws SpriteNotFoundException a sprite is not found
     */
    public MapLayer(final Map<Integer, Action> movement,
                    final Action interact, final Action menu,
                    final String[][] map, final Point position,
                    final String spriteSheetPath) throws SpriteNotFoundException {
        super();

        this.map = readMap(map); // reads the map
        this.position = position; // reads the position

        this.setPreferredSize(SIZE); // sets the size of this layer
        this.setBounds(0, 0, SIZE.width, SIZE.height); // sets the size and position in the view

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
                default: // TODO direzione non supportata?
                    break;
            }
        }

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
        actionMap.put("ENTER", interact);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        actionMap.put("ESCAPE", menu);
    }

    /**
     * Moves the character in the 4 cardinal directions.
     * @param direction the direction the character will move to
     */
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

    /**
     * Move the character to the specified position.
     * @param position the position the character will move to.
     */
    public void move(final Point position) {
        this.position = position;

        repaint();
    }

    /**
     * Change the current map
     * @param map the new map's sprite paths
     * @param position the position the character will be in
     * @throws SpriteNotFoundException a sprite is not found
     */
    public void changeMap(final String[][] map,
                          final Point position) throws SpriteNotFoundException {
        this.map = readMap(map);
        this.position = position;

        repaint();
    }

    /**
     * Show a dialogue in the view.
     * @param dialogue the text to be shown inside the dialogue
     */
    public void showDialogue(final String dialogue) {
        this.dialogueActive = true;
        this.dialogueText = dialogue;

        repaint();
    }

    /**
     * Hide the dialogue.
     */
    public void hideDialogue() {
        dialogueActive = false;

        repaint();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final int border = 10;
        final int height = 100;

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                g.drawImage(map[x][y],
                            x * CELL_SIZE.width, y * CELL_SIZE.height,
                            CELL_SIZE.width, CELL_SIZE.height, null);
            }
        }

        g.drawImage(sprite,
                    position.x * CELL_SIZE.width,
                    position.y * CELL_SIZE.height,
                    CELL_SIZE.width, CELL_SIZE.height, null);

        if (dialogueActive) {
            g.drawRect(border, SIZE.height - border,
                       SIZE.width - border * 2, height);

            final StringBuilder stringBuilder = new StringBuilder();

            for (final char c : dialogueText.toCharArray()) {
                if (g.getFontMetrics().stringWidth(stringBuilder.toString() + c)
                    > SIZE.width - border * 4) {
                    stringBuilder.append('\n');
                }
                stringBuilder.append(c);
            }

            g.drawString(stringBuilder.toString(), border * 2, SIZE.height - border * 2);
        }
    }

    private BufferedImage[][] readMap(final String[]... map) throws SpriteNotFoundException {
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