package it.unibo.unori.view.layers.menus;

import it.unibo.unori.view.View;
import it.unibo.unori.view.components.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;

import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

/**
 * 
 * Main menu.
 *
 */
public class MainMenuLayer extends MenuLayer {
    private final JPanel buttonPanel = new JPanel();
    private final Color backgroundColor = Color.BLACK;
    private final Dimension size = new Dimension(600, 400);

    /**
     * Creates an instance of the main menu.
     */
     public MainMenuLayer() {
        super();

        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        setBounds(0, 0, size.width, size.height);

        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        addButton(new Button("New Game"));
        addButton(new Button("Load Game"));

        for (final Button b : getButtonList()) {
            buttonPanel.add(b);
            b.addActionListener(new ClickAction());
        }

        add(buttonPanel, BorderLayout.PAGE_END);

        setFocusedButton(0);
        getButtonList().get(getFocusedButton()).requestFocus();

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("LEFT", new MoveAction(-1));
        actionMap.put("RIGHT", new MoveAction(1));
        actionMap.put("ENTER", new PressAction());

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
     }

     /**
      * {@inheritDoc}
      */
     @Override
     public void disable() {
         for (final Component component : buttonPanel.getComponents()) {
             component.setEnabled(false);
         }
     }

     /**
      * Tests the class.
      * @param args arguments
      */
     public static void main(final String... args) {
         final View view = new View();
         final MenuLayer mainMenu = new MainMenuLayer();

         view.push(mainMenu);
         view.resizeTo(mainMenu);

         SwingUtilities.invokeLater(new Runnable() {
             @Override public void run() {
                 view.setVisible(true);
             }
         });

         view.center();
     }
}
