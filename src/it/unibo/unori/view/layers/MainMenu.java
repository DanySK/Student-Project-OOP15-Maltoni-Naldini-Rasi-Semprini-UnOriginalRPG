package it.unibo.unori.view.layers;

import it.unibo.unori.view.components.Button;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

/**
 * 
 * Main menu.
 *
 */
public class MainMenu extends Menu {
    private final Color backgroundColor = Color.BLACK;
    private final Dimension size = new Dimension(600, 400);

    private final JPanel buttonPanel = new JPanel();

    /**
     * Creates an instance of the main menu.
     */
     public MainMenu() {
        super();

        setPreferredSize(size);
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        setBounds(0, 0, size.width, size.height);

        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        final Button[] button = new Button[2];

        button[0] = new Button("New Game");
        button[1] = new Button("Load Game");

        mapButtonsHorizontally(button);

        for (final Button b : button) {
            buttonPanel.add(b);
        }

        add(buttonPanel, BorderLayout.PAGE_END);
     }

     /**
      * {@inheritDoc}
      */
     public void disable() {
         for (final Component component : buttonPanel.getComponents()) {
             component.setEnabled(false);
         }
     }
}
