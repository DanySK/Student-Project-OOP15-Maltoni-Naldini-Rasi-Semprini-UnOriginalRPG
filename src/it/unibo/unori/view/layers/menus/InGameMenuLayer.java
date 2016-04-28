package it.unibo.unori.view.layers.menus;

import it.unibo.unori.view.View;
import it.unibo.unori.view.components.Button;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

/**
 * 
 * In-game menu.
 *
 */
public class InGameMenuLayer extends MenuLayer {
    private final Color backgroundColor = Color.GRAY;
    private final Dimension size = new Dimension(200, 400);

    /**
     * Creates the in-game menu.
     */
    public InGameMenuLayer() {
        super();

        setOpaque(true);
        setPreferredSize(size);
        setBackground(backgroundColor);
        setBounds(0, 0, size.width, size.height);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        addButton(new Button("Party"));
        addButton(new Button("Items"));
        addButton(new Button("Save"));
        addButton(new Button("Quit"));

        for (final Button b : getButtonList()) {
            this.add(b);
            b.addActionListener(new ClickAction());
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        setFocusedButton(0);
        getButtonList().get(getFocusedButton()).requestFocus();

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("UP", new MoveAction(-1));
        actionMap.put("DOWN", new MoveAction(1));
        actionMap.put("ENTER", new PressAction());

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disable() {
        for (final Component component : getComponents()) {
            component.setEnabled(false);
        }
    }

    /**
     * Tests this class.
     * @param args arguments
     */
    public static void main(final String... args) {
        final View view = new View();
        final MenuLayer mainMenu = new MainMenuLayer();
        final MenuLayer inGameMenu = new InGameMenuLayer();

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
