package it.unibo.unori.model.battle.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.items.Armor;

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
    private static final int PERCENTAGE_MEDIUM = 12;
    private static final int PERCENTAGE_HIGH = 25;
    private static final int LOWIA = FoeImpl.MAXIA / 3;
    private static final int MEDIUMIA = (FoeImpl.MAXIA * 2) / 3;
    private static final int HIGHIA = (FoeImpl.MAXIA * 4) / 5;
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
        final int toMult = charLevel > 1 ? charLevel - 1 : 1;
        final int toRet = SHIFT + (MULT * charLevel * toMult
                + (atck * 3 * charLevel) / 2);
        if (toRet <= 0) {
            return 100;
        } else {
            return toRet;
        }
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
        int luck = rand.nextInt(LUCKPERCENTAGE);
        if (luck == YOURELUCKY) {
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
        return whosFirst(myLev, enemLev);
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
    public static Map<Hero, Integer> expAcquired(final HeroTeam squad, 
            final int mediumLevel, final int notBeaten) {
        final Map<Hero, Integer> exp = new HashMap<>();
        squad.getAllHeroes().forEach(i -> {
            final int value = ((mediumLevel * notBeaten)
                    * ((LEVELER * mediumLevel + MULT) ^ 2)
                    / ((mediumLevel + i.getLevel() + LEVELER) ^ 2 + MULT) + 1)
                    * i.getExpFactor();
            exp.put(i, value);
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
        return (getStandardDamage(charLev, atck) * (LEVELER - 2)
                + charLev * LEVELER) / 2;
    }
    
    /**
     * This method is useful to determine if a Weapon has caused a Status changing.
     * @param my the Character who is attacking.
     * @param en the Character being attacked.
     * @return the Status that the attack causes, 
     * depending on Hero and Enemy's level.
     * @throws NoWeaponException if the Hero is not holding any Weapon
     */
    public static Status causingStatus(final Character my, final Character en) 
            throws NoWeaponException {
        final Status toReturn;
        if (my instanceof Hero) {
            if (((Foe) en).getImmunity().equals(my.getWeapon().getWeaponStatus())) {
                return Status.NONE;
            } else {
                toReturn = my.getWeapon().getWeaponStatus();
            }
        } else if (my instanceof Foe) {
            for (final Armor arm : ((Hero) en).getWholeArmor().values()) {
                if (en.getWeapon().getWeaponStatus().equals(arm.getImmunity())) {
                    return Status.NONE;
                }
            }
            toReturn = en.getWeapon().getWeaponStatus();
        } else {
            throw new IllegalStateException();
        }
        final int diff = my.getLevel() - en.getLevel();
        if (diff >= DIFFERENCE_MAX) {
            return toReturn;
        } else if (diff > 2 && diff < DIFFERENCE_MAX) {
            final Random rand = new Random();
            final int luck = rand.nextInt(PERCENTAGE_MEDIUM);
            if (luck == YOURELUCKY) {
                return toReturn;
            } else {
                return Status.NONE;
            }
        } else if (diff >= 0 && diff <= 2) {
            final Random rand = new Random();
            final int luck = rand.nextInt(PERCENTAGE_HIGH);
            if (luck == YOURELUCKY) {
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
    
}
