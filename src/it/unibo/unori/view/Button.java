package it.unibo.unori.view;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * A custom JButton.
 *
 */
public class Button extends JButton implements FocusListener
{
    private static final String FOCUS_ICON = "res/button/focus.png";
    private static final String DEFAULT_ICON = "res/button/default.png";
    private static final String ROLLOVER_ICON = "res/button/rollover.png";
    private static final String ROLLOVER2_ICON = "res/button/rollover2.png";

    /**
     * Creates a button with the specified label.
     * @param label text to be shown inside the button
     */
    public Button(final String label)
    {
        super(label);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        setIcon(new ImageIcon(DEFAULT_ICON));
        setPressedIcon(new ImageIcon(DEFAULT_ICON));
        setRolloverIcon(new ImageIcon(ROLLOVER_ICON));

        setForeground(Color.WHITE);
        setHorizontalTextPosition(JButton.CENTER);
        setFont(new Font("Arial", Font.PLAIN, 17));

        this.addFocusListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focusGained(final FocusEvent e)
    {
        setIcon(new ImageIcon(FOCUS_ICON));
        setRolloverIcon(new ImageIcon(ROLLOVER2_ICON));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focusLost(final FocusEvent e)
    {
        setIcon(new ImageIcon(DEFAULT_ICON));
        setRolloverIcon(new ImageIcon(ROLLOVER_ICON));
    }
}