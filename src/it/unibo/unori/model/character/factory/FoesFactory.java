package it.unibo.unori.model.character.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.battle.utility.MagicGenerator;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponFactory;
import it.unibo.unori.model.items.WeaponImpl;
import it.unibo.unori.model.menu.utility.Pair;

/**
 * Utility class to generate Statistics for a Foe.
 */
public final class FoesFactory {
    
    private static final int SHIFT = 30;
    private static final int LUCKFORSTATS = 4;
    private static final int LUCKMED = 6;
    private static final int MEDIUMIA = 4;
    private static final int HIGHIA = 7;

    private FoesFactory() {
        //Empty private constructor, because this is a final utility class
    }
    
    private static int getBasicOf(final Statistics s) {
        return getBasicStats().get(s);
    }
    
    /**
     * This method gives the basic Stats for a Foe of ia 1.
     * @return a Map of Statistics.
     */
    public static Map<Statistics, Integer> getBasicStats() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, 1500);
        m.put(Statistics.TOTALMP, 700);
        m.put(Statistics.SPEED, 1200);
        m.put(Statistics.FIREATK, 1000);
        m.put(Statistics.FIREDEF, 750);
        m.put(Statistics.THUNDERATK, 1000);
        m.put(Statistics.THUNDERDEF, 750);
        m.put(Statistics.ICEATK, 1000);
        m.put(Statistics.ICEDEF, 750);
        m.put(Statistics.PHYSICATK, 1000);
        m.put(Statistics.PHYSICDEF, 1000);
        m.put(Statistics.EXPFACTOR, 0);
        return m;
    }
    
    /**
     * Method that returns a basic Weapon.
     * @return a standard Weapon for a Foe.
     */
    public static Weapon getBasicWeap() {
        return WeaponImpl.FISTS;
    }
    
    /**
     * Method that returns a basic Magic.
     * @return a standard magic for the Foe.
     */
    public static MagicAttackInterface getBasicMag() {
        return MagicGenerator.getBasic();
    }
    
    /**
     * This method gives the stats of a Foe grown depending on his ia.
     * @param ia the ia of the Foe.
     * @return a Map of Stats
     */
    public static Map<Statistics, Integer> getGrowingStats(final int ia) {
        final Map<Statistics, Integer> m = new HashMap<>();
        final int growth = (ia ^ 2) * 10 + SHIFT;
        Pair<Statistics, Statistics> best = new Pair<>(Statistics.PHYSICATK, Statistics.PHYSICDEF);
        final Random rand = new Random();
        final int luck = rand.nextInt(LUCKFORSTATS);
        switch (luck) {
        case 0 : best = new Pair<>(Statistics.FIREATK, Statistics.FIREDEF);
            break;
        case 1 : best = new Pair<>(Statistics.ICEATK, Statistics.ICEDEF);
            break;
        case 2 :  best = new Pair<>(Statistics.THUNDERATK, Statistics.THUNDERDEF);
            break;
        case 3 : best = new Pair<>(Statistics.PHYSICATK, Statistics.PHYSICDEF);
            break;
        default : break;
        }
        
        m.put(Statistics.TOTALHP, getBasicOf(Statistics.TOTALHP) + growth);
        m.put(Statistics.TOTALMP, getBasicOf(Statistics.TOTALMP) + growth);
        m.put(Statistics.SPEED, getBasicOf(Statistics.SPEED) + growth);
        m.put(Statistics.FIREATK, getBasicOf(Statistics.FIREATK) + growth);
        m.put(Statistics.FIREDEF, getBasicOf(Statistics.FIREDEF) + growth);
        m.put(Statistics.THUNDERATK, getBasicOf(Statistics.THUNDERATK) + growth);
        m.put(Statistics.THUNDERDEF, getBasicOf(Statistics.THUNDERDEF) + growth);
        m.put(Statistics.ICEATK, getBasicOf(Statistics.ICEATK) + growth);
        m.put(Statistics.ICEDEF, getBasicOf(Statistics.ICEDEF) + growth);
        m.put(Statistics.PHYSICATK, getBasicOf(Statistics.PHYSICATK) + growth);
        m.put(Statistics.PHYSICDEF, getBasicOf(Statistics.PHYSICDEF) + growth);
        m.put(Statistics.EXPFACTOR, 0);
        for (final Entry<Statistics, Integer> e : m.entrySet()) {
            if (best.getX().equals(e.getKey())) {
                m.replace(e.getKey(), e.getValue() + SHIFT);
            }
            if (best.getY().equals(e.getKey())) {
                m.replace(e.getKey(), e.getValue() + SHIFT);
            }
        }
        return m;
    }
    
    /**
     * Method that gives the Weapon of a Foe depending on his IA.
     * @param ia the IA of the Foe.
     * @return a Weapon for the Foe.
     */
    public static Weapon getWeaponGrown(final int ia) {
        Weapon w = WeaponImpl.FISTS;
        if (ia > 0 && ia <= 3) {
            final Random randLow = new Random();
            final int luckLow = randLow.nextInt(LUCKFORSTATS);
            switch (luckLow) {
            case 0 : w = new WeaponFactory().getStdSword();
                break;
            case 1 : w = new WeaponFactory().getPugnale();
                break;
            case 2 :  w = new WeaponFactory().getClava();
                break;
            case 3 : w = new WeaponFactory().getBalestra();
                break;
            default : break;
            }
        } else if (ia > 3 && ia <= 7) {
            final Random randMed = new Random();
            final int luckMed = randMed.nextInt(LUCKMED);
            switch (luckMed) {
            case 0 : w = new WeaponFactory().getMaledizione();
                break;
            case 1 : w = new WeaponFactory().getChiodo();
                break;
            case 2 :  w = new WeaponFactory().getLancia();
                break;
            case 3 : w = new WeaponFactory().getOcarina();
                break;
            case 4 : w = new WeaponFactory().getFionda();
                break;
            case 5 : w = new WeaponFactory().getCannone();
                break;
            default : break;
            }
        } else {
            final Random randHi = new Random();
            final int luckHi = randHi.nextInt(LUCKFORSTATS + 1);
            switch (luckHi) {
            case 0 : w = new WeaponFactory().getMazza();
                break;
            case 1 : w = new WeaponFactory().getLanciafiamme();
                break;
            case 2 :  w = new WeaponFactory().getCerbottana();
                break;
            case 3 : w = new WeaponFactory().getSpadaMistica();
                break;
            case 4 : w = new WeaponFactory().getColtre();
                break;
            default : break;
            }
        }
        return w;
    }
    
    /**
     * This method gives a list of Magic attacks, depending on the ia of the Foe.
     * @param ia the ia of the interested Foe.
     * @return the List of magics for a Foe.
     */
    public static List<MagicAttackInterface> getGrownMagics(final int ia) {
        final List<MagicAttackInterface> l = new ArrayList<>();
        if (ia > 0 && ia <= MEDIUMIA) {
            for (final Jobs j : Jobs.values()) {
                 l.add(MagicGenerator.getStandard(j));
            }
        } else if (ia > MEDIUMIA && ia <= HIGHIA) {
            for (final Jobs j : Jobs.values()) {
                l.add(MagicGenerator.getMedium(j));
           }
        } else {
            for (final Jobs j : Jobs.values()) {
                l.add(MagicGenerator.getAdvanced(j));
           }  
        }
        return l;
    }
}
