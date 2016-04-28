package it.unibo.unori.view.layers.menus;

import it.unibo.unori.view.layers.Layer;
import it.unibo.unori.view.components.Button;

import java.util.List;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * A game menu.
 *
 */
public abstract class MenuLayer extends Layer {
    private int focusedButton;
    private final List<Button> buttonList = new ArrayList<Button>();

    /**
     * Get the list of buttons.
     * @return the list of the buttons
     */
    protected List<Button> getButtonList() {
        return buttonList;
    }

    /**
     * Adds a button to the menu.
     * @param button the button to be added
     */
    protected void addButton(final Button button) {
        buttonList.add(button);
    }

    /**
     * Gets the index of the focused button.
     * @return the index of the focused button
     */
    protected int getFocusedButton() {
        return focusedButton;
    }

    /**
     * Sets the index of the button to be focused.
     * @param index the index of the button to be focused
     */
    protected void setFocusedButton(final int index) {
        this.focusedButton = index;
    }

    /**
     * Disable all the controls inside this menu.
     */
    @Override
    public void disable() { };

    /**
     * 
     * Shift the focus to the desired button.
     * 
     */
    protected class MoveAction extends AbstractAction {
        private final int direction;

        /**
         * @param direction the direction the focus will shift to.
         */
        public MoveAction(final int direction) {
            super();
            this.direction = direction;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            final int newFocus = getFocusedButton() + direction;

            if (newFocus < getButtonList().size() && newFocus >= 0) {
                setFocusedButton(newFocus);
                getButtonList().get(getFocusedButton()).requestFocus();
            }
        }
    }

    /**
     *
     * Press the focused button.
     *
     */
    protected class PressAction extends AbstractAction {
        @Override
        public void actionPerformed(final ActionEvent e) {
            getButtonList().get(focusedButton).doClick();
        }
    }

    /**
     * 
     * Shift the focus to the clicked button.
     *
     */
    protected class ClickAction implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            setFocusedButton(getButtonList().indexOf(e.getSource()));
        }
    }
}
