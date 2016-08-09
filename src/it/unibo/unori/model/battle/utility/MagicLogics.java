package it.unibo.unori.model.battle.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.battle.exceptions.FailedException;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.menu.utility.Pair;

/**
 * Utility class that contains static methods that allow to model 
 * the logic of Magics, Weapons and Armors.
 *
 */
public final class MagicLogics {
    
    private static final int SHIFT = 30;
    private static final int MULT = 10;
    private static final int YOURELUCKY = 3;
    private static final int HIGHIA = 8;
    private static final int SHIFTWEAKNESS = 50;
    private static final double SHIFTNOTWEAK = 25.0;
    
    private MagicLogics() {
        //Empty private constructor, because this is an utility class
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
        Random rand = new Random();
        int luck = rand.nextInt(toCalc);
        if (accuracy >= HIGHIA) {
            return !(luck == YOURELUCKY);
        } else {
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
    private static double weakOrNot(final Character ch, final MagicAttackInterface magic) {
        if (magic.getFireAtk() == magic.getIceAtk() 
                && magic.getFireAtk() == magic.getThunderAtk()) {
            return 1 / 2;
        }
        Statistics powerMagic = getBestStat(magic.getMap()).getX();
        Map<Statistics, Integer> mapToCheck = generateMapFor(true, ch);
        Pair<Statistics, Integer> powerOpponent = getBestStat(mapToCheck);
        
        return weaknessGeneral(powerOpponent.getX(), powerMagic);
    }
    
    /**
     * Private method that, given a Map of Statistics and Integer, returns the best Stat.
     * @param map the Map to check.
     * @return a Pair of Statistic and its relative value.
     */
    private static Pair<Statistics, Integer> getBestStat(final Map<Statistics, Integer> map) {
        Pair<Statistics, Integer> power = new Pair<>(null, 0);
        for (Entry<Statistics, Integer> s : map.entrySet()) {
            int temp = map.get(s.getKey());
            if (power.getY() <= temp) {
                power = new Pair<>(s.getKey(), temp);
            }
        }
        return power;
    }
    
    /**
     * This private method generates a Map for a MagicAttack, a Weapon or an Armor.
     * Explain: given a Character, the method takes the Stats of interest to compare with
     * the stats of a MagicAttack, a Weapon or an Armor.
     * @param atkOrDef true if we are using it in context of Weapon and MagicAttack (in that case
     * we need Attack and not Defense), or Armor (in this case we need Defense) .
     * @param opp the Character from which extrapolate the Map.
     * @return the Map itself.
     */
    private static Map<Statistics, Integer> generateMapFor(final boolean atkOrDef, 
            final Character opp) {
        final Map<Statistics, Integer> map = new HashMap<>();
        if (atkOrDef)  {
            map.put(Statistics.FIREATK, opp.getFireAtk());
            map.put(Statistics.ICEATK, opp.getIceAttack());
            map.put(Statistics.THUNDERATK, opp.getThunderAttack());
        } else {
            map.put(Statistics.FIREDEF, opp.getFireDefense());
            map.put(Statistics.ICEDEF, opp.getIceDefense());
            map.put(Statistics.THUNDERDEF, opp.getThunderDefense());
        }
        return map;
    }
    
    /**
     * General method that calculates weakness baasing on two Stats.
     * @param opp best Stat of the opponent.
     * @param best best Stat of mine.
     * @return a weakness factor.
     */
    private static double weaknessGeneral(final Statistics opp, final Statistics best) {
        double weakness = 0;
            if ((opp.equals(Statistics.FIREATK) 
                    && best.equals(Statistics.ICEATK))
            || (opp.equals(Statistics.ICEATK) 
                    && best.equals(Statistics.THUNDERATK))
            || (opp.equals(Statistics.THUNDERATK) 
                    && best.equals(Statistics.FIREATK))) {
                weakness = 1 / 3;
            } else if ((opp.equals(Statistics.ICEATK) 
                    && best.equals(Statistics.FIREATK))
            || opp.equals(Statistics.FIREATK) 
                && best.equals(Statistics.THUNDERATK) 
            || opp.equals(Statistics.THUNDERATK) 
                && best.equals(Statistics.ICEATK)) {
                weakness = 2 / 3;
            } else if ((opp.equals(Statistics.FIREATK) 
                    && best.equals(Statistics.FIREATK))
            || (opp.equals(Statistics.ICEATK) 
                    && best.equals(Statistics.ICEATK))
            || (opp.equals(Statistics.THUNDERATK) 
                    && best.equals(Statistics.THUNDERATK))) {
                weakness = 1 / 2;
            }
            return weakness;
    }
    
    
    /**
     * Method supposed to calculate weakness in a MagicAttack.
     * Note: possible Exceptions are handled in BattleImpl.
     * @param att the Character that throws the MagicAttack.
     * @param opp the Character that suffers the MagicAttack.
     * @param toThrow the MagicAttack interested.
     * @return the damage to be inflicted either to the Foe or the Hero.
     * @throws FailedException if the attack fails.
     */
    public static int calculateMagic(final Character att, final Character opp,
            final MagicAttackInterface toThrow) throws FailedException {
        if (isSuccessfull(toThrow)) {
            final int diff = att.getLevel() - opp.getLevel();
            final int toMultiply = toThrow.getPhysicAtk() * MULT + SHIFT + diff;
            final Double weaknessFactor;
            weaknessFactor = weakOrNot(opp, toThrow) * toMultiply;
            return weaknessFactor.intValue();
        } else {
            throw new FailedException();
        }
    }

    /**
     * Method that calculates the value to add to the physic attack of a Weapon, calculating weakness.
     * @param w the Weapon interested.
     * @param opp the opponent.
     * @return the value of a weapon attack.
     */
    public static int toAddToWeapon(final Weapon w, final Character opp) {
        Double weakness;
        if (w.getFireAtk() == w.getIceAtk() 
                && w.getFireAtk() == w.getThunderAtk()) {
            weakness = SHIFTNOTWEAK;
        } else {
            final Statistics powerWeap = getBestStat(w.getStats()).getX();
            Map<Statistics, Integer> mapToCheck = generateMapFor(true, opp);
            Pair<Statistics, Integer> powerOpponent = getBestStat(mapToCheck);
            weakness = weaknessGeneral(powerWeap, powerOpponent.getX()) * SHIFTWEAKNESS;
        }
        return weakness.intValue() + w.getPhysicalAtk();
    }
    
    /**
     * Method that calculates the value to add to the physic defense of an Armor, calculating weakness.
     * @param ar the Armor interested.
     * @param opp the opponent.
     * @return the value of a armor resistance.
     */
    public static int toAddToArmor(final Map<ArmorPieces, Armor> ar, final Character opp) {
        Double weakness;
        final Map<Statistics, Integer> m = new HashMap<>();
        int fireDef = 0;
        int iceDef = 0;
        int thunDef = 0;
        int physDef = 0;
        for (Entry<ArmorPieces, Armor> armor : ar.entrySet()) {
            fireDef += armor.getValue().getFireDef();
            iceDef += armor.getValue().getIceDefense();
            thunDef += armor.getValue().getThunderDefense();
            physDef += armor.getValue().getPhysicalRes();
        }
        if (fireDef == iceDef && fireDef == thunDef) {
            weakness = SHIFTNOTWEAK;
        } else {
            m.put(Statistics.FIREDEF, fireDef);
            m.put(Statistics.ICEDEF, iceDef);
            m.put(Statistics.THUNDERDEF, thunDef);
            
            final Statistics powerArm = getBestStat(m).getX();
            Map<Statistics, Integer> mapToCheck = generateMapFor(false, opp);
            Pair<Statistics, Integer> powerOpponent = getBestStat(mapToCheck);
            weakness = weaknessGeneral(powerArm, powerOpponent.getX()) * SHIFTWEAKNESS;
        }
        return weakness.intValue() + physDef;
    }
    
    /**
     * This method calculates the whole damage to inflict in Battle, merging Attack and Defense.
     * @param att the Character who attacks.
     * @param opp the Character who suffers.
     * @param w the Weapon of the Character who attacks.
     * @return the amount of damage to inflict.
     */
    public static int mergeAtkAndDef(final Character att, final Character opp, final Weapon w) {
        final int atkTot = att.getAttack() + toAddToWeapon(w, att);
        if (opp instanceof Foe) {
            Double weakness;
            if (opp.getFireDefense() == opp.getIceDefense() 
                    && opp.getFireDefense() == opp.getThunderDefense()) {
                weakness = SHIFTNOTWEAK - MULT;
            } else {
                Map<Statistics, Integer> mapToCheck = generateMapFor(false, opp);
                Statistics powerOpponent = getBestStat(mapToCheck).getX();
                Map<Statistics, Integer> mapToCheckAtt = generateMapFor(true, att);
                Statistics powerAtt = getBestStat(mapToCheckAtt).getX();
                weakness = weaknessGeneral(powerAtt, powerOpponent) * (SHIFTWEAKNESS - 10);
            }
            return BattleLogics.getStandardDamage(att.getLevel(),
                    atkTot - weakness.intValue() + opp.getDefense());
        } else if (opp instanceof Hero) {
            
            return BattleLogics.getStandardDamage(att.getLevel(), atkTot 
                    - toAddToArmor(((Hero) opp).getWholeArmor(), att));
        } else {
            throw new IllegalStateException();
        }
    }
}
