package it.unibo.unori.model.battle.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.exceptions.NoWeaponException;

/**
 * Utility class that contains static methods that allow to model 
 * the Battle Mode.
 *
 */
public final class BattleLogics {

    private static final int SHIFT = 30;
    private static final int MULT = 10;
    private static final int LUCKPERCENTAGE = 50;
    private static final int YOURELUCKY = 3;
    private static final int LEVELER = 5;
    private static final int DIFFERENCE_MAX = 4;
    private static final int PERCENTAGE_MEDIUM = 25;

    private BattleLogics() {
        //Empty private constructor, because this is an utility class
    }

    /**
     * This method generates the standard damage to inflict depending on
     * character's level.
     * The algorithm allows to generate a "fair" damage for a standard attack
     * depending on character's level.
     * The damage is improved by Character's Attack statistic. 
     * @param charLevel the level of the Character that throws the attack.
     * @param atck the Attack Statistic of the Character.
     * @return the damage calculated by the algorithm.
     */
    public static int getStandardDamage(final int charLevel, final int atck) {
        return BattleLogics.SHIFT 
               + (BattleLogics.MULT * charLevel * (charLevel - 1)
               + (atck * 3 * charLevel) / 2);
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
        final Random rand = new Random();
        final int luck = rand.nextInt(BattleLogics.LUCKPERCENTAGE);
        if (luck == BattleLogics.YOURELUCKY) {
            return true;
        } else {
            return myV > enemV;
        }
    }
    
    /**
     * This method tells me if I can escape or not.
     * Its logic is the same as the method {@link #whosFirst(int, int)}.
     * @param myLev the level of my Character.
     * @param enemLev Enemy's level.
     * @return true if I can escape. False otherwise
     */
    public static boolean canEscape(final int myLev, final int enemLev) {
        return BattleLogics.whosFirst(myLev, enemLev);
    }

    /**
     * This method calculates the experience points acquired by each
     * Character of my team at the end of the battle, depending on 
     * my Characters' levels, enemies' ones and other parameters.
     * @param squad my team.
     * @param mediumLevel the average level of enemies in Battle.
     * @param notBeaten the number of the members of my team that haven't been
     * beaten in Battle.
     * @return the List of the experience points acquired by each member
     * of my team.
     */
    public static List<Integer> expAcquired(final HeroTeam squad, 
            final int mediumLevel, final int notBeaten) {
        
        final List<Integer> exp = new ArrayList<>();
        squad.getAllHeroes().forEach(i -> {
            exp.add(((mediumLevel / BattleLogics.LEVELER * notBeaten)
                    * ((2 * mediumLevel + BattleLogics.MULT) ^ 2)
                    / ((mediumLevel + i.getLevel() + BattleLogics.MULT) ^ 2) + 1)
                    * i.getExpFactor());
        });
        return exp;
    }
    
    /**
     * This method calculates the damage to inflict to an enemy by throwing
     * a special attack.
     * The damage is obtained by doubling the standard damage and adding
     * the Character's level multiplied by 10.
     * @param charLev the level of my Character.
     * @param atck the Attack Statistic of the Character.
     * @return the damage of the special attack.
     */
    public static int specialAttackCalc(final int charLev, final int atck) {
        return BattleLogics.getStandardDamage(charLev, atck) * 2
                + charLev * BattleLogics.MULT;
    }
    
    /**
     * This method calculates the damage to inflict to an enemy by throwing 
     * a magic attack.
     * @param h the Hero involved in Battle.
     * @param toThrow the Magic Attack that is supposed to be thrown.
     * @return the damage of the magic attack.
     */
    public static int magicAttackCalc(final Hero h, 
            final MagicAttackInterface toThrow) {
        //TODO
        return 0;
    }
    
    /**
     * This method is useful to determine if a Weapon has caused a Status changing.
     * @param my the Hero who is attacking.
     * @param en the Enemy being attacked.
     * @return the Status that the attack causes, 
     * depending on Hero and Enemy's level.
     * @throws NoWeaponException if the Hero is not holding any Weapon
     */
    public static Status causingStatus(final Hero my, final Foe en) 
            throws NoWeaponException {
        final int diff = my.getLevel() - en.getLevel();
        if (diff >= BattleLogics.DIFFERENCE_MAX) {
            return my.getWeapon().getWeaponStatus();
        } else if (diff > 2 && diff < BattleLogics.DIFFERENCE_MAX) {
            final Random rand = new Random();
            final int luck = rand.nextInt(BattleLogics.PERCENTAGE_MEDIUM);
            if (luck == BattleLogics.YOURELUCKY) {
                return my.getWeapon().getWeaponStatus();
            } else {
                return Status.NONE;
            }
        } else if (diff >= 0 && diff <= 2) {
            final Random rand = new Random();
            final int luck = rand.nextInt(BattleLogics.LUCKPERCENTAGE);
            if (luck == BattleLogics.YOURELUCKY) {
                return my.getWeapon().getWeaponStatus();
            } else {
                return Status.NONE;
            }
        }
        return Status.NONE;
    }
}
