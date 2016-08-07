package it.unibo.unori.model.menu.test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.unibo.unori.model.menu.Dialogue;

/**
 * Class to test the methods of Class Dialogue.
 *
 */
public class DialogueTest {
    
    private final Dialogue toTest = new Dialogue("Dialogo di prova, devo solo aggiungere"
            + " righe per vedere se le principali funzioni della classe vanno bene"
            + "asvbdkbvkjavbadvadbsbgvbdjkdasb<fkjcbaqslbfhuwqgufvuvuefy"
            + "QUALCOSA efvuguegvquag ugauegvyge aufuygruyvhbaie"
            + "aVEFIUHRAISHFIQEHIFOIYEAQFGHIOYAIVOIYVB"
            + "adkbnvajsbgihquiwghpisuihviushiuhwihgushruighuiwhguihrwguhruh");
    
    /**
     * Method to test the principal functions of class Dialogue.
     */
    @Test
    public void testDialogue() {
        
        System.out.println(this.toTest);
        System.out.println(this.toTest.showNext());
        assertFalse(this.toTest.changeWindow());
        System.out.println(this.toTest.showNext());
        assertTrue(this.toTest.changeWindow());
        System.out.println(this.toTest.showNext());
        System.out.println(this.toTest.showNext());
        assertTrue(this.toTest.changeWindow());
        System.out.println(this.toTest.showNext());
        System.out.println(this.toTest.showNext());
        assertTrue(this.toTest.changeWindow());
        System.out.println(this.toTest.showNext());
        assertTrue(this.toTest.isOver());
    }
    
    /**
     * Method to check the Exceptions of Dialogue.
     */
    @Test
    public void testExceptions() {
        this.toTest.showNext();
        this.toTest.showNext();
        this.toTest.showNext();
        this.toTest.showNext();
        this.toTest.showNext();
        this.toTest.showNext();
        this.toTest.showNext();
        try {
            System.out.println(this.toTest.showNext());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Giusto cosi");
        } catch (Exception other) {
            fail("Other Exception!!!!");
        }
    }
    
}
