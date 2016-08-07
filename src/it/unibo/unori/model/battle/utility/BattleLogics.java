package it.unibo.unori.model.battle.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.battle.exceptions.FailedException;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.menu.utility.Pair;

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
    private static final int LOWIA = 3;
    private static final int MEDIUMIA = 6;
    private static final int HIGHIA = 8;
    private static final int TURNSFORMEDIUMIA = 7;
    private static final int TURNSFORHIGHIA = 5;
    private static final int MAGICFILL = 30;
    private static final int NORMALFILL = 20;
    private static final int SHIFTLEVELEQUAL = 5;
    private static final int SHIFTLEVELLOWER = 10;
    

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
     * If the two speed levels are equal, both of the contenders have the same (fifty-fifty)
     * probability to gain the first move.
     * Luck Percentage is 5%: if my random number (between 0 and 49)
     * equals the number 3, then I'm allowed to move first anyways.
     * Else, if I'm not lucky, the Character with highest speed moves first.
     * @param myV my Character's speed.
     * @param enemV enemy's speed.
     * @return true if I may move first. False otherwise.
     */
    public static boolean whosFirst(final int myV, final int enemV) {
        Random rand = new Random();
        int luck = rand.nextInt(BattleLogics.LUCKPERCENTAGE);
        if (luck == BattleLogics.YOURELUCKY) {
            return true;
        } else if (myV == enemV) {
            rand = new Random();
            luck = rand.nextInt(2);
            return luck == 0;
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
     * This method is useful to determine if a Weapon has caused a Status changing.
     * @param my the Hero who is attacking.
     * @param en the Enemy being attacked.
     * @param who a boolean variable: true if the Status is inflicted to a Foe, false if it is
     * inflicted to a Hero.
     * @return the Status that the attack causes, 
     * depending on Hero and Enemy's level.
     * @throws NoWeaponException if the Hero is not holding any Weapon
     */
    public static Status causingStatus(final Hero my, final Foe en, final boolean who) 
            throws NoWeaponException {
        final int diff;
        final Status toReturn;
        if (who) {
             diff = my.getLevel() - en.getLevel();
             toReturn = my.getWeapon().getWeaponStatus();
        } else {
            diff = en.getLevel() - my.getLevel();
            toReturn = en.getWeapon().getWeaponStatus();
        }
        if (diff >= BattleLogics.DIFFERENCE_MAX) {
            return toReturn;
        } else if (diff > 2 && diff < BattleLogics.DIFFERENCE_MAX) {
            final Random rand = new Random();
            final int luck = rand.nextInt(BattleLogics.PERCENTAGE_MEDIUM);
            if (luck == BattleLogics.YOURELUCKY) {
                return toReturn;
            } else {
                return Status.NONE;
            }
        } else if (diff >= 0 && diff <= 2) {
            final Random rand = new Random();
            final int luck = rand.nextInt(BattleLogics.LUCKPERCENTAGE);
            if (luck == BattleLogics.YOURELUCKY) {
                return toReturn;
            } else {
                return Status.NONE;
            }
        }
        return Status.NONE;
    }
    
    /**
     * Method that returns the amount of MP that the method restoreInBattle() of Foe must use.
     * @param f the Foe interested.
     * @return the amount of MP that the Foe can restore by using restoreInBattle(),
     * depending on his IA.
     */
    public static int mPToRestoreForFoe(final Foe f) {
        final int toReturn;
        if (f.getIA() <= LOWIA) {
            toReturn = (f.getTotalMP() - f.getCurrentMP()) / 4;
        } else if (f.getIA() > LOWIA && f.getIA() <= MEDIUMIA) {
            toReturn = (f.getTotalMP() - f.getCurrentMP()) / 3;
        } else if (f.getIA() > MEDIUMIA && f.getIA() <= HIGHIA) {
            toReturn = (f.getTotalMP() - f.getCurrentMP()) / 2;
        } else {
            toReturn = f.getTotalMP() - f.getCurrentMP();
        }
        return toReturn;
    }
    
    /**
     * Method that gives to a Foe a sort of Intelligence.
     * The method calculates weather the Foe can restore his Statistics or not,
     * depending on his IA and on the turns that he has already played.
     * Plus, there's a luck percentage that can allow a low-leveled Foe to use a
     * restore when his IA shouldn't let him to. This percentage is calculated by method
     * whosFirst(), with, as parameters, two integers (first lower than second).
     * @param f the Foe interested.
     * @param nOfTurnsPlayed the number of turns that he is already played
     * @return true if the Foe can Restore a Statistic in Battle, false otherwise.
     */
    public static boolean canFoeRestore(final Foe f, final int nOfTurnsPlayed) {
        if (whosFirst(LOWIA, MEDIUMIA)) {
            return true;
        } else {
            if (f.getIA() <= LOWIA) {
                return  nOfTurnsPlayed >= 10;
            } else if (f.getIA() > LOWIA && f.getIA() <= MEDIUMIA) {
                return nOfTurnsPlayed >= TURNSFORMEDIUMIA;
            } else if (f.getIA() > MEDIUMIA && f.getIA() <= HIGHIA) {
                return nOfTurnsPlayed >= TURNSFORHIGHIA;
            } else {
                return nOfTurnsPlayed >= 3;
            }
        }
    }
    
    /**
     * Method to calculate how much the special bar of an Hero must be filled after each attack.
     * This value depends on the type of the attack (magic or standard) and on the difference between
     * the levels of the two opponents.
     * @param f the Foe against which the Hero throws an attack.
     * @param isMagic true if the attack is Magic, false otherwise.
     * @param my the Hero whose special attack bar is to be filled.
     * @return the value to fill the special bar.
     */
    public static int toFillSpecialBar(final Foe f, final boolean isMagic, final Hero my) {
        final int toReturn;
        if (isMagic) {
            toReturn = MAGICFILL;
        } else {
            toReturn = NORMALFILL;
        }
        if (my.getLevel() - f.getLevel() > 0) {
            return toReturn;
        } else if (my.getLevel() == f.getLevel()) {
            return toReturn + SHIFTLEVELEQUAL;
        } else {
            return toReturn + SHIFTLEVELLOWER;
        }
    }
    
    /**
     * Method supposed to calculate weakness in a MagicAttack.
     * Note: possible Exceptions are handled in BattleImpl.
     * @param f the Foe that throws or  suffers the MagicAttack.
     * @param my the Hero that throws or suffers the MagicAttack.
     * @param who true if the Hero throws the attack, false if the Foe throws.
     * @param toThrow the MagicAttack interested.
     * @return the damage to be inflicted either to the Foe or the Hero.
     * @throws FailedException if the attack fails.
     */
    public static int calculateWeakness(final Foe f, final Hero my, final boolean who,
            final MagicAttackInterface toThrow) throws FailedException {
        if (isSuccessfull(toThrow)) {
            Pair<Statistics, Integer> powerMagic = new Pair<>(Statistics.SPEED, 0);
            final double weaknessFactor;
            for (Statistics s : toThrow.getMap().keySet()) {
                int temp = toThrow.getMap().get(s);
                if (powerMagic.getY() <= temp) {
                    powerMagic = new Pair<>(s, temp);
                }
            }
            if (who) {
                weaknessFactor = weakOrNot(f, powerMagic.getX());
                return 0; 
            } else {
                weaknessFactor = weakOrNot(my, powerMagic.getX());
                return 0;
            }
        } else {
            throw new FailedException();
        }
    }
    
    /**
     * Accuracy is calculated in this way:
     * If the accuracy of the attack is x, then the Character has 2/x+1 probability
     * to fail the attack. Except from the case in which the accuracy equals 8: in that
     * case the probability to fail is 1/x+1.
     * @param m the MagicAttack to throw.
     * @return true if the attack does not fail, false otherwise.
     */
    private static boolean isSuccessfull(final MagicAttackInterface m) {
        final int accuracy = m.getAccuracy();
        final int toCalc = accuracy + 1;
        if (accuracy >= HIGHIA) {
            Random rand = new Random();
            int luck = rand.nextInt(toCalc);
            return !(luck == YOURELUCKY);
        } else if (accuracy >= MEDIUMIA && accuracy < HIGHIA) {
            Random rand = new Random();
            int luck = rand.nextInt(toCalc);
            return !(luck == YOURELUCKY || luck == 0);
        } else {
            Random rand = new Random();
            int luck = rand.nextInt(toCalc);
            return !(luck == YOURELUCKY || luck == 0);
        }
    }
    
    /**
     * Private method which calculates a weakness factor based on 
     * the most Powerful stat of the magic to throw and of the opponent.
     * @param ch the opponent (Hero or Foe).
     * @param powerMagic the most powerful statistic of the MagicAttack.
     * @return a weakness factor.
     */
    private static double weakOrNot(final Character ch, final Statistics powerMagic) {
        Pair<Statistics, Integer> powerOpponent = new Pair<>(Statistics.SPEED, 0);
        double weakness = 0;
        Map<Statistics, Integer> mapToCheck = new HashMap<>();
        mapToCheck.put(Statistics.FIREATK, ch.getFireAtk());
        mapToCheck.put(Statistics.ICEATK, ch.getIceAttack());
        mapToCheck.put(Statistics.THUNDERATK, ch.getThunderAttack());
        
        for (Statistics s : mapToCheck.keySet()) {
            int temp = ch.getStatistics().get(s);
            if (powerOpponent.getY() <= temp) {
                powerOpponent = new Pair<>(s, temp);
            }
        }
        if ((powerOpponent.getX().equals(Statistics.FIREATK) 
                        && powerMagic.equals(Statistics.ICEATK))
                || (powerOpponent.getX().equals(Statistics.ICEATK) 
                        && powerMagic.equals(Statistics.THUNDERATK))
                || (powerOpponent.getX().equals(Statistics.THUNDERATK) 
                        && powerMagic.equals(Statistics.FIREATK))) {
            weakness = 1 / 3;
        } else if ((powerOpponent.getX().equals(Statistics.ICEATK) 
                && powerMagic.equals(Statistics.FIREATK))
                || powerOpponent.getX().equals(Statistics.FIREATK) 
                && powerMagic.equals(Statistics.THUNDERATK) 
                || powerOpponent.getX().equals(Statistics.THUNDERATK) 
                && powerMagic.equals(Statistics.ICEATK)) {
            weakness = 2 / 3;
        } else if ((powerOpponent.getX().equals(Statistics.FIREATK) 
                && powerMagic.equals(Statistics.FIREATK))
                || (powerOpponent.getX().equals(Statistics.ICEATK) 
                        && powerMagic.equals(Statistics.ICEATK))
                || (powerOpponent.getX().equals(Statistics.THUNDERATK) 
                        && powerMagic.equals(Statistics.THUNDERATK))) {
            weakness = 1 / 2;
        }
        return weakness;
    }
}
