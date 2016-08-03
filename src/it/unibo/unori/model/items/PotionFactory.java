package it.unibo.unori.model.items;

import it.unibo.unori.model.character.Statistics;

/**
 * Factory to generate some Potions.
 *
 */
public class PotionFactory {
    
    /**
     * Method that generates a simple and standard Potion.
     * @return a simple Potion.
     */
    public Potion getStdPotion() {
        return new PotionImpl(1, Statistics.TOTALHP, "Acqua", 
                "Un rimedio tanto comune quanto inutile");
    }
    
    /**
     * Getter method that generates a Pozione.
     * @return Pozione.
     */
    public Potion getPozione() {
        return new PotionImpl(100, Statistics.TOTALHP, "Pozione",
                "Pozione in grado di rigenerare 100 HP");
    }
    
    /**
     * Getter method that generates a Intruglio.
     * @return Intruglio
     */
    public Potion getIntruglio() {
        return new PotionImpl(200, Statistics.TOTALHP, "Intruglio",
                "Intruglio magico in grado di rigenerare 200 HP");
    }
    
    /**
     * Getter method that generates a Cura Totale.
     * @return Cura Totale
     */
    public Potion getCuraTotale() {
        return new PotionImpl(100000, Statistics.TOTALHP, "Cura Totale",
                "Pozione rarissima. Riesce a rigenerare tutti gli HP del personaggio");
    }
    
    /**
     * Getter method that generates a Pozione della Vita.
     * @return Pozione della Vita
     */
    public Potion getPozioneVita() {
        return new PotionImpl(300, Statistics.TOTALHP, "Pozione della Vita",
                "Pozione che riesce anche a ridare la vita, rigenerando 30 HP");
    }
    
    /**
     * Getter method that generates a Pozione di Dio.
     * @return Pozione di Dio
     */
    public Potion getPozioneDio() {
        return new PotionImpl(100000, Statistics.TOTALHP, "Pozione di Dio",
                "Pozione leggendaria che riesce a rubare il lavoro a un Dio,"
                + " rigenerando TUTTI gli HP dim un personaggio morto");
    }
    
    /**
     * Getter method that generates a Gran Pozione.
     * @return Gran Pozione
     */
    public Potion getGranPozione() {
        return new PotionImpl(800, Statistics.TOTALHP, "Gran Pozione",
                "Rimedio piu' potente di una semplice Pozione, restituisce 800 HP");
    }
    
    /**
     * Getter method that generates a Giga Pozione.
     * @return Giga Pozione
     */
    public Potion getGigaPozione() {
        return new PotionImpl(2000, Statistics.TOTALHP, "Giga Pozione",
                "Rimedio MOLTO piu' potente di una semplice Pozione, restituisce 2000 HP");
    }
    
    /**
     * Getter method that generates a Rimedio della Nonna.
     * @return Rimedio della Nonna
     */
    public Potion getRimedioDellaNonna() {
        return new PotionImpl(600, Statistics.TOTALHP, "Rimedio della Nonna",
                "Una rara tisana rigenerante che restituisce 600 HP e cura lo Stato", true);
    }
    
    /**
     * Getter method that generates a Salute in Provetta.
     * @return Salute in Provetta
     */
    public Potion getSaluteInProvetta() {
        return new PotionImpl(200, Statistics.TOTALHP, "Salute in Provetta",
                "Un rimedio utilissimo che restituisce 200 HP e cura lo Stato", true);
    }
    
    /**
     * Getter method that generates a Pasticche Magiche.
     * @return Pasticche Magiche
     */
    public Potion getPasticcheMagiche() {
        return new PotionImpl(300, Statistics.TOTALMP, "Pasticche Magiche",
                "Pasticche che permettono di usare piu' magie");
    }
    
    /**
     * Getter method that generates a Aspirina Magica.
     * @return Aspirina Magica
     */
    public Potion getAspirinaMagica() {
        return new PotionImpl(900, Statistics.TOTALMP, "Aspirina Magica",
                "Rimedio in polvere che restituisce 900 MP");
    }
    
    /**
     * Getter method that generates a Trapianto di Mana.
     * @return Trapianto di Mana
     */
    public Potion getTrapiantoMana() {
        return new PotionImpl(2000, Statistics.TOTALMP, "Trapianto di Mana",
                "Una siringa piena di MP, operazione di livello avanzato: restituisce 2000 MP");
    }
}
