package it.unibo.unori.model.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to model a dialogue window.
 */
public class Dialogue extends DummyMenu {
    
    private static final int MAX_ROWS = 2;
    private static final int MAX_CHARS = 50;
    private final String sentence;
    private int nextToShow;
    private final List<String> listRows;
    
    /**
     * Standard constructor.
     * @param toShow the string to be shown.
     */
    public Dialogue(final String toShow) {
        this.sentence = toShow;
        this.listRows = this.showRows();
        this.nextToShow = 0;
    }
    
    /**
     * This method returns the sentence in form of a List of Strings.
     * @return the sentence in form of a List of Strings.
     */
    private List<String> showRows() {
        final List<String> toShow = new ArrayList<>();
        int count = 0;
        for (final Character c : this.sentence.toCharArray()) {
            String s = c.toString();
            if (count > 0 && count < Dialogue.MAX_CHARS) {
                s = s.concat(c.toString());            
            } 
            count++;
            if (count == Dialogue.MAX_CHARS || count == this.sentence.length()) {
                count = 0;
                toShow.add(s);
            }
        }
        return toShow;
    }
    
    /**
     * This method gives the next String to show in the dialogue window.
     * @return the next string to show.
     */
    public String showNext() {
        final String show = this.listRows.get(this.nextToShow);
        this.nextToShow++;
        return show;
    }
    
    /**
     * This method tells if the dialogue window is full of rows or not.
     * @return true if the rows currently showed are 2, false otherwise.
     */
    public boolean changeWindow() {
        return this.nextToShow % Dialogue.MAX_ROWS == 0; 
    }
}
