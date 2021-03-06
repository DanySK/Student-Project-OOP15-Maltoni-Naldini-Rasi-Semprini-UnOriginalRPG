package it.unibo.unori.controller.state;

import it.unibo.unori.controller.actionlistener.DialogActionListener;
import it.unibo.unori.view.Button;
import it.unibo.unori.view.layers.DialogLayer;

/**
 * This GameState models a state that is used to communicate with the user, commonly for showing errors before closing
 * everything.
 */
public class DialogState extends AbstractGameState {

    /**
     * Default constructor. It instantiates a new DialogState.
     * <p>
     * {@link ErrorSeverity#MINOR} makes the dialog simply close when pressing the OK button;
     * {@link ErrorSeverity#SERIUOS} makes the dialog close the whole program when pressing the OK button.
     * 
     * @param message
     *            the message to show
     * @param severity
     *            the severity of the error, which determines the behavior of the whole program after clicking the OK
     *            button
     */
    public DialogState(final String message, final ErrorSeverity severity) {
        super(new DialogLayer(message, DialogState.getButton(severity)));
    }

    /**
     * This constructor instantiates a communication dialog.
     * 
     * @param message
     *            the message to show
     */
    public DialogState(final String message) {
        this(message, ErrorSeverity.MINOR);
    }

    private static Button getButton(final ErrorSeverity severity) {
        final Button returnButton = new Button("Ok");

        returnButton.addActionListener(new DialogActionListener(severity));

        return returnButton;
    }

    /**
     * This enumeration models the error severity that made the program open the DialogState.
     */
    public enum ErrorSeverity {
        /**
         * Minor error or message, for communication purpose only.
         */
        MINOR,
        /**
         * Serious error, must close everything.
         */
        SERIUOS;
    }

}
