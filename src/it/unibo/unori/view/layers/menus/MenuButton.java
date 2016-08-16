package it.unibo.unori.view.layers.menus;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class MenuButton extends JButton implements FocusListener {
    public MenuButton(final String label) {
        super(label);

        this.addFocusListener(this);
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