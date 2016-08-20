package it.unibo.unori.model.battle;

import it.unibo.unori.model.character.jobs.Jobs;

/**
 * This class generates MagicAttacks.
 */
public class MagicGenerator {
    
    /**
     * Method that generates a basic MagicAttack.
     * @return a basic MagicAttack.
     */
    public static MagicAttackInterface getBasic() {
        return new MagicAttack("Schiaffo", "Che schiaffo!", 
                "Non c'è nulla di magico in uno schiaffo", 0, 0, 0, 1, 10, 1);
    }
    
    /**
     * Method that generates a standard Magic Attack.
     * @param j the Attack depends on the Job.
     * @return the MagicAttack depending on the Job.
     */
    public MagicAttackInterface getStandard(final Jobs j) {
        MagicAttackInterface mag = MagicGenerator.getBasic();
        if (j == null) {
            throw new IllegalArgumentException();
        } else {
            switch (j) {
                case WARRIOR : mag = new MagicAttack("Spada Magica", 
                        "ha colpito con la spada magica!", 
                        "Spada di basso livello, molto efficace per i danni fisici",
                        7, 7, 7, 20, 8, 20); break;
                case PALADIN : mag = new MagicAttack("Lancia Magica",
                        "ha colpito con la spada magica!",
                        "Una lancia magica, efficace in tutti i campi",
                        8, 8, 8, 14, 8, 30); break;
                case MAGE : mag = new MagicAttack("Scettro magico", 
                        "ha colpito con lo scettro magico!",
                        "Uno scettro magico, non molto efficace per i danni fisici",
                        12, 12, 12, 8, 8, 40); break;
                case RANGER : mag = new MagicAttack("Frustata elettrica",
                        "ha rilasciato una frustata elettrica!",
                        "Indiana Jones sarebbe fiero di questa frusta",
                        9, 15, 6, 13, 8, 25); break;
                case COOK : mag = new MagicAttack("Mestolo magico",
                        "ha colpito con un mestolo magico!",
                        "Un mestolo forgiato da Antonino Cannavacciuolo, "
                        + "molto efficace nel tipo fuoco",
                        15, 6, 9, 12, 8, 30); break;
                case CLOWN : mag = new MagicAttack("Occhiata glaciale",
                        "ha lanciato un'occhiata glaciale!",
                        "Abilita' classica di un Clown,"
                        + "particolarmente efficace nel tipo ghiaccio",
                        10, 10, 12, 10, 8, 25); break;
                default:
                    break;
            }
        }
        return mag;
    }
    
    /**
     * Method that generates a medium Magic Attack.
     * @param j the Attack depends on the Job.
     * @return the MagicAttack depending on the Job.
     */
    public MagicAttackInterface getMedium(final Jobs j) {
        MagicAttackInterface mag = MagicGenerator.getBasic();
        if (j == null) {
            throw new IllegalArgumentException();
        } else {
            switch (j) {
                case WARRIOR : mag = new MagicAttack("Mazzata Magica",
                        "ha usato una Mazza Magica per colpire!",
                        "Una mazza chiodata che puÃ² causare gravi danni fisici",
                        10, 10, 10, 45, 7, 30); break;
                case PALADIN :mag = new MagicAttack("Urlo della Giustizia",
                        "ha usato un Urlo della Giustizia!",
                        "Un urlo utile per redimere i nemici (e per fare male)",
                        15, 15, 15, 38, 7, 38); break;
                case MAGE : mag = new MagicAttack("Magia Bianca",
                        "ha colpito con la magia bianca", 
                        "Un attacco magico per eccellenza. Efficace su tutto",
                        22, 22, 22, 30, 7, 55); break;
                case RANGER : mag = new MagicAttack("Stivali Magici",
                        "ha usato gli stivali magici per colpire!",
                        "Un paio di stivali inusuali. Molto elettrizzanti",
                        16, 26, 12, 36, 7, 35); break;
                case COOK : mag = new MagicAttack("Coltellata del Destino",
                        "ha colpito con il coltello del destino!",
                        "Un coltello che non taglia solo prosciutto,"
                        + " ma brucia anche i nemici",
                        26, 12, 16, 35, 7, 38); break;
                case CLOWN : mag = new MagicAttack("BUH!",
                        "ha spaventato magicamente il nemico!",
                        "Uno spavento che può far scendere il gelo",
                        18, 18, 22, 33, 7, 35); break;
                default:
                    break;
            }
        }
        return mag;
    }
    
    /**
     * Method that generates an advanced Magic Attack.
     * @param j the Attack depends on the Job.
     * @return the MagicAttack depending on the Job.
     */
    public MagicAttackInterface getAdvanced(final Jobs j) {
        MagicAttackInterface mag = MagicGenerator.getBasic();
        if (j == null) {
            throw new IllegalArgumentException();
        } else {
            switch (j) {
                case WARRIOR : mag = new MagicAttack("Furia Primitiva",
                        "ha colpito il nemico con furia primitiva!",
                        "Dolore e distruzione",
                        20, 20, 20, 80, 5, 60); break;
                case PALADIN :mag = new MagicAttack("Guanto del Potere",
                        "ha usato il Guanto del Potere!",
                        "Lanciare un guanto di sfida non e' mai stato cosi' doloroso",
                        30, 30, 30, 75, 5, 68); break;
                case MAGE : mag = new MagicAttack("Incantesimo Devastante",
                        "ha lanciato un incantesimo devastante!", 
                        "Una magia molto avanzata e molto potente",
                        40, 40, 40, 65, 5, 75); break;
                case RANGER : mag = new MagicAttack("Autorita'  Imponente",
                        "ha usato la tua autorita'  per colpire!",
                        "Un vero Ranger e' molto orgoglioso della sua autorita' ."
                        + " E la usa come arma",
                        30, 45, 26, 71, 5, 65); break;
                case COOK : mag = new MagicAttack("Urlo Anti-Vegano",
                        "ha lanciato un urlo Anti-Vegano!",
                        "Un urlo che disprezza i cereali, "
                        + "ed arrostisce la carne dei nemici",
                        45, 26, 30, 70, 5, 68); break;
                case CLOWN : mag = new MagicAttack("Giochiamo insieme",
                        "ha inquietato a morte il nemico!",
                        "Non c'e' nulla di piu' inquietante e glaciale"
                        + " di una richiesta come questa",
                        35, 35, 40, 68, 5, 65); break;
                default:
                    break;
            }
        }
        return mag;
    }
}
