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
        if (j != null) {
            switch (j) {
                case WARRIOR : return new MagicAttack("Spada Magica", 
                        "Hai colpito con la spada magica!", 
                        "Spada di basso livello, molto efficace per i danni fisici",
                        7, 7, 7, 20, 8, 20);
                case PALADIN : return new MagicAttack("Lancia Magica",
                        "Hai colpito con la spada magica!",
                        "Una lancia magica, efficace in tutti i campi",
                        8, 8, 8, 14, 8, 30);
                case MAGE : return new MagicAttack("Scettro magico", 
                        "Hai colpito con lo scettro magico!",
                        "Uno scettro magico, non molto efficace per i danni fisici",
                        12, 12, 12, 8, 8, 45);
                case RANGER : return new MagicAttack("Frustata elettrica",
                        "Hai rilasciato una frustata elettrica!",
                        "Indiana Jones sarebbe fiero di questa frusta",
                        9, 15, 6, 13, 8, 25);
                case COOK : return new MagicAttack("Mestolo magico",
                        "Hai colpito con un mestolo magico!",
                        "Un mestolo forgiato da Antonino Cannavacciuolo, "
                        + "molto efficace nel tipo fuoco",
                        15, 6, 9, 12, 8, 30);
                case CLOWN : return new MagicAttack("Occhiata glaciale",
                        "Hai lanciato un'occhiata glaciale!",
                        "Abilità classica di un Clown,"
                        + "particolarmente efficace nel tipo ghiaccio",
                        10, 10, 12, 10, 8, 25);
                default:
                    break;
            }
        }
        return null;
    }
}
