package it.unibo.unori.view.layers;

import it.unibo.unori.view.sprites.CharacterSprite;

import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellState;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.model.maps.exceptions.BlockedPathException;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import javax.swing.JFrame;
import it.unibo.unori.view.View;
import javax.swing.SwingUtilities;
import it.unibo.unori.model.maps.GameMapImpl;

/**
 *
 * The game map.
 *
 */
public class MapLayer extends JPanel
{
    private BufferedImage sprite;
    private CharacterSprite character;
    private final Party party = SingletonParty.getParty();
    private final GameMap map = party.getCurrentGameMap();

    private final Dimension size = new Dimension();
    private static final Dimension CELL_SIZE = new Dimension(32, 32);

    /**
     * Creates the game map.
     */
    public MapLayer()
    {
        super();

        size.width = map.getMapWidth() * CELL_SIZE.width;
        size.height = map.getMapLength() * CELL_SIZE.height;

        setPreferredSize(size);
        setBounds(0, 0, size.width, size.height);

        character = CharacterSprite.CLOWN;
        sprite = character.getSprite(CharacterSprite.View.FRONT);

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        actionMap.put("UP", new MoveAction(Party.CardinalPoints.NORTH));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        actionMap.put("DOWN", new MoveAction(Party.CardinalPoints.SOUTH));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
        actionMap.put("LEFT", new MoveAction(Party.CardinalPoints.WEST));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
        actionMap.put("RIGHT", new MoveAction(Party.CardinalPoints.EAST));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (int y = 0; y < map.getMapLength(); y++)
        {
            final List<Cell> row = map.getRow(y);

            for (int x = 0; x < row.size(); x++)
            {
                if (row.get(x).getState() == CellState.FREE)
                    g.setColor(Color.GREEN);
                if (row.get(x).getState() == CellState.BLOCKED)
                    g.setColor(Color.RED);

                g.fillRect(y * CELL_SIZE.height, x * CELL_SIZE.width,
                           CELL_SIZE.width, CELL_SIZE.height);
            }
        }

        g.drawImage(sprite,
                    party.getCurrentPosition().getPosY() * CELL_SIZE.width,
                    party.getCurrentPosition().getPosX() * CELL_SIZE.height,
                    CELL_SIZE.width, CELL_SIZE.height, null);
    }

    private class MoveAction extends AbstractAction
    {
        private final Party.CardinalPoints direction;

        MoveAction(final Party.CardinalPoints direction)
        {
            super();
            this.direction = direction;
        }

        @Override
        public void actionPerformed(final ActionEvent e)
        {
            try
            {
                party.moveParty(direction);
            }
            catch (BlockedPathException e1)
            {
                System.out.println("Blocked path!");
            }

            new Thread() {
                @Override
                public void run()
                {
                    BufferedImage[] frame = new BufferedImage[2];

                    switch (direction)
                    {
                        case NORTH:
                            frame[0] = character.getSprite(CharacterSprite.View.BACK);
                            frame[1] = character.getSprite(CharacterSprite.View.BACK2);
                            break;
                        case SOUTH:
                            frame[0] = character.getSprite(CharacterSprite.View.FRONT);
                            frame[1] = character.getSprite(CharacterSprite.View.FRONT2);
                            break;
                        case WEST:
                            frame[0] = character.getSprite(CharacterSprite.View.LEFT);
                            frame[1] = character.getSprite(CharacterSprite.View.LEFT2);
                            break;
                        case EAST:
                            frame[0] = flipImage(character.getSprite(CharacterSprite.View.LEFT));
                            frame[1] = flipImage(character.getSprite(CharacterSprite.View.LEFT2));
                            break;
                        default: break;
                    }

                    sprite = frame[1]; repaint();
                    try { sleep(50); } catch (InterruptedException e) { }
                    sprite = frame[0]; repaint();
                }
            }.start();
        }
    }

    private BufferedImage flipImage(BufferedImage image)
    {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(- image.getWidth(null), 0);
        AffineTransformOp op;
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        return op.filter(image, null);
    }

    public static void main(final String... args)
    {
        final View view = new View();
        SingletonParty.getParty().setCurrentMap(new GameMapImpl(12, 12));

        final JPanel mapLayer = new MapLayer();

        view.push(mapLayer);
        view.resizeTo(mapLayer);

        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() { view.setVisible(true); }
        });

        view.center();
    }
}