package it.unibo.unori.view.components;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * A custom game button.
 */
public class Button extends JButton implements FocusListener {
    private static final String FOCUS = "res/button/focus.png";
    private static final String DEFAULT = "res/button/default.png";
    private static final String ROLLOVER = "res/button/rollover.png";
    private static final String ROLLOVER2 = "res/button/rollover2.png";

    /**
     * Creates a button with the specified label.
     * @param label text to be shown inside the button
     */
    public Button(final String label) {
        super(label);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        setIcon(new ImageIcon(DEFAULT));
        setPressedIcon(new ImageIcon(DEFAULT));
        setRolloverIcon(new ImageIcon(ROLLOVER));

        final int fontSize = 17;
        setForeground(Color.WHITE);
        setHorizontalTextPosition(JButton.CENTER);
        setFont(new Font("Arial", Font.PLAIN, fontSize));

        this.addFocusListener(this);
    }

    /**
     * Changes the button appearance when focused.
     */
    @Override
    public void focusGained(final FocusEvent e) {
        setIcon(new ImageIcon(FOCUS));
        setRolloverIcon(new ImageIcon(ROLLOVER2));
    }

    /**
     * Reverts changes to the button appearance when unfocused.
     */
    @Override
    public void focusLost(final FocusEvent e) {
        setIcon(new ImageIcon(DEFAULT));
        setRolloverIcon(new ImageIcon(ROLLOVER));
    }
}
