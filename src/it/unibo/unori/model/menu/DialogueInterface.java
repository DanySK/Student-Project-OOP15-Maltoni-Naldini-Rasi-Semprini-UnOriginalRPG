package it.unibo.unori.model.menu;

import java.util.List;

/**
 * An Interface that models a generic Dialogue.
 * Dialogues can be created in Battle, Npcs and Characters.
 *
 */

public interface DialogueInterface {
    
    /**
     * This method gives the next String to show in the dialogue window.
     * @return the next string to show.
     */
    String showNext();
    
    /**
     * This method tells if the dialogue window is full of rows or not.
     * @return true if the rows currently showed are 2, false otherwise.
     */
    boolean changeWindow();
    
    /**
     * This getter method returns the whole sentence of the Dialogue.
     * @return the whole sentence.
     */
    String getWholeDialogue();
    
    /**
     * This getter method returns the length of the sentence in Chars.
     * @return the length of the sentence.
     */
    int getNumChars();
    
    /**
     * Getter method that returns the sentence in form of List of Strings.
     * @return a List of Strings representing the sentence.
     */
    List<String> getList();
    
}
