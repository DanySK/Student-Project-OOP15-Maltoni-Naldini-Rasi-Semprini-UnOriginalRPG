package it.unibo.unori.model.items;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;

/**
 * Factory to create some common weapon.
 *
 */
public final class WeaponFactory {
    
    private WeaponFactory() {
        //Empty private constructor.
    }
    
    private static Map<Statistics, Integer> generateMap(final Statistics phys, final int first,
            final Statistics fire, final int secnd, final Statistics ice,
            final int third, final Statistics thun, final int fourth) {
        final Map<Statistics, Integer> stats = new HashMap<Statistics, Integer>();
        stats.put(phys, first);
        stats.put(fire, secnd);
        stats.put(ice, third);
        stats.put(thun, fourth);
        
        return new HashMap<>(stats);
    }
    
    /**
     * Create a standard sword.
     * @return Standard Sword object
     */
    public static Weapon getStdSword() {
        return new WeaponImpl("Spada Semplice",
                "Adatta per tagliare il burro più che i nemici",
                generateMap(
                    Statistics.PHYSICATK, 100,
                    Statistics.FIREATK, 0,
                    Statistics.ICEATK, 0,
                    Statistics.THUNDERATK, 0
                    ),
                Status.NONE);
    }
    
    /**
     * Create Pugnale.
     * @return Weapon Pugnale.
     */
    public static Weapon getPugnale() {
        return new WeaponImpl("Pugnale",
                "Un'arma adatta per gli attacchi fisici. Non ha nulla di magico",
                generateMap(
                    Statistics.PHYSICATK, 500,
                    Statistics.FIREATK, 0,
                    Statistics.ICEATK, 0,
                    Statistics.THUNDERATK, 0
                    ),
                Status.BLEEDING);
    }
    
    /**
     * Create Clava.
     * @return Weapon Clava.
     */
    public static Weapon getClava() {
        return new WeaponImpl("Clava Pesante",
                "Una Clava può ferire nell'orgoglio e nel corpo",
                generateMap(
                    Statistics.PHYSICATK, 350,
                    Statistics.FIREATK, 0,
                    Statistics.ICEATK, 40,
                    Statistics.THUNDERATK, 0
                    ),
                Status.NONE);
    }
    
    /**
     * Create Balestra.
     * @return Weapon Balestra.
     */
    public static Weapon getBalestra() {
        return new WeaponImpl("Balestra Antica",
                "Arma appartenuta a gloriosi guerrieri del passato",
                generateMap(
                    Statistics.PHYSICATK, 450,
                    Statistics.FIREATK, 12,
                    Statistics.ICEATK, 0,
                    Statistics.THUNDERATK, 20
                    ),
                Status.BLEEDING);
    }
    
    /**
     * Create Maledizione.
     * @return Weapon Maledizione.
     */
    public static Weapon getMaledizione() {
        return new WeaponImpl("Maledizione Verbale",
                "Anche se è un'arma verbale, una maledizione non è da sottovalutare",
                generateMap(
                    Statistics.PHYSICATK, 150,
                    Statistics.FIREATK, 9,
                    Statistics.ICEATK, 20,
                    Statistics.THUNDERATK, 15
                    ),
                Status.CURSED);
    }
    
    /**
     * Create Chiodo.
     * @return Weapon Chiodo.
     */
    public static Weapon getChiodo() {
        return new WeaponImpl("Chiodo Arrugginito",
                "Enorme chiodo appuntito in grado di avvelenare",
                generateMap(
                    Statistics.PHYSICATK, 220,
                    Statistics.FIREATK, 8,
                    Statistics.ICEATK, 10,
                    Statistics.THUNDERATK, 15
                    ),
                Status.POISONED);
    }
    
    /**
     * Create Lancia.
     * @return Weapon Lancia.
     */
    public static Weapon getLancia() {
        return new WeaponImpl("Lancia Elettrica",
                "Una lancia elettrificata che può causare gravi danni",
                generateMap(
                    Statistics.PHYSICATK, 260,
                    Statistics.FIREATK, 10,
                    Statistics.ICEATK, 5,
                    Statistics.THUNDERATK, 30
                    ),
                Status.NOT_DEFENDABLE);
    }
    
    /**
     * Create Ocarina.
     * @return Weapon Ocarina.
     */
    public static Weapon getOcarina() {
        return new WeaponImpl("Ocarina Maledetta",
                "Suona una melodia maledetta che causa danni al corpo e allo spirito",
                generateMap(
                    Statistics.PHYSICATK, 200,
                    Statistics.FIREATK, 10,
                    Statistics.ICEATK, 25,
                    Statistics.THUNDERATK, 20
                    ),
                Status.ASLEEP);
    }
    
    /**
     * Create Fionda.
     * @return Weapon Fionda.
     */
    public static Weapon getFionda() {
        return new WeaponImpl("Fionda Incandescente",
                "Un'arma molto potente nel tipo Fuoco",
                generateMap(
                    Statistics.PHYSICATK, 280,
                    Statistics.FIREATK, 50,
                    Statistics.ICEATK, 0,
                    Statistics.THUNDERATK, 10
                    ),
                Status.NONE);

    }
    
    /**
     * Create Cannone.
     * @return Weapon Cannone.
     */
    public static Weapon getCannone() {
        return new WeaponImpl("Cannone Espandibile",
                "Un cannone portatile, che può essere, all'occorrenza, espanso per magia",
                generateMap(
                    Statistics.PHYSICATK, 480,
                    Statistics.FIREATK, 30,
                    Statistics.ICEATK, 0,
                    Statistics.THUNDERATK, 0
                    ),
                Status.BLEEDING);
    }
    
    /**
     * Create Mazza.
     * @return Weapon Mazza.
     */
    public static Weapon getMazza() {
        return new WeaponImpl("Mazza Chiodata",
                "Una mazza molto speciale, che può anche provocare una maledizione",
                generateMap(
                    Statistics.PHYSICATK, 380,
                    Statistics.FIREATK, 10,
                    Statistics.ICEATK, 50,
                    Statistics.THUNDERATK, 0
                    ),
                Status.CURSED);
    }
    
    /**
     * Create Lanciafiamme.
     * @return Weapon Lanciafiamme.
     */
    public static Weapon getLanciafiamme() {
        return new WeaponImpl("Lanciafiamme",
                "C'è davvero bisogno di una descrizione?",
                generateMap(
                    Statistics.PHYSICATK, 580,
                    Statistics.FIREATK, 90,
                    Statistics.ICEATK, 0,
                    Statistics.THUNDERATK, 0
                    ),
                Status.NOT_DEFENDABLE);
    }
    
    /**
     * Create Cerbottana.
     * @return Weapon Cerbottana.
     */
    public static Weapon getCerbottana() {
        return new WeaponImpl("Cerbottana",
                "Arma meschina, silenziosa e letale",
                generateMap(
                    Statistics.PHYSICATK, 350,
                    Statistics.FIREATK, 20,
                    Statistics.ICEATK, 20,
                    Statistics.THUNDERATK, 25
                    ),
                Status.ASLEEP);
    }
    
    /**
     * Create Spada.
     * @return Weapon Spada.
     */
    public static Weapon getSpadaMistica() {
        return new WeaponImpl("Spada Mistica",
                "Una spada leggendaria, non per pivelli",
                generateMap(
                    Statistics.PHYSICATK, 500,
                    Statistics.FIREATK, 20,
                    Statistics.ICEATK, 40,
                    Statistics.THUNDERATK, 25
                    ),
                Status.ASLEEP);
    }
    
    /**
     * Create Coltre.
     * @return Weapon Coltre.
     */
    public static Weapon getColtre() {
        return new WeaponImpl("Coltre Tossica",
                "Una coltre di fumo capace di avvelenare il nemico",
                generateMap(
                    Statistics.PHYSICATK, 200,
                    Statistics.FIREATK, 20,
                    Statistics.ICEATK, 10,
                    Statistics.THUNDERATK, 35
                    ),
                Status.POISONED);
    }
}
