package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;
import it.unibo.unori.view.Button;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

/**
 *
 * This class displays a message to the user.
 *
 */
public class DialogLayer extends JPanel {
    final private Button button;
    final private static Dimension SIZE = new Dimension(View.SIZE.width / 2,
                                                        View.SIZE.height / 2);

    /**
     * Creates a dialog.
     */
    public DialogLayer(String message, Button button) {
        this.setPreferredSize(SIZE);
        this.setBounds(View.SIZE.width / 4, View.SIZE.height / 4,
                       SIZE.width, SIZE.height);

        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);

        this.setLayout(new BorderLayout());

        this.button = button;

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);

        this.add(label, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);

        button.requestFocus();

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("ENTER", new ButtonAction());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
    }

    private class ButtonAction extends AbstractAction {
        ButtonAction() {
            super();
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            button.doClick();
        }
    }

    public static void main(final String... args) {
        final Button button = new Button("OK");

        final View view = new View();
        final JPanel dialog = new DialogLayer("C'è stato un errore di qualche tipo", button);

        view.resize();
        view.push(dialog);

        view.run();

        view.centerToScreen();
    }

}