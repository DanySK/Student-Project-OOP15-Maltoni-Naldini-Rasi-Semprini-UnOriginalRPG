package it.unibo.unori.model.character.jobs;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.controller.utility.JobsSetup;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.Weapon;

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
    COOK(JobsSetup.COOK), CLOWN(JobsSetup.CLOWN);
    /**
     * Dump Job for testing reasons
     */
    DUMP();

    private final Map<ArmorPieces, Armor> basicEquip;
    private final Map<Statistics, Integer> basicStats;
    private final Map<Statistics, Integer> growthStats;
    private final Weapon basicWeapon;

     Jobs(final String filePath) {
        this.basicEquip = JobsSetup.getDefaultArmor(filePath);
        this.basicStats = JobsSetup.getDefaultStats(filePath);
        this.growthStats = JobsSetup.getDefaultIncrements(filePath);
        this.basicWeapon = JobsSetup.getDefaultWeapon(filePath);
    }
     
    Jobs(){
        
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


}
