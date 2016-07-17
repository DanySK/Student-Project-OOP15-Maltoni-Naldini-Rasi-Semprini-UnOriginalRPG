package it.unibo.unori.model.character.jobs;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.Statistics;

/**
 * Class to generate the Growth factor of the single statistics of a job.
 * The getter function check if the map of the statistics of the job asked is 
 * already in the JobsMap, if it's not, the method call the private method to
 * generate the required map.
 *
 */
public class GrowthFactory {

    private static final  Map<Jobs, Map<Statistics, Integer>> JOBSMAP = new HashMap<>();
    private static final int HIGHGROWTHPV = 200;
    private static final int MEDIUMGROWTHPV = 100;
    private static final int LOWGROWTHPV = 50;

    private Map<Statistics, Integer> createWarriorGrowth() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, MEDIUMGROWTHPV);
        m.put(Statistics.TOTALMP, GrowthParameters.LOW.value);
        m.put(Statistics.SPEED, GrowthParameters.MEDIUM.value);
        m.put(Statistics.FIREATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.FIREDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.THUNDERATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.THUNDERDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.ICEATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.ICEDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.PHYSICATK, GrowthParameters.HIGH.value);
        m.put(Statistics.PHYSICDEF, GrowthParameters.HIGH.value);
        return m;
    }

    private Map<Statistics, Integer> createPaladinGrowth() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, HIGHGROWTHPV);
        m.put(Statistics.TOTALMP, GrowthParameters.MEDIUM.value);
        m.put(Statistics.SPEED, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.FIREATK, GrowthParameters.LOW.value);
        m.put(Statistics.FIREDEF, GrowthParameters.MEDIUM_HIGH.value);
        m.put(Statistics.THUNDERATK, GrowthParameters.LOW.value);
        m.put(Statistics.THUNDERDEF, GrowthParameters.MEDIUM_HIGH.value);
        m.put(Statistics.ICEATK, GrowthParameters.LOW.value);
        m.put(Statistics.ICEDEF, GrowthParameters.MEDIUM_HIGH.value);
        m.put(Statistics.PHYSICATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.PHYSICDEF, GrowthParameters.HIGH.value);
        return m;
    }

    private Map<Statistics, Integer> createMageGrowth() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, LOWGROWTHPV);
        m.put(Statistics.TOTALMP, GrowthParameters.HIGH.value);
        m.put(Statistics.SPEED, GrowthParameters.MEDIUM.value);
        m.put(Statistics.FIREATK, GrowthParameters.MEDIUM.value);
        m.put(Statistics.FIREDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.THUNDERATK, GrowthParameters.MEDIUM.value);
        m.put(Statistics.THUNDERDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.ICEATK, GrowthParameters.MEDIUM.value);
        m.put(Statistics.ICEDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.PHYSICATK, GrowthParameters.LOW.value);
        m.put(Statistics.PHYSICDEF, GrowthParameters.LOW.value);
        return m;
    }

    private Map<Statistics, Integer> createRangerGrowth() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, MEDIUMGROWTHPV);
        m.put(Statistics.TOTALMP, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.SPEED, GrowthParameters.HIGH.value);
        m.put(Statistics.FIREATK, GrowthParameters.MEDIUM.value);
        m.put(Statistics.FIREDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.THUNDERATK, GrowthParameters.MEDIUM.value);
        m.put(Statistics.THUNDERDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.ICEATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.ICEDEF, GrowthParameters.MEDIUM.value);
        m.put(Statistics.PHYSICATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.PHYSICDEF, GrowthParameters.MEDIUM.value);
        return m;
    }

    private Map<Statistics, Integer> createCookGrowth() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, MEDIUMGROWTHPV);
        m.put(Statistics.TOTALMP, GrowthParameters.MEDIUM.value);
        m.put(Statistics.SPEED, GrowthParameters.MEDIUM.value);
        m.put(Statistics.FIREATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.FIREDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.THUNDERATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.THUNDERDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.ICEATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.ICEDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.PHYSICATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.PHYSICDEF, GrowthParameters.MEDIUM_LOW.value);
        return m;
    }

    private Map<Statistics, Integer> createClownGrowth() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, MEDIUMGROWTHPV);
        m.put(Statistics.TOTALMP, GrowthParameters.MEDIUM.value);
        m.put(Statistics.SPEED, GrowthParameters.MEDIUM.value);
        m.put(Statistics.FIREATK, GrowthParameters.MEDIUM.value);
        m.put(Statistics.FIREDEF, GrowthParameters.MEDIUM.value);
        m.put(Statistics.THUNDERATK, GrowthParameters.MEDIUM.value);
        m.put(Statistics.THUNDERDEF, GrowthParameters.MEDIUM.value);
        m.put(Statistics.ICEATK, GrowthParameters.MEDIUM.value);
        m.put(Statistics.ICEDEF, GrowthParameters.MEDIUM.value);
        m.put(Statistics.PHYSICATK, GrowthParameters.MEDIUM.value);
        m.put(Statistics.PHYSICDEF, GrowthParameters.MEDIUM.value);
        return m;
    }

    /**
     * Create dump stats for a dump job.
     * @return dump stats
     */
    public Map<Statistics, Integer> createDumpGrowth() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, MEDIUMGROWTHPV);
        m.put(Statistics.TOTALMP, GrowthParameters.LOW.value);
        m.put(Statistics.SPEED, GrowthParameters.MEDIUM.value);
        m.put(Statistics.FIREATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.FIREDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.THUNDERATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.THUNDERDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.ICEATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.ICEDEF, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.PHYSICATK, GrowthParameters.HIGH.value);
        m.put(Statistics.PHYSICDEF, GrowthParameters.HIGH.value);
        return m;
    }
    /**
     * Getter for growth parameters of a job.
     * @param job the job specified 
     * @return a map containing the growth statistics of the job
     */
    public Map<Statistics, Integer> getJobGrowth(final Jobs job) {
        if (!JOBSMAP.containsKey(job)) {
            switch(job) {
                case WARRIOR : JOBSMAP.put(Jobs.WARRIOR, this.createWarriorGrowth()); break;
                case PALADIN : JOBSMAP.put(Jobs.PALADIN, this.createPaladinGrowth()); break;
                case MAGE : JOBSMAP.put(Jobs.MAGE, this.createMageGrowth()); break;
                case RANGER : JOBSMAP.put(Jobs.RANGER, this.createRangerGrowth()); break;
                case COOK : JOBSMAP.put(Jobs.MAGE, this.createCookGrowth()); break;
                case CLOWN : JOBSMAP.put(Jobs.CLOWN, this.createClownGrowth()); break;
                default:
                    break;
            }
        }
        return new HashMap<>(JOBSMAP.get(job));
    }


    /**
     * Private enum with the growth values
     *
     */
    private enum GrowthParameters {
        /**
         * growth values
         */
        LOW(10), MEDIUM_LOW(20), MEDIUM(30), MEDIUM_HIGH(40), HIGH(50);

        final int value;


        GrowthParameters(final int value) {
            this.value = value;
        }

    }

}
