package it.unibo.unori.model.battle.utility;

import it.unibo.unori.model.battle.MagicAttack;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.jobs.Jobs;

/**
 * This class generates MagicAttacks.
 */
public final class MagicAttackGenerator {
    
    private MagicAttackGenerator() {
        //Empty private constructor because this is an utility class.
    }
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
    public static MagicAttackInterface getStandard(final Jobs j) {
        MagicAttackInterface mag = MagicAttackGenerator.getBasic();
        if (j == null) {
            throw new IllegalArgumentException();
        } else {
            switch (j) {
                case WARRIOR : mag = new MagicAttack("Spada Magica", 
                        "Hai colpito con la spada magica!", 
                        "Spada di basso livello, molto efficace per i danni fisici",
                        7, 7, 7, 20, 8, 20); break;
                case PALADIN : mag = new MagicAttack("Lancia Magica",
                        "Hai colpito con la spada magica!",
                        "Una lancia magica, efficace in tutti i campi",
                        8, 8, 8, 14, 8, 30); break;
                case MAGE : mag = new MagicAttack("Scettro magico", 
                        "Hai colpito con lo scettro magico!",
                        "Uno scettro magico, non molto efficace per i danni fisici",
                        12, 12, 12, 8, 8, 40); break;
                case RANGER : mag = new MagicAttack("Frustata elettrica",
                        "Hai rilasciato una frustata elettrica!",
                        "Indiana Jones sarebbe fiero di questa frusta",
                        9, 15, 6, 13, 8, 25); break;
                case COOK : mag = new MagicAttack("Mestolo magico",
                        "Hai colpito con un mestolo magico!",
                        "Un mestolo forgiato da Antonino Cannavacciuolo, "
                        + "molto efficace nel tipo fuoco",
                        15, 6, 9, 12, 8, 30); break;
                case CLOWN : mag = new MagicAttack("Occhiata glaciale",
                        "Hai lanciato un'occhiata glaciale!",
                        "Abilità classica di un Clown,"
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
    public static MagicAttackInterface getMedium(final Jobs j) {
        MagicAttackInterface mag = MagicAttackGenerator.getBasic();
        if (j == null) {
            throw new IllegalArgumentException();
        } else {
            switch (j) {
                case WARRIOR : mag = new MagicAttack("Mazzata Magica",
                        "Hai usato una Mazza Magica per colpire!",
                        "Una mazza chiodata che può causare gravi danni fisici",
                        10, 10, 10, 45, 7, 30); break;
                case PALADIN :mag = new MagicAttack("Urlo della Giustizia",
                        "Hai usato un Urlo della Giustizia!",
                        "Un urlo utile per redimere i nemici (e per fare male)",
                        15, 15, 15, 38, 7, 38); break;
                case MAGE : mag = new MagicAttack("Magia Bianca",
                        "Hai colpito con la magia bianca", 
                        "Un attacco magico per eccellenza. Efficace su tutto",
                        22, 22, 22, 30, 7, 55); break;
                case RANGER : mag = new MagicAttack("Stivali Magici",
                        "Hai usato gli stivali magici per colpire!",
                        "Un paio di stivali inusuali. Molto elettrizzanti",
                        16, 26, 12, 36, 7, 35); break;
                case COOK : mag = new MagicAttack("Coltellata del Destino",
                        "Hai colpito con il coltello del destino!",
                        "Un coltello che non taglia solo prosciutto,"
                        + " ma brucia anche i nemici",
                        26, 12, 16, 35, 7, 38); break;
                case CLOWN : mag = new MagicAttack("BUH!",
                        "Hai spaventato magicamente il nemico!",
                        "Uno spavento che può far scendere il gelo",
                        18, 18, 22, 33, 7, 35); break;
                default:
                    break;
            }
        }
        return mag;
    }
}
