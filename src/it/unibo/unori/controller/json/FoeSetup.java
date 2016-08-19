package it.unibo.unori.controller.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    // private final Map<String, Status> immunityMap;
    private final Map<String, Weapon> weaponMap;
    private final Map<String, /* List< */MagicAttackInterface>/* > */ magicsMap;

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
            return /* FOLLETTO */DEFAULT;
        } else if (j.equals(FoesFindable.GNOMO_DA_GIARDINO)) {
            return /* GNOMO_DA_GIARDINO */DEFAULT;
        } else if (j.equals(FoesFindable.DEMONE)) {
            return /* DEMONE */DEFAULT;
        } else if (j.equals(FoesFindable.DRAGO)) {
            return /* DRAGO */DEFAULT;
        } else if (j.equals(FoesFindable.SPIRITO)) {
            return /* SPIRITO */DEFAULT;
        } else if (j.equals(FoesFindable.BAMBINO)) {
            return /* BAMBINO */DEFAULT;
        } else if (j.equals(FoesFindable.STREGONE)) {
            return /* STREGONE */DEFAULT;
        } else if (j.equals(FoesFindable.EROE_CADUTO)) {
            return /* EROE_CADUTO */DEFAULT;
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

    public static String getSpritePath(final FoesFindable ff, final int ia) throws IllegalArgumentException {
        Optional<String> jobPath;

        if (ff.equals(FoesFindable.BAMBINO)) {
            if (ia <= 3) {
                jobPath = Optional.of("res/sprites/foes/bambino2.png");
            } else if (ia > 3 && ia <= 5) {
                jobPath = Optional.of("res/sprites/foes/bambino.png");
            } else if (ia > 5 && ia <= 9) {
                jobPath = Optional.of("res/sprites/foes/bambino3.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/bambino4.png");
            }
        } else if (ff.equals(FoesFindable.DEMONE)) {
            if (ia <= 5) {
                jobPath = Optional.of("res/sprites/foes/demone2.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/demone.png");
            }
        } else if (ff.equals(FoesFindable.DRAGO)) {
            if (ia <= 5) {
                jobPath = Optional.of("res/sprites/foes/drago2.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/drago.png");
            }
        } else if (ff.equals(FoesFindable.EROE_CADUTO)) {
            jobPath = Optional.of("res/sprites/foes/cavaliere2.png");
        } else if (ff.equals(FoesFindable.FOLLETTO)) {
            if (ia <= 5) {
                jobPath = Optional.of("res/sprites/foes/folletto.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/folletto2.png");
            }
        } else if (ff.equals(FoesFindable.GNOMO_DA_GIARDINO)) {
            jobPath = Optional.of("res/sprites/foes/gnomo.png");
        } else if (ff.equals(FoesFindable.SPIRITO)) {
            if (ia <= 5) {
                jobPath = Optional.of("res/sprites/foes/spirito.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/spirito2.png");
            }
        } else if (ff.equals(FoesFindable.STREGONE)) {
            if (ia <= 5) {
                jobPath = Optional.of("res/sprites/foes/stregone.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/stregone2.png");
            }
        } else {
            jobPath = Optional.empty();
        }

        return jobPath.orElseThrow(
                        () -> new IllegalArgumentException("It is not provided any default file path for the Foe " + ff));
    }

}
