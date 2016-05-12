package it.unibo.unori.model.character.jobs;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.Statistics;

/**
 * Class to generate the Growth factor of the single statistics of a job.
 *
 */
public class GrowthFactory {

    private static final  Map<Jobs, Map<Statistics, Integer>> JOBSMAP = new HashMap<>();
    private static final int MAXGROWTHPV = 200;
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
        m.put(Statistics.PHYSICATK, GrowthParameters.MEDIUM_LOW.value);
        m.put(Statistics.PHYSICDEF, GrowthParameters.MEDIUM_LOW.value);
        return m;
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

        private final int value;

        GrowthParameters(final int value) {
            this.value = value;
        }

        /**
         * getter for the enum value.
         * @return value of the enum
         */
        public int getValue() {
            return this.value;
        }
    }

}
