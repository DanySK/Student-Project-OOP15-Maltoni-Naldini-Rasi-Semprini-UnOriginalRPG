package it.unibo.unori.view.layers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import it.unibo.unori.controller.utility.ResourceLoader;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.sprites.JobSprite;

/**
 *
 * The game map.
 *
 */
public class MapLayer extends Layer {
    private static final long serialVersionUID = 1L;

    /**
     * The default map size.
     */
    public static final Dimension SIZE = new Dimension(960, 720);
    private static final Dimension CELL_SIZE = new Dimension(32, 32);

    private Point mapStartingPoint;

    private Point position;
    private BufferedImage[][] map;
    private BufferedImage spriteSheet;

    private boolean dialogueActive;
    private String dialogueText = "";

    private BufferedImage sprite;
    private final BufferedImage[] frame = new BufferedImage[2];

    private final Action menu;
    private final Action interact;
    private final Map<Integer, Action> movement;

    /**
     * Creates the game map.
     *
     * @param movement
     *            the action that moves the character
     * @param menu
     *            the action that openes the in-game menu
     * @param interact
     *            the action that interacts with the map
     *
     * @param map
     *            the game map as a matrix of image paths
     * @param position
     *            the initial position of the character
     * @param spriteSheetPath
     *            the path of the character's sprite sheet
     *
     * @throws SpriteNotFoundException
     *             if a sprite is not found
     */
    public MapLayer(final Map<Integer, Action> movement,
            final Action interact, final Action menu, final String[][] map, final Point position,
            final String spriteSheetPath) throws SpriteNotFoundException {
        super();

        this.menu = menu;
        this.interact = interact;
        this.movement = movement;

        this.setBackground(Color.BLACK);

        this.map = readMap(map);
        this.position = position;

        this.setBounds(0, 0, SIZE.width, SIZE.height);

        try {
            spriteSheet = ImageIO.read(ResourceLoader.load(spriteSheetPath));
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
                case SwingConstants.WEST:
                    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
                    actionMap.put("LEFT", entry.getValue());
                    break;
                case SwingConstants.EAST:
                    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
                    actionMap.put("RIGHT", entry.getValue());
                    break;
                default:
                    break;
            }
        }

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
        actionMap.put("ENTER", this.interact);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        actionMap.put("ESCAPE", this.menu);
    }

    /**
     * Moves the character in the 4 cardinal directions.
     *
     * @param direction
     *            the direction the character will move to
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
            case SwingConstants.WEST:
                frame[0] = getSprite(spriteSheet, JobSprite.LEFT);
                frame[1] = getSprite(spriteSheet, JobSprite.LEFT2);
                break;
            case SwingConstants.EAST:
                frame[0] = flipImage(getSprite(spriteSheet, JobSprite.LEFT));
                frame[1] = flipImage(getSprite(spriteSheet, JobSprite.LEFT2));
                break;
            default:
                break;
        }

        new Thread() {
            @Override
            public void run() {
                final int delay = 50;

                sprite = frame[1];
                repaint();
                try {
                    sleep(delay);
                } catch (final InterruptedException e) {
                }
                sprite = frame[0];
                repaint();
            }
        }.start();

        switch (direction) {
            case SwingConstants.NORTH:
                position.translate(-1, 0);
                break;
            case SwingConstants.SOUTH:
                position.translate(1, 0);
                break;
            case SwingConstants.EAST:
                position.translate(0, 1);
                break;
            case SwingConstants.WEST:
                position.translate(0, -1);
                break;
            default:
                break;
        }
    }

    /**
     * Make the character turn.
     *
     * @param direction
     *            the direction to turn to.
     */
    public void rotate(final int direction) {
        switch (direction) {
            case SwingConstants.NORTH:
                frame[0] = getSprite(spriteSheet, JobSprite.BACK);
                break;
            case SwingConstants.SOUTH:
                frame[0] = getSprite(spriteSheet, JobSprite.FRONT);
                break;
            case SwingConstants.WEST:
                frame[0] = getSprite(spriteSheet, JobSprite.LEFT);
                break;
            case SwingConstants.EAST:
                frame[0] = flipImage(getSprite(spriteSheet, JobSprite.LEFT));
                break;
            default:
                break;
        }

        sprite = frame[0];
        repaint();
    }

    /**
     * Move the character to the specified position.
     *
     * @param position
     *            the position the character will move to.
     */
    public void move(final Point position) {
        this.position = position;

        repaint();
    }

    /**
     * Change the current map.
     *
     * @param map
     *            the new map's sprite paths
     * @param position
     *            the position the character will be in
     * @throws SpriteNotFoundException
     *             a sprite is not found
     */
    public void changeMap(final String[][] map, final Point position) throws SpriteNotFoundException {
        this.map = readMap(map);
        this.position = position;

        repaint();
    }

    /**
     * Show a dialogue in the view.
     *
     * @param dialogue
     *            the text to be shown inside the dialogue
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
    public void setEnabled(final boolean b) {
        super.setEnabled(b);

        menu.setEnabled(b);
        interact.setEnabled(b);
        movement.forEach((i, a) -> a.setEnabled(b));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                g.drawImage(map[x][y], mapStartingPoint.y + y * CELL_SIZE.height,
                        mapStartingPoint.x + x * CELL_SIZE.width, CELL_SIZE.height, CELL_SIZE.width, null);
            }
        }

        g.drawImage(sprite, mapStartingPoint.y + position.y * CELL_SIZE.height,
                mapStartingPoint.x + position.x * CELL_SIZE.width, CELL_SIZE.height, CELL_SIZE.width, null);

        if (dialogueActive) {
            final int border = 10;
            final int height = 100;
            final int x = border * 2;
            final double leftBorder = 4.5;
            int y = SIZE.height - height - border / 2;

            g.setColor(Color.WHITE);
            g.fillRect(border, SIZE.height - border - height, SIZE.width - border * 2, height);

            g.setColor(Color.BLACK);
            final StringBuilder stringBuilder = new StringBuilder();

            for (final char c : dialogueText.toCharArray()) {
                if (c == '\n') {
                    y += g.getFontMetrics().getHeight();
                    g.drawString(stringBuilder.toString(), x, y);

                    stringBuilder.setLength(0);
                } else if (g.getFontMetrics().stringWidth(stringBuilder.toString() + c) > SIZE.width
                        - border * leftBorder) {
                    y += g.getFontMetrics().getHeight();
                    g.drawString(stringBuilder.toString(), x, y);

                    stringBuilder.setLength(0);
                    stringBuilder.append(c);
                } else {
                    stringBuilder.append(c);
                }
            }
            g.drawString(stringBuilder.toString(), x, y + g.getFontMetrics().getHeight());
        }
    }

    private BufferedImage[][] readMap(final String[]... map) throws SpriteNotFoundException {
        final int width = map.length;
        final int height = map[0].length;

        final BufferedImage[][] mapImage = new BufferedImage[map.length][map[0].length];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                try {
                    mapImage[x][y] = ImageIO.read(ResourceLoader.load(map[x][y]));
                } catch (final IOException e) {
                    mapImage[x][y] = null;
                    throw new SpriteNotFoundException(map[x][y]);
                }
            }
        }

        mapStartingPoint = new Point((SIZE.height - width * CELL_SIZE.width) / 2,
                (SIZE.width - height * CELL_SIZE.height) / 2);

        return mapImage;
    }

    private BufferedImage getSprite(final BufferedImage spriteSheet, final JobSprite view) {
        return spriteSheet.getSubimage(view.getPosition().x, view.getPosition().y, view.getDimension().width,
                view.getDimension().height);
    }

    private BufferedImage flipImage(final BufferedImage image) {
        final AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);

        AffineTransformOp op;
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        return op.filter(image, null);
    }
}
