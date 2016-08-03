package it.unibo.unori.view.layers;

import it.unibo.unori.view.Button;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.AbstractAction;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import it.unibo.unori.view.View;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;

/**
 * 
 * A menu that can be opened in-game.
 *
 */
public class InGameMenuLayer extends JPanel
{
    private int focusedButton = 0;
    private final List<Button> buttons;

    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Dimension SIZE = new Dimension(200, 400);

    /**
     * Creates the in-game menu.
     * @param buttons the list of buttons to be displayed
     */
    public InGameMenuLayer(final List<Button> buttons)
    {
        super();

        this.buttons = buttons;

        this.setOpaque(true);
        this.setPreferredSize(SIZE);
        this.setBackground(BACKGROUND_COLOR);
        this.setBounds(0, 0, SIZE.width, SIZE.height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        for (final Button button : buttons)
        {
            this.add(button);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        try {
            buttons.get(focusedButton).requestFocus();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The button list is empty");
        }

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("UP", new ButtonAction(-1));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        actionMap.put("DOWN", new ButtonAction(1));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        actionMap.put("ENTER", new ButtonAction(0));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disable()
    {
        for (final Component component : getComponents())
        {
            component.setEnabled(false);
        }
    }

    private class ButtonAction extends AbstractAction
    {
        private final int direction;

        ButtonAction(final int direction)
        {
            super();
            this.direction = direction;
        }

        @Override
        public void actionPerformed(final ActionEvent e)
        {
            if (direction == 0)
            {
                buttons.get(focusedButton).doClick();
            }
            else
            {
                if (buttons.size() != 0)
                {
                    focusedButton += direction;

                    if (focusedButton < 0)
                        focusedButton = buttons.size() - 1;
                    if (focusedButton > buttons.size() - 1)
                        focusedButton = 0;

                    buttons.get(focusedButton).requestFocus();
                }
            }
        }
    }

    public static void main(final String... args)
    {
        final View view = new View();

        /* MAIN MENU */

        final List<Button> buttons = new ArrayList<Button>();
        final Button button = new Button("Resume Game");

        buttons.add(button);
        buttons.add(new Button("New Game"));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e)
            {
                System.out.println("Resume Game");
            }
        });

        final JPanel mainMenu = new MainMenuLayer(buttons);

        view.push(mainMenu);
        view.resizeTo(mainMenu);

        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() { view.setVisible(true); }
        });

        view.centerToScreen();

        /* IN-GAME MENU */

        final List<Button> inGameButtons = new ArrayList<Button>();

        inGameButtons.add(new Button("Party"));
        inGameButtons.add(new Button("Stats"));

        final JPanel inGameMenu = new InGameMenuLayer(inGameButtons);

        mainMenu.disable();
        view.push(inGameMenu);
    }
}