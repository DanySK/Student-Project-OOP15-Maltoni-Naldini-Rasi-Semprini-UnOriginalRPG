package it.unibo.unori.controller.json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.factory.FoesFactory;
import it.unibo.unori.model.character.factory.FoesFindable;
import it.unibo.unori.model.items.Weapon;

public class FoeSetup {
    public static final String FOLLETTO = "res/foes/fairy.json";
    public static final String GNOMO_DA_GIARDINO = "res/foes/gnome.json";
    public static final String DEMONE = "res/foes/demon.json";
    public static final String DRAGO = "res/foes/dragon.json";
    public static final String SPIRITO = "res/foes/spirit.json";
    public static final String BAMBINO = "res/foes/baby.json";
    public static final String STREGONE = "res/foes/wizard.json";
    public static final String EROE_CADUTO = "res/foes/fallen.json";
    public static final String DEFAULT = "res/foes/defaultFoe.json";
    private final JsonFileManager fileManager;
    private final Map<String, Map<Statistics, Integer>> statsMap;
    // private Map<String, Status> immunityMap;
    private Map<String, Weapon> weaponMap;
    private Map<String, /* List< */MagicAttackInterface>/* > */ magicsMap;

    /**
     * Default constructor.
     */
    public FoeSetup() {
        fileManager = new JsonFileManager();
        statsMap = new HashMap<>();
        // immunityMap = new HashMap<>();
        weaponMap = new HashMap<>();
        magicsMap = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {
        final JsonFileManager jfm = new JsonFileManager();

        final JsonFoeParameters jsonFoe = new JsonFoeParameters(FoesFactory.getBasicStats(),
                Status.NONE /* TODO check */, FoesFactory.getBasicWeap(), FoesFactory.getBasicMag());
        jfm.saveFoe(jsonFoe, DEFAULT);

    }

    public static String getPath(final FoesFindable j) {
        if (j.equals(FoesFindable.FOLLETTO)) {
            return /*FOLLETTO*/DEFAULT;
        } else if (j.equals(FoesFindable.GNOMO_DA_GIARDINO)) {
            return /*GNOMO_DA_GIARDINO*/DEFAULT;
        } else if (j.equals(FoesFindable.DEMONE)) {
            return /*DEMONE*/DEFAULT;
        } else if (j.equals(FoesFindable.DRAGO)) {
            return /*DRAGO*/DEFAULT;
        } else if (j.equals(FoesFindable.SPIRITO)) {
            return /*SPIRITO*/DEFAULT;
        } else if (j.equals(FoesFindable.BAMBINO)) {
            return /*BAMBINO*/DEFAULT;
        } else if (j.equals(FoesFindable.STREGONE)) {
            return /*STREGONE*/DEFAULT;
        } else if (j.equals(FoesFindable.EROE_CADUTO)) {
            return /*EROE_CADUTO*/DEFAULT;
        } else {
            return null;
        }
    }

    public Map<Statistics, Integer> getBasicStats(final String path) {
        if (!this.statsMap.containsKey(path)) {
            try {
                this.statsMap.put(path, fileManager.loadFoe(path).getStats());
            } catch (IOException e) {
                return null; // TODO maybe check
            }
        }
        return new HashMap<>(this.statsMap.get(path));
    }

    public Weapon getBasicWeapon(final String path) {
        if (!this.weaponMap.containsKey(path)) {
            try {
                this.weaponMap.put(path, fileManager.loadFoe(path).getWeapon());
            } catch (IOException e) {
                return null; // TODO maybe check
            }
        }
        return this.weaponMap.get(path);
    }

    public MagicAttackInterface getBasicMagic(final String path) {
        if (!this.magicsMap.containsKey(path)) {
            try {
                this.magicsMap.put(path, fileManager.loadFoe(path).getMagics().get(0));
            } catch (IOException e) {
                return null; // TODO maybe check
            }
        }
        return this.magicsMap.get(path)/* .get(0) */;
    }

}
