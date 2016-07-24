package it.unibo.unori.model.menu.test;


import org.junit.Test;

import it.unibo.unori.model.menu.Dialogue;

/**
 * Class to test the methods of Class Dialogue.
 *
 */
public class DialogueTest {
    
    private final Dialogue toTest = new Dialogue("Dialogo di prova, devo solo aggiungere"
            + " righe per vedere se le principali funzioni della classe vanno bene"
            + "asvbdkbvkjavbadvadbsbgvbdjkdasb<fkjcbaqslbfhuwqgufvuvuefy "
            + "asvbahefvuguegvquagugauegvygeaufuygruyvhbaie "
            + "aVEFIUHRAISHFIQEHIFOIYEAQFGHIOYAIVOIYVB"
            + "adkbnvajsbgihquiwghpisuihviushiuhwihgushruighuiwhguihrwguhruh");
    
    private final int chars = this.toTest.getWholeDialogue().length();
    
    /**
     * Method to test the principal functions of class Dialogue.
     */
    @Test
    public void testDialogue() {
        
        this.toTest.generate();
        System.out.println("" + this.chars);
        
    }
    
}
