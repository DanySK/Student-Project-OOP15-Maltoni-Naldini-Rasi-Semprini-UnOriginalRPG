package it.unibo.unori.view;

import javax.swing.JButton;

import it.unibo.unori.controller.utility.ResourceLoader;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

/**
 *
 * A custom JButton for the game's menus.
 *
 */
public class Button extends JButton implements FocusListener {
    private static final int FONT_SIZE = 17;
    private static final String FONT_NAME = "Arial";

    private static final String FOCUS_ICON = "/button/focus.png";
    private static final String DEFAULT_ICON = "/button/default.png";
    private static final String ROLLOVER_ICON = "/button/rollover.png";
    private static final String ROLLOVER2_ICON = "/button/rollover2.png";

    /**
     * Creates a button with the specified label.
     *
     * @param label
     *            the text to be shown inside the button
     * @throws IOException 
     */
    public Button(final String label) {
        super(label);

        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);

        try {
            this.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load(DEFAULT_ICON))));
            this.setPressedIcon(new ImageIcon(ImageIO.read(ResourceLoader.load(DEFAULT_ICON))));
            this.setRolloverIcon(new ImageIcon(ImageIO.read(ResourceLoader.load(ROLLOVER_ICON))));
        } catch (IOException e) {
            System.out.println("image not found: button");
        }
        

        this.setForeground(Color.WHITE);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));

        this.addFocusListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focusGained(final FocusEvent e) {
        try {
            this.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load(FOCUS_ICON))));
            this.setRolloverIcon(new ImageIcon(ImageIO.read(ResourceLoader.load(ROLLOVER2_ICON))));
        } catch (IOException e1) {
            System.out.println("image not found: button");
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focusLost(final FocusEvent e) {
        try {
            this.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load(DEFAULT_ICON))));
            this.setRolloverIcon(new ImageIcon(ImageIO.read(ResourceLoader.load(ROLLOVER_ICON))));
        } catch (IOException e1) {
            System.out.println("image not found: button");
        }
        
    }
}
