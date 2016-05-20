package it.unibo.unori.model.battle;

/**
 * This class generates MagicAttacks.
 */
public class MagicAttackGenerator {
    
    /**
     * Method that generates a basic MagicAttack.
     * @return a basic MagicAttack.
     */
    public MagicAttackInterface getStandard() {
        return new MagicAttack("Schiaffo", "Che schiaffo!", 
                "Non c'è nulla di magico in uno schiaffo", 0, 0, 0, 1, 10, 1);
    }
}
