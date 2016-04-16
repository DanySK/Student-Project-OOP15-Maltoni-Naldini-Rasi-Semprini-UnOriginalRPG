package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;
import it.unibo.unori.view.components.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * Main menu layer.
 *
 */
public class MainMenu extends JPanel {
    private final Dimension size = new Dimension(800, 600);

    /**
     * Creates an instance of the main menu.
     */
     public MainMenu() {
        super();
        setPreferredSize(size);
        setBounds(0, 0, size.width, size.height);

        final Color background = Color.BLACK;

        setBackground(background);
        setLayout(new BorderLayout());

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(background);

        final int buttons = 2;
        final Button[] button = new Button[buttons];

        button[0] = new Button("New Game");
        button[1] = new Button("Load Game");

        for (int i = 0; i < buttons; i++) {
            final int cur = i;
            button[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(final KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                        ((Button) e.getComponent()).doClick();
                    }

                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                           if (cur > 0) {
                               button[cur - 1].requestFocus();
                           }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                           if (cur < buttons - 1) {
                               button[cur + 1].requestFocus();
                           }
                    }
                }
            });

            buttonPanel.add(button[i]);
        }

        add(buttonPanel, BorderLayout.PAGE_END);
    }

     /**
      * Tests this class.
      * @param args main's arguments
      */
     public static void main(final String... args) {
         final View view = new View();

         view.push(new MainMenu());

         SwingUtilities.invokeLater(new Runnable() {
             @Override public void run() {
                 view.setVisible(true);
             }
         });
     }
}
