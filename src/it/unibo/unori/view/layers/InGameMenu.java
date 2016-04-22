package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;
import it.unibo.unori.view.components.Button;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

/**
 * 
 * In-game menu.
 *
 */
public class InGameMenu extends Menu {
    /**
     * 
     */
    private final Color backgroundColor = Color.GRAY;
    private final Dimension size = new Dimension(200, 400);

    /**
     * Creates an instance of the in-game menu.
     */
    public InGameMenu() {
        super();

        setOpaque(true);
        setPreferredSize(size);
        setBackground(backgroundColor);
        setBounds(0, 0, size.width, size.height);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        final Button[] button = new Button[4];

        button[0] = new Button("Party");
        button[1] = new Button("Items");
        button[2] = new Button("Save");
        button[3] = new Button("Quit");

        mapButtonsVertically(button);

        for (final Button b : button) {
            b.setAlignmentX(Component.CENTER_ALIGNMENT);

            add(b);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void disable() {
        for (final Component component : getComponents()) {
            component.setEnabled(false);
        }
    }

    /**
     * Tests this class.
     * 
     * @param args main's arguments
     */
    public static void main(final String... args) {
        final View view = new View();
        final Menu mainMenu = new MainMenu();
        final Menu inGameMenu = new InGameMenu();

        view.push(mainMenu);
        view.resizeTo(mainMenu);

        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                view.setVisible(true);
            }
        });

        view.center();

        mainMenu.disable();
        view.push(inGameMenu);
    }
}
