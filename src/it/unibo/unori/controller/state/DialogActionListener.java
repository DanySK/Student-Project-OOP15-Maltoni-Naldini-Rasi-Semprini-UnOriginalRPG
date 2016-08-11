package it.unibo.unori.controller.state;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.actionlistener.AbstractUnoriActionListener;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;

/**
 * ActionListener that should be used for the OK button in DialogState.
 */
public class DialogActionListener extends AbstractUnoriActionListener {
    private final ErrorSeverity severity;

    /**
     * Default constructor.
     * 
     * @param severity
     *            the severity of the error
     */
    public DialogActionListener(final ErrorSeverity severity) {
        super();
        this.severity = severity;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (severity.equals(ErrorSeverity.MINOR)) {
            this.getController().getStack().pop();
        } else if (severity.equals(ErrorSeverity.SERIUOS)) {
            this.getController().closeGame();
        }
    }

}
