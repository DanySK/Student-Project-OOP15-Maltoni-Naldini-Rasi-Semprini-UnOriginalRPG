package it.unibo.unori.model.character.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponFactory;
import it.unibo.unori.model.items.WeaponImpl;
import it.unibo.unori.model.menu.utility.Pair;

public final class FoesFactory {
    
    private static final int SHIFT = 30;
    private static final int LUCKFORSTATS = 4;
    private static final int LUCKMED = 6;

    private FoesFactory() {
        //Empty private constructor, because this is a final utility class
    }
    
    private int getBasicOf(Statistics s) {
        return this.getBasicStats().getY().get(s);
    }
    
    public Pair<Weapon, Map<Statistics, Integer>> getBasicStats() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, 2000);
        m.put(Statistics.TOTALMP, 700);
        m.put(Statistics.SPEED, 1200);
        m.put(Statistics.FIREATK, 1000);
        m.put(Statistics.FIREDEF, 750);
        m.put(Statistics.THUNDERATK, 1000);
        m.put(Statistics.THUNDERDEF, 750);
        m.put(Statistics.ICEATK, 1000);
        m.put(Statistics.ICEDEF, 750);
        m.put(Statistics.PHYSICATK, 2000);
        m.put(Statistics.PHYSICDEF, 2000);
        return new Pair<>(WeaponImpl.FISTS, m);
    }
    
    public Pair<Weapon, Map<Statistics, Integer>> getGrowingStats(final int ia) {
        final Map<Statistics, Integer> m = new HashMap<>();
        final int growth = (ia ^ 2) * 10 + SHIFT;
        Pair<Statistics, Statistics> best = new Pair<>(Statistics.PHYSICATK, Statistics.PHYSICDEF);
        Weapon w = WeaponImpl.FISTS;
        Random rand = new Random();
        int luck = rand.nextInt(LUCKFORSTATS);
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
        
        if (ia > 0 && ia <= 3) {
            Random randLow = new Random();
            int luckLow = randLow.nextInt(LUCKFORSTATS);
            switch (luckLow) {
            case 0 : w = WeaponFactory.getStdSword();
                break;
            case 1 : w = WeaponFactory.getPugnale();
                break;
            case 2 :  w = WeaponFactory.getClava();
                break;
            case 3 : w = WeaponFactory.getBalestra();
                break;
            default : break;
            }
        } else if (ia > 3 && ia <= 7) {
            Random randMed = new Random();
            int luckMed = randMed.nextInt(LUCKMED);
            switch (luckMed) {
            case 0 : w = WeaponFactory.getMaledizione();
                break;
            case 1 : w = WeaponFactory.getChiodo();
                break;
            case 2 :  w = WeaponFactory.getLancia();
                break;
            case 3 : w = WeaponFactory.getOcarina();
                break;
            case 4 : w = WeaponFactory.getFionda();
                break;
            case 5 : w = WeaponFactory.getCannone();
                break;
            default : break;
            }
        } else {
            Random randHi = new Random();
            int luckHi = randHi.nextInt(LUCKFORSTATS + 1);
            switch (luckHi) {
            case 0 : w = WeaponFactory.getMazza();
                break;
            case 1 : w = WeaponFactory.getLanciafiamme();
                break;
            case 2 :  w = WeaponFactory.getCerbottana();
                break;
            case 3 : w = WeaponFactory.getSpadaMistica();
                break;
            case 4 : w = WeaponFactory.getColtre();
                break;
            default : break;
            }
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
        for(Entry<Statistics, Integer> e : m.entrySet()) {
            if (best.getX().equals(e.getKey())) {
                m.replace(e.getKey(), e.getValue() + SHIFT);
            }
            if(best.getY().equals(e.getKey())) {
                m.replace(e.getKey(), e.getValue() + SHIFT);
            }
        }
        return new Pair<>(w, m);
    }
}
