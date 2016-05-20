package it.unibo.unori.model.battle.utility;

import it.unibo.unori.model.battle.MagicAttack;
import it.unibo.unori.model.battle.MagicAttackInterface;

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
    public static MagicAttackInterface getStandard() {
        return new MagicAttack("Schiaffo", "Che schiaffo!", 
                "Non c'è nulla di magico in uno schiaffo", 0, 0, 0, 1, 10, 1);
    }
}
