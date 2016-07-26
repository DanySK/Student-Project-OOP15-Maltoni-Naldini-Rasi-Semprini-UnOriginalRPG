package it.unibo.unori.model.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to model a Dialogue window.
 */
public class Dialogue implements DialogueInterface {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4424588982670056462L;
    
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
        String s = "";
        for (final Character c : this.sentence.toCharArray()) {
            if (count >= 0 && count < Dialogue.MAX_CHARS) {
                s = s.concat(c.toString());            
            } 
            count++;
            if (count == Dialogue.MAX_CHARS) {
                count = 0;
                toShow.add(s);
                s = "";
            }
        }
        toShow.add(s);
        return toShow;
    }
    
    @Override
    public String showNext() {
        final String show = this.listRows.get(this.nextToShow);
        this.nextToShow++;
        return show;
    }
    
    @Override
    public boolean changeWindow() {
        return this.nextToShow % Dialogue.MAX_ROWS == 0; 
    }
    
    @Override
    public String getWholeDialogue() {
        return this.sentence;
    }
    
    @Override
    public int getNumChars() {
        return this.sentence.length();
    }
    
    @Override
    public List<String> getList() {
        return this.listRows;
    }
    
    /**
     * Method that generates the Dialogue printing it on Console. Just to test. To be modified.
     */
    public void generate() {
        //TODO Come vogliamo gestire il dialogo? Non so se è mio compito
        this.listRows.forEach(e -> {
            System.out.println(this.showNext());
            if (this.changeWindow()) {
                System.out.println();
            }
        });
    }
}
