package it.unibo.unori.model.battle.utility;

/**
 * Utility class that contains static methods that allow to model 
 * the Battle Mode.
 *
 */
public final class AttackLogics {
    
    private static final int SHIFT = 30;
    private static final int MULT = 10;
    
    private AttackLogics() {
        //Empty private constructor, because this is an utility class
    }
    
    /**
     * This method generates the standard damage to inflict depending on
     * character's level.
     * The algorithm allows to generate a "fair" damage for a standard attack
     * depending on character's level:
     * Level 1 -> Damage = 30;
     * Level 2 -> Damage = 50;
     * Level 3 -> Damage = 90;
     * Level 4 -> Damage = 150;
     * Level 5 -> Damage = 230;
     * @param charLevel the level of the character that throws the attack.
     * @return the damage calculated by the algorithm.
     */
    public static int getStandardDamage(final int charLevel) {
        return AttackLogics.SHIFT 
               + (AttackLogics.MULT * charLevel * (charLevel - 1));
        
    }
}
