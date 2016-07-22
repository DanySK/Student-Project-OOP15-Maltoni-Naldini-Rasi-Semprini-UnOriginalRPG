package it.unibo.unori.view.sprites;

import java.util.EnumMap;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;

/**
 *
 * This enum represents the various characters available.
 *
 */
public enum CharacterSprite
{
    CLOWN(new String("res/sprites/clown.png")),
    COOK(new String("res/sprites/cook.png")),
    MAGE(new String("res/sprites/mage.png")),
    PALADIN(new String("res/sprites/paladin.png")),
    RANGER(new String("res/sprites/ranger.png")),
    WARRIOR(new String("res/sprites/warrior.png"));

    private final EnumMap<CharacterSprite.View, BufferedImage> sprite;

    /**
     *
     * This enum represents the various views of a character.
     *
     */
    public enum View
    {
        FRONT(new Point(0, 0), new Dimension(32, 32)),
        BACK(new Point(32, 0), new Dimension(32, 32)),
        LEFT(new Point(64, 0), new Dimension(32, 32)),
        FRONT2(new Point(0, 32), new Dimension(32, 32)),
        BACK2(new Point(32, 32), new Dimension(32, 32)),
        LEFT2(new Point(64, 32), new Dimension(32, 32)),
        BATTLE(new Point(0, 64), new Dimension(32, 48));

        private final Point point;
        private final Dimension dimension;

        View(final Point point, final Dimension dimension)
        {
            this.point = point; this.dimension = dimension;
        }

        Point getPosition() { return point; }
        Dimension getDimension() { return dimension; }
    }

    CharacterSprite(final String path)
    {
        BufferedImage spriteSheet;
        sprite = new EnumMap<>(CharacterSprite.View.class);

        try {
            spriteSheet = ImageIO.read(new File(path));
        } catch (IOException e) {
            spriteSheet = null;
            System.out.println("This sprite do not exist");
        }

        for (CharacterSprite.View spriteView : CharacterSprite.View.values())
        {
            sprite.put(spriteView,
                       spriteSheet.getSubimage(spriteView.getPosition().x,
                                               spriteView.getPosition().y,
                                               spriteView.getDimension().width,
                                               spriteView.getDimension().height));
        }
    }

    /**
     * Returns the sprite associated to the character view specified.
     * @param spriteView the character view of the sprite to be returned
     */
    public BufferedImage getSprite(final CharacterSprite.View spriteView)
    {
        return sprite.get(spriteView);
    }

    /**
     * @return the next character
     */
    public CharacterSprite next()
    {
        return values()[(ordinal() + 1) % values().length];
    }

    /**
     * @return the previous character
     */
    public CharacterSprite previous()
    {
        if (ordinal() - 1 >= 0)
            return values()[ordinal() - 1];
        else
            return values()[values().length - 1];
    }

    public static void main(final String... args)
    {
        final JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());

        BufferedImage sprite;
        sprite = CharacterSprite.CLOWN.getSprite(CharacterSprite.View.FRONT);
        frame.getContentPane().add(new JLabel(new ImageIcon(sprite)));

        frame.pack();
        frame.setVisible(true);
    }
}
