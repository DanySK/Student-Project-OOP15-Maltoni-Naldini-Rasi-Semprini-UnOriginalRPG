package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;
import it.unibo.unori.view.Button;

import java.util.List;

import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.AbstractAction;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.awt.event.ActionListener;
import it.unibo.unori.view.layers.CharacterSelectionLayer;

/**
 *
 * The main menu of the game.
 *
 */
public class MainMenuLayer extends JPanel {
    private int focusedButton;
    private final List<Button> buttons;

    private final JPanel buttonPanel = new JPanel();
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Dimension SIZE = View.SIZE;

     /*
      * Creates the main menu.
      * @param buttons the list of buttons to be displayed
      */
     public MainMenuLayer(final List<Button> buttons) {
        super();

        this.setPreferredSize(SIZE);
        this.setBounds(0, 0, SIZE.width, SIZE.height);

        this.setLayout(new BorderLayout());
        this.setBackground(BACKGROUND_COLOR);

        BufferedImage logoImage;
        try { // TODO se non trova il logo?
            logoImage = ImageIO.read(new File("res/logo.png"));
        } catch (final IOException e) {
            logoImage = null;
        }
        if (logoImage != null) {
            final JLabel logo = new JLabel(new ImageIcon(logoImage));
            this.add(logo);
        }

        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

        this.buttons = buttons;
        for (final Button button : buttons) {
            buttonPanel.add(button);
        }

        try {
            buttons.get(focusedButton).requestFocus();
        } catch (final IndexOutOfBoundsException e) {
            System.out.println("The button list is empty"); // ERROR
        }

        this.add(buttonPanel, BorderLayout.PAGE_END);

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("LEFT", new ButtonAction(-1));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
        actionMap.put("RIGHT", new ButtonAction(1));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
        actionMap.put("ENTER", new ButtonAction(0));
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

    private class ButtonAction extends AbstractAction {
        private final int direction;

        ButtonAction(final int direction) {
            super();
            this.direction = direction;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (direction == 0) {
                buttons.get(focusedButton).doClick();
            }
            else {
                if (buttons.size() != 0) {
                    focusedButton += direction;

                    if (focusedButton < 0) {
                        focusedButton = buttons.size() - 1;
                    }
                    if (focusedButton > buttons.size() - 1) {
                        focusedButton = 0;
                    }

                    buttons.get(focusedButton).requestFocus();
                }
            }
        }
    }

    public static void main(final String... args) {
        final View view = new View();

        final List<Button> buttons = new ArrayList<Button>();
        final Button button = new Button("Resume Game");

        buttons.add(button);
        buttons.add(new Button("New Game"));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                view.push(new CharacterSelectionLayer(5, button));
            }
        });

        final JPanel mainMenu = new MainMenuLayer(buttons);

        view.push(mainMenu);
        view.resizeTo(mainMenu);

        view.run();
        view.centerToScreen();
    }
}