package it.unibo.unori.view.layers.common;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * A custom button for the menu.
 *
 */
public class MenuButton extends JButton implements FocusListener {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a menu button.
     * @param label the text to be shown inside the button
     */
    public MenuButton(final String label) {
        super(label);

        this.setOpaque(true);
        this.addFocusListener(this);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focusGained(final FocusEvent e) {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createLineBorder(Color.BLUE)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focusLost(final FocusEvent e) {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
}