package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;
import it.unibo.unori.view.Button;

import javax.swing.JLabel;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

/**
 *
 * This class displays a message to the user.
 *
 */
public class DialogLayer extends Layer {
    private static final long serialVersionUID = 1L;

    private final Button button;
    private static final Dimension SIZE = new Dimension(View.SIZE.width / 2, View.SIZE.height / 2);

    // TODO sfondo non nero
    /**
     * Creates a dialog.
     *
     * @param message
     *            the message to be shown
     * @param button
     *            the button the be shown
     */
    public DialogLayer(final String message, final Button button) {
        super();

        this.setPreferredSize(SIZE);
        this.setBounds(0, 0, SIZE.width, SIZE.height);

        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);

        this.setLayout(new BorderLayout());

        this.button = button;

        final String htmlText = "<html>" + message.replaceAll("\n", "<br/>") + "</html>";
        final JLabel label = new JLabel(htmlText, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);

        this.add(label, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);

        button.requestFocus();

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("ENTER", new ButtonAction());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnabled(final boolean b) {
        for (final Component component : this.getComponents()) {
            component.setEnabled(b);
        }
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
}
