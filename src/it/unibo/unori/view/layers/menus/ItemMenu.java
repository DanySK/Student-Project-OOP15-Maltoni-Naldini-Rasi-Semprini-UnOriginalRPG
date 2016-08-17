package it.unibo.unori.view.layers.menus;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibo.unori.model.items.Bag;

public class ItemMenu extends JScrollPane {
        private static final int BORDER = 5;
        private final Dimension size = new Dimension(160, 320);

        private int focusedButton;
        private final List<MenuButton> buttons = new LinkedList<MenuButton>();

        private final JPanel buttonPanel = new JPanel();

        public ItemMenu(final Bag bag) {
            super();

            this.setBounds(200, 200, size.width, size.height);

            for (int i = 0; i < 10; i++) {
                buttonPanel.add(new MenuButton("teest"));
            }

            this.add(buttonPanel);
        }
    }