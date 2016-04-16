package it.unibo.unori.view.components;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * A custom game button.
 */
public class Button extends JButton implements FocusListener {
    private final ImageIcon icon = new ImageIcon("res/button.png");
    private final ImageIcon iconFocus = new ImageIcon("res/buttonFocus.png");

    /**
     * Creates a button with the specified label.
     * @param label text to be shown inside the button
     */
    public Button(final String label) {
        super(label);

        /* foreground */
        setIcon(icon);
        setPressedIcon(icon); // TODO disabled icon

        /* background */
        final int fontSize = 18;
        setForeground(Color.WHITE);
        setHorizontalTextPosition(JButton.CENTER);
        setFont(new Font("Arial", Font.PLAIN, fontSize));

        setFocusPainted(false);
        setBorderPainted(false);
        setRolloverEnabled(false);
        setContentAreaFilled(false);

        this.addFocusListener(this);
    }

    @Override
    public void focusLost(final FocusEvent e) {
        setIcon(icon);
    }

    @Override
    public void focusGained(final FocusEvent e) {
        setIcon(iconFocus);
    }
}
