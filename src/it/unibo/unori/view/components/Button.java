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
    private final ImageIcon icon = new ImageIcon("res/button/icon.png");
    private final ImageIcon focusIcon = new ImageIcon("res/button/focusIcon.png");
    private final ImageIcon rolloverIcon = new ImageIcon("res/button/rolloverIcon.png");
    private final ImageIcon rolloverFocusIcon = new ImageIcon("res/button/rolloverFocusIcon.png");

    /**
     * Creates a button with the specified label.
     * @param label text to be shown inside the button
     */
    public Button(final String label) {
        super(label);

        setIcon(icon);
        setPressedIcon(icon);
        setRolloverIcon(rolloverIcon);
        this.addFocusListener(this);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        final int fontSize = 17;
        setForeground(Color.WHITE);
        setHorizontalTextPosition(JButton.CENTER);
        setFont(new Font("Arial", Font.PLAIN, fontSize));
    }

    /**
     * Changes the button appearance when focused.
     */
    @Override
    public void focusGained(final FocusEvent e) {
        setIcon(focusIcon);
        setRolloverIcon(rolloverFocusIcon);
    }

    /**
     * Reverts changes to the button appearance when unfocused.
     */
    @Override
    public void focusLost(final FocusEvent e) {
        setIcon(icon);
        setRolloverIcon(rolloverIcon);
    }
}
