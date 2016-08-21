package it.unibo.unori.controller.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.factory.FoesFactory;
import it.unibo.unori.model.character.factory.FoesFindable;
import it.unibo.unori.model.items.Weapon;

/**
 * Factory which provides default parameters for the foes and loading them from
 * file. It can support also more than one magic serialization and status
 * serialization, but model doesn't use these features.
 */
public class FoeSetup {
    /**
     * Path to the file with default paramethers for generating foes.
     */
    public static final String DEFAULT = "res/foes/defaultFoe.json";
    private static final int ONE_THIRD_IA = FoeImpl.MAXIA / 3;
    private static final int HALF_IA = FoeImpl.MAXIA / 2;
    private final JsonFileManager fileManager;
    private final Map<String, Map<Statistics, Integer>> statsMap;
    private final Map<String, Status> immunityMap;
    private final Map<String, Weapon> weaponMap;
    private final Map<String, List<MagicAttackInterface>> magicsMap;

    /**
     * Default constructor.
     */
    public FoeSetup() {
        fileManager = new JsonFileManager();
        statsMap = new HashMap<>();
        immunityMap = new HashMap<>();
        weaponMap = new HashMap<>();
        magicsMap = new HashMap<>();
    }

    /**
     * This main method simply generates default foe parameters file.
     * 
     * @param args
     *            unused default arguments
     * @throws IOException
     *             if something wrong happens
     */
    public static void main(final String[] args) throws IOException {
        final JsonFileManager jfm = new JsonFileManager();

        final JsonFoeParameter jsonFoe = new JsonFoeParameter(FoesFactory.getBasicStats(),
                Status.NONE /* TODO check */, FoesFactory.getBasicWeap(), FoesFactory.getBasicMag());
        jfm.saveFoe(jsonFoe, DEFAULT);

    }

    /**
     * This method returns the path where to find the file with default
     * parameters for that type of monster. Because of modeling choices, this
     * method ended up to return the same path for every supported monster.
     * 
     * @param foe
     *            the foe type
     * @return the path, if supported; null otherwise
     */
    public static String getPath(final FoesFindable foe) {
        if (foe.equals(FoesFindable.FOLLETTO) || foe.equals(FoesFindable.GNOMO_DA_GIARDINO)
                || foe.equals(FoesFindable.DEMONE) || foe.equals(FoesFindable.DRAGO) || foe.equals(FoesFindable.SPIRITO)
                || foe.equals(FoesFindable.BAMBINO) || foe.equals(FoesFindable.STREGONE)
                || foe.equals(FoesFindable.EROE_CADUTO)) {
            return DEFAULT;
        } else {
            return null;
        }
    }

    /**
     * This method returns the basic statistics map for a foe from the specified
     * file.
     * 
     * @param path
     *            the path to the file
     * @return the statistics map, if possible; null otherwise
     */
    public Map<Statistics, Integer> getBasicStats(final String path) {
        if (!this.statsMap.containsKey(path)) {
            try {
                this.statsMap.put(path, fileManager.loadFoe(path).getStats());
            } catch (IOException e) {
                return null;
            }
        }
        return new HashMap<>(this.statsMap.get(path));
    }

    /**
     * This method returns the basic weapon for a foe from the specified file.
     * 
     * @param path
     *            the path to the file
     * @return the weapon, if possible; null otherwise
     */
    public Weapon getBasicWeapon(final String path) {
        if (!this.weaponMap.containsKey(path)) {
            try {
                this.weaponMap.put(path, fileManager.loadFoe(path).getWeapon());
            } catch (IOException e) {
                return null;
            }
        }
        return this.weaponMap.get(path);
    }

    /**
     * This method returns the basic magic attack for a foe from the specified
     * file.
     * 
     * @param path
     *            the path to the file
     * @return the magic attack, if possible; null otherwise
     */
    public MagicAttackInterface getBasicMagic(final String path) {
        if (!this.magicsMap.containsKey(path)) {
            try {
                this.magicsMap.put(path, fileManager.loadFoe(path).getMagics());
            } catch (IOException e) {
                return null;
            }
        }
        return this.magicsMap.get(path).get(0);
    }

    /**
     * This method returns the default path to a monster sprite in function of
     * its type and its IA.
     * 
     * @param ff
     *            the foe type
     * @param ia
     *            the IA level of the foe
     * @return the path to the sprite
     * @throws IllegalArgumentException
     *             if unsupported Foe is provided
     */
    public static String getSpritePath(final FoesFindable ff, final int ia) throws IllegalArgumentException {
        Optional<String> jobPath;

        if (ff.equals(FoesFindable.BAMBINO)) {
            if (ia <= ONE_THIRD_IA) {
                jobPath = Optional.of("res/sprites/foes/bambino2.png");
            } else if (ia > ONE_THIRD_IA && ia <= HALF_IA) {
                jobPath = Optional.of("res/sprites/foes/bambino.png");
            } else if (ia > ONE_THIRD_IA && ia <= FoeImpl.MAXIA - 1) {
                jobPath = Optional.of("res/sprites/foes/bambino3.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/bambino4.png");
            }
        } else if (ff.equals(FoesFindable.DEMONE)) {
            if (ia <= HALF_IA) {
                jobPath = Optional.of("res/sprites/foes/demone2.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/demone.png");
            }
        } else if (ff.equals(FoesFindable.DRAGO)) {
            if (ia <= HALF_IA) {
                jobPath = Optional.of("res/sprites/foes/drago2.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/drago.png");
            }
        } else if (ff.equals(FoesFindable.EROE_CADUTO)) {
            jobPath = Optional.of("res/sprites/foes/cavaliere2.png");
        } else if (ff.equals(FoesFindable.FOLLETTO)) {
            if (ia <= HALF_IA) {
                jobPath = Optional.of("res/sprites/foes/folletto.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/folletto2.png");
            }
        } else if (ff.equals(FoesFindable.GNOMO_DA_GIARDINO)) {
            jobPath = Optional.of("res/sprites/foes/gnomo.png");
        } else if (ff.equals(FoesFindable.SPIRITO)) {
            if (ia <= HALF_IA) {
                jobPath = Optional.of("res/sprites/foes/spirito.png");
            } else {
                jobPath = Optional.of("res/sprites/foes/spirito2.png");
            }
        } else if (ff.equals(FoesFindable.STREGONE)) {
            if (ia <= HALF_IA) {
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

    /**
     * This method returns the default immunity for a monster loading it from
     * file.
     * 
     * @return the status the foe is immune to
     */
    public Map<String, Status> getImmunityMap() {
        return immunityMap;
    }

}
