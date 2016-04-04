package it.unibo.unori.model.battle.utility;

import java.util.ArrayList;
import java.util.Random;

/**
 * Utility class that contains static methods that allow to model 
 * the Battle Mode.
 *
 */
public final class AttackLogics {

    private static final int SHIFT = 30;
    private static final int MULT = 10;
    private static final int LUCKPERCENTAGE = 50;
    private static final int YOURELUCKY = 3;

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

    /**
     * This method tells me whose the first move is in the battle turn.
     * The magic numbers LUCKPERCENTAGE and YOURELUCKY help to implement
     * a sort of lucky possibility for my Character to move first even if
     * his speed is lower than enemy's.
     * Luck Percentage is 5%: if my random number (between 0 and 49)
     * equals the number 3, then I'm allowed to move first anyways.
     * Else, if I'm not lucky, the Character with highest speed moves first.
     * @param myV my Character's speed.
     * @param enemV enemy's speed.
     * @return true if I may move first. False otherwise.
     */
    public static boolean whosFirst(final int myV, final int enemV) {
        Random rand = new Random();
        int luck = rand.nextInt(AttackLogics.LUCKPERCENTAGE);
        if (luck == AttackLogics.YOURELUCKY) {
            return true;
        } else {
            return myV > enemV;
        }
    }

    /**
     * This method calculates the experience points acquired by each
     * Character of my team at the end of the battle, depending on 
     * my Characters' levels and enemies' ones.
     * @param charLev my team's levels presented as a List of Integer.
     * @param enemLev enemies' levels presented as a List of Integer.
     * @return the List of the experience points acquired by each member
     * of my team.
     */
    public static ArrayList<Integer> expAcquired(
     final ArrayList<Integer> charLev, final ArrayList<Integer> enemLev) {
        charLev.forEach(i -> {
            //TODO experience algorithm.
        });
        return null;
    }
}
