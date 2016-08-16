package it.unibo.unori.view.layers.menus;

import it.unibo.unori.controller.actionlistener.SaveActionListener;

import java.awt.Color;
import java.util.List;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import javax.swing.AbstractAction;

public class MainMenu extends JPanel {
    private static final int BORDER = 5;
    private final Dimension size = new Dimension(160, 160);

    private int focusedButton;
    private final List<MenuButton> buttons = new LinkedList<MenuButton>();

    public MainMenu() {
        super();

        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0, 1, 5, 5));
        this.setBounds(BORDER * 2, BORDER, size.width, size.height);

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        final MenuButton party = new MenuButton("Party");
        party.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {

            }
        });


        final MenuButton items = new MenuButton("Items");
        items.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {

            }
        });

        final MenuButton save = new MenuButton("Save");
        save.addActionListener(new SaveActionListener());

        buttons.add(party);
        buttons.add(items);
        buttons.add(save);

        for (final MenuButton button : buttons) {
            this.add(button);
        }

        buttons.get(focusedButton).requestFocus();

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("UP", new ButtonAction(-1));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        actionMap.put("DOWN", new ButtonAction(1));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        actionMap.put("ENTER", new ButtonAction(0));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
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
}