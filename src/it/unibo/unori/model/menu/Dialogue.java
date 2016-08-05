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
        boolean longWord = false;
        String[] splitted = this.sentence.split(" ");
        for (final String str : splitted) {
            if (count >= 0 && count < Dialogue.MAX_CHARS) {
                if (str.length() + count < Dialogue.MAX_CHARS) {
                    if (longWord) {
                        s = s.concat(" ");
                    }
                    s = s.concat(str + " ");
                    count += str.length() + 1;
                } 
                if (str.length() + count == Dialogue.MAX_CHARS) {
                    s = s.concat(str);
                    count = 0;
                    toShow.add(s);
                    s = "";
                } else if (str.length() + count > Dialogue.MAX_CHARS) {
                    if (str.length() + 1 <= MAX_CHARS) {
                        toShow.add(s);
                        s = str + " ";
                        count = str.length() + 1;
                    } else {
                        for (Character ch : str.toCharArray()) {
                            longWord = true;
                            if (count < Dialogue.MAX_CHARS) {
                                s = s.concat(ch.toString());
                                count++;
                            }
                            if (count == Dialogue.MAX_CHARS) {
                                count = 0;
                                toShow.add(s);
                                s = "";
                            }
                            
                        }
                    }
                }
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
    
    @Override
    public boolean isOver() {
        return this.nextToShow == this.listRows.size();
    }
    
    /**
     * Method that generates the Dialogue printing it on Console.
     * For Test purposes.
     */
    public void generate() {
        this.listRows.forEach(e -> {
            System.out.println(this.showNext());
            if (this.changeWindow()) {
                System.out.println();
            }
        });
    }
}
