package it.unibo.unori.view;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * A custom JButton for the game's menus.
 *
 */
public class Button extends JButton implements FocusListener
{
    private static final int FONT_SIZE = 17;
    private static final String FONT_NAME = "Arial";

    private static final String FOCUS_ICON = "res/button/focus.png";
    private static final String DEFAULT_ICON = "res/button/default.png";
    private static final String ROLLOVER_ICON = "res/button/rollover.png";
    private static final String ROLLOVER2_ICON = "res/button/rollover2.png";

    /**
     * Creates a button with the specified label.
     * @param label the text to be shown inside the button
     */
    public Button(final String label) {
        super(label);

        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);

        this.setIcon(new ImageIcon(DEFAULT_ICON));
        this.setPressedIcon(new ImageIcon(DEFAULT_ICON));
        this.setRolloverIcon(new ImageIcon(ROLLOVER_ICON));

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
        this.setIcon(new ImageIcon(FOCUS_ICON));
        this.setRolloverIcon(new ImageIcon(ROLLOVER2_ICON));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focusLost(final FocusEvent e) {
        this.setIcon(new ImageIcon(DEFAULT_ICON));
        this.setRolloverIcon(new ImageIcon(ROLLOVER_ICON));
    }
}