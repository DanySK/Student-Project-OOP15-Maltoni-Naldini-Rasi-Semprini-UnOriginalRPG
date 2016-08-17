package it.unibo.unori.model.character.jobs;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.controller.json.JobsSetup;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorFactory;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponFactory;

/**
 * Enumeration to define the jobs of the party members.
 * Jobs influence the statistics, the basic equipments and the level of 
 * the characters
 *
 */
public enum Jobs {
    /**
     * Basic jobs for the heroes.
     * Warrior and Paladin
     */
    WARRIOR(JobsSetup.WARRIOR), PALADIN(JobsSetup.PALADIN), 
    /**
     * Mage and Ranger.
     */
    MAGE(JobsSetup.MAGE), RANGER(JobsSetup.RANGER),
    /**
     * Clown and Cook.
     */
    COOK(JobsSetup.COOK), CLOWN(JobsSetup.CLOWN),
    /**
     * Dump Job for testing reasons.
     */
    DUMP;

    private final Map<ArmorPieces, Armor> basicEquip;
    private final Map<Statistics, Integer> basicStats;
    private final Map<Statistics, Integer> growthStats;
    private final Weapon basicWeapon;
    private final String battleFrame;

     Jobs(final String filePath) {
         final JobsSetup js = new JobsSetup();
         
        this.basicEquip = js.getDefaultArmorMap(filePath);
        this.basicStats = js.getDefaultStatsMap(filePath);
        this.growthStats = js.getDefaultIncrementsMap(filePath);
        this.basicWeapon = js.getDefaultWeaponNullable(filePath);
        this.battleFrame = js.getBattleSpritePath(filePath);
    }

    Jobs() {
       this.basicEquip = new ArmorFactory().getStdEquip();
       this.basicWeapon = WeaponFactory.getStdSword();
       this.basicStats = new StatisticsFactory().createDumpStats();
       this.growthStats = new GrowthFactory().createDumpGrowth();
       this.battleFrame = "";
    }

     /**
      * Get the initial equipments of a job.
      * @return a defensive copy of the equipments
      */
     public Map<ArmorPieces, Armor> getInitialArmor() {
         return new HashMap<>(this.basicEquip);
     }

     /**
      * Get the initial statistics of a job.
      * @return a defensive copy of the statistics
      */
     public Map<Statistics, Integer> getInitialStats() {
         return new HashMap<>(this.basicStats);
     }
     /**
      * Get the increment values of the job statistics .
      * @return a defensive copy of the statistics
      */
     public Map<Statistics, Integer> getGrowthStats() {
         return new HashMap<>(this.growthStats);
     }
     /**
      * Get the starter weapon of the job.
      * @return the initial weapon of the job
      */
     public Weapon getInitialWeapon() {
         return this.basicWeapon;
     }

     /**
      * Get the battle sprite of the job.
      * @return
      *         the path of the file containing the sprite
      */
     public String getBattleFrame() {
         return this.battleFrame;
     }
}
