package it.unibo.unori.view.layers;

import it.unibo.unori.view.sprites.JobSprite;

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

import it.unibo.unori.view.exceptions.SpriteNotFoundException;

/**
 *
 * The game map.
 *
 */
public class MapLayer extends JPanel {
    private Point position;
    private Dimension cellSize;
    private BufferedImage[][] map;

    private boolean dialogue = false;
    private String dialogueText = "";

    private BufferedImage sprite;
    private BufferedImage spriteSheet;

    private final Dimension size;
    private final BufferedImage[] frame = new BufferedImage[2];

    /**
     * Creates the game map.
     *
     * @param movement the action of the character moving
     * @param interact the action of the character interaction
     * @param menu the action that opens the in-game menu
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

        this.map = readMap(map);
        this.position = position;

        size = new Dimension(cellSize.width * this.map.length,
                             cellSize.height * this.map[0].length);

        this.setPreferredSize(size);
        this.setBounds(0, 0, size.width, size.height);

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
                default: // direzione non supportata
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
        this.dialogue = true;
        this.dialogueText = dialogue;

        repaint();
    }

    /**
     * Hide the dialogue.
     */
    public void hideDialogue() {
        dialogue = false;

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
                            x * cellSize.width, y * cellSize.height,
                            cellSize.width, cellSize.height, null);
            }
        }

        g.drawImage(sprite,
                    position.x * cellSize.width,
                    position.y * cellSize.height,
                    cellSize.width, cellSize.height, null);

        if (dialogue) {
            g.drawRect(border, size.height - border,
                       size.width - border * 2, height);

            String text = new String();

            for (final char c : dialogueText.toCharArray()) {
                if (g.getFontMetrics().stringWidth(text + c) > size.width - border * 4) {
                    text = text.concat("\n" + c);
                } else {
                    text = text.concat("" + c);
                }
            }

            g.drawString(dialogueText, border * 2, size.height - border * 2);
        }
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