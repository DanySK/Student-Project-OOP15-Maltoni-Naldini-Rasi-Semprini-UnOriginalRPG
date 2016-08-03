package it.unibo.unori.view.layers;

import it.unibo.unori.view.Button;
import it.unibo.unori.view.sprites.CharacterSprite;

import java.util.List;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.Point;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import it.unibo.unori.view.View;
import javax.swing.SwingUtilities;

/**
 *
 * The character selection menu.
 *
 */
public class CharacterSelectionLayer extends JPanel
{
    private static final int PARTY_SIZE = 5;
    private CharacterSprite character = CharacterSprite.values()[0];
    private List<CharacterSprite> party = new LinkedList<CharacterSprite>();

    private static final int SEPARATOR = 25;
    private static final Point BORDER = new Point(25, 25);
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Dimension SIZE = new Dimension(325, 300);

    /**
     * Creates the character selection menu.
     */
    public CharacterSelectionLayer()
    {
        this.setPreferredSize(SIZE);
        this.setBackground(BACKGROUND_COLOR);
        this.setBounds(0, 0, SIZE.width, SIZE.height);

        this.setLayout(null);
        Insets insets = this.getInsets();

        Button button = new Button("Done");
        JTextField textField = new JTextField(10);
        BasicArrowButton left = new BasicArrowButton(SwingConstants.WEST);
        BasicArrowButton right = new BasicArrowButton(SwingConstants.EAST);

        /* TEXT FIELD */

        Dimension textFieldSize = textField.getPreferredSize();
        textField.setBounds(BORDER.x + insets.left,
                            BORDER.y + insets.top,
                            textFieldSize.width, textFieldSize.height);

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e)
            {
                if (party.size() <= PARTY_SIZE)
                {
                    if (party.size() == PARTY_SIZE)
                    {
                        button.setEnabled(true);
                    }

                    party.add(character);
                }
                textField.setText("");

                repaint();
            }
        });

        this.add(textField);

        /* ARROWS */

        Dimension arrowSize = left.getPreferredSize();
        left.setBounds(BORDER.x + insets.left,
                             BORDER.y + SEPARATOR * 2 + insets.top,
                             arrowSize.width, arrowSize.height);
        right.setBounds(BORDER.x + (int) (SEPARATOR * 2.6) + insets.left,
                              BORDER.y + SEPARATOR * 2 + insets.top,
                              arrowSize.width, arrowSize.height);

        left.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e)
            {
                character = character.previous();
                repaint();
            }
        });
        right.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e)
            {
                character = character.next();
                repaint();
            }
        });

        this.add(left);
        this.add(right);

        /* BUTTON */

        Dimension buttonSize = button.getPreferredSize();
        button.setBounds(BORDER.x + SEPARATOR * 2 + insets.left,
                         BORDER.y + SEPARATOR * 8 + insets.top,
                         buttonSize.width, buttonSize.height);

        button.setEnabled(false);
        this.add(button);
    }

    @Override
    protected void paintComponent(final Graphics g)
    {
        super.paintComponent(g);
        BufferedImage sprite;

        // SELECTED

        sprite = character.getSprite(CharacterSprite.View.BATTLE);

        g.drawImage(sprite, BORDER.x + SEPARATOR,
                    SEPARATOR * 3,
                    sprite.getWidth(), sprite.getHeight(), null);

        // ENTIRE PARTY

        for (int i = 0; i < party.size(); i++)
        {
            sprite = party.get(i).getSprite(CharacterSprite.View.BATTLE);

            g.drawImage(sprite,
                        BORDER.x + SEPARATOR * 2 * i,
                        BORDER.y + SEPARATOR * 5,
                        sprite.getWidth(), sprite.getHeight(), null);
        }
    }

    public static void main(final String... args)
    {
        final View view = new View();
        final JPanel characterSelection = new CharacterSelectionLayer();

        view.push(characterSelection);
        view.resizeTo(characterSelection);

        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() { view.setVisible(true); }
        });

        view.centerToScreen();
    }
}