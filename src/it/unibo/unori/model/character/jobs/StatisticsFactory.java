package it.unibo.unori.model.character.jobs;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.Statistics;

/**
  *Class to generate the basic statistics of jobs.
  */
public class StatisticsFactory {

    private static final  Map<Jobs, Map<Statistics, Integer>> JOBSMAP = new HashMap<>();

    private Map<Statistics, Integer> createWarriorStats() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, 1500);
        m.put(Statistics.TOTALMP, 500);
        m.put(Statistics.SPEED, 1100);
        m.put(Statistics.FIREATK, 800);
        m.put(Statistics.FIREDEF, 750);
        m.put(Statistics.THUNDERATK, 800);
        m.put(Statistics.THUNDERDEF, 750);
        m.put(Statistics.ICEATK, 800);
        m.put(Statistics.ICEDEF, 750);
        m.put(Statistics.PHYSICATK, 2000);
        m.put(Statistics.PHYSICDEF, 2000);
        return m;
    }

    private Map<Statistics, Integer> createPaladinStats() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, 2000);
        m.put(Statistics.TOTALMP, 1000);
        m.put(Statistics.SPEED, 900);
        m.put(Statistics.FIREATK, 700);
        m.put(Statistics.FIREDEF, 1200);
        m.put(Statistics.THUNDERATK, 700);
        m.put(Statistics.THUNDERDEF, 1200);
        m.put(Statistics.ICEATK, 700);
        m.put(Statistics.ICEDEF, 1200);
        m.put(Statistics.PHYSICATK, 1000);
        m.put(Statistics.PHYSICDEF, 2500);
        return m;
    }

    private Map<Statistics, Integer> createMageStats() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, 1000);
        m.put(Statistics.TOTALMP, 2500);
        m.put(Statistics.SPEED, 1200);
        m.put(Statistics.FIREATK, 1500);
        m.put(Statistics.FIREDEF, 900);
        m.put(Statistics.THUNDERATK, 1500);
        m.put(Statistics.THUNDERDEF, 900);
        m.put(Statistics.ICEATK, 1500);
        m.put(Statistics.ICEDEF, 900);
        m.put(Statistics.PHYSICATK, 500);
        m.put(Statistics.PHYSICDEF, 500);
        return m;
    }

    private Map<Statistics, Integer> createRangerStats() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, 1000);
        m.put(Statistics.TOTALMP, 800);
        m.put(Statistics.SPEED, 2500);
        m.put(Statistics.FIREATK, 900);
        m.put(Statistics.FIREDEF, 900);
        m.put(Statistics.THUNDERATK, 1200);
        m.put(Statistics.THUNDERDEF, 1200);
        m.put(Statistics.ICEATK, 900);
        m.put(Statistics.ICEDEF, 900);
        m.put(Statistics.PHYSICATK, 1300);
        m.put(Statistics.PHYSICDEF, 700);
        return m;
    }

    private Map<Statistics, Integer> createCookStats() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, 1000);
        m.put(Statistics.TOTALMP, 1500);
        m.put(Statistics.SPEED, 1000);
        m.put(Statistics.FIREATK, 1200);
        m.put(Statistics.FIREDEF, 1200);
        m.put(Statistics.THUNDERATK, 750);
        m.put(Statistics.THUNDERDEF, 750);
        m.put(Statistics.ICEATK, 1000);
        m.put(Statistics.ICEDEF, 1000);
        m.put(Statistics.PHYSICATK, 750);
        m.put(Statistics.PHYSICDEF, 750);
        return m;
    }

    private Map<Statistics, Integer> createClownStats() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, 1500);
        m.put(Statistics.TOTALMP, 1000);
        m.put(Statistics.SPEED, 1000);
        m.put(Statistics.FIREATK, 1000);
        m.put(Statistics.FIREDEF, 1000);
        m.put(Statistics.THUNDERATK, 1000);
        m.put(Statistics.THUNDERDEF, 1000);
        m.put(Statistics.ICEATK, 1000);
        m.put(Statistics.ICEDEF, 1000);
        m.put(Statistics.PHYSICATK, 1000);
        m.put(Statistics.PHYSICDEF, 1000);
        return m;
    }
    /**
     * Get the basic statistics of a job.
     * @param job 
     *            job of the character
     * @return statics of the chosen job
     */
    public Map<Statistics, Integer> getJobStats(final Jobs job) {
        if (!JOBSMAP.containsKey(job)) {
            switch(job) {
                case WARRIOR : JOBSMAP.put(Jobs.WARRIOR, this.createWarriorStats()); break;
                case PALADIN : JOBSMAP.put(Jobs.PALADIN, this.createPaladinStats()); break;
                case MAGE : JOBSMAP.put(Jobs.MAGE, this.createMageStats()); break;
                case RANGER : JOBSMAP.put(Jobs.RANGER, this.createRangerStats()); break;
                case COOK : JOBSMAP.put(Jobs.COOK, this.createCookStats()); break;
                case CLOWN : JOBSMAP.put(Jobs.CLOWN, this.createClownStats()); break;
                default:
                    break;
            }
        }
        return new HashMap<>(JOBSMAP.get(job));
    }
    /**
     * Method to create the dumpStats.
     * @return dumpstats
     */
    public Map<Statistics, Integer> createDumpStats() {
        final Map<Statistics, Integer> m = new HashMap<>();
        m.put(Statistics.TOTALHP, 1500);
        m.put(Statistics.TOTALMP, 500);
        m.put(Statistics.SPEED, 1100);
        m.put(Statistics.FIREATK, 800);
        m.put(Statistics.FIREDEF, 750);
        m.put(Statistics.THUNDERATK, 800);
        m.put(Statistics.THUNDERDEF, 750);
        m.put(Statistics.ICEATK, 800);
        m.put(Statistics.ICEDEF, 750);
        m.put(Statistics.PHYSICATK, 2000);
        m.put(Statistics.PHYSICDEF, 2000);
        m.put(Statistics.EXPFACTOR, 100);
        return m;
    }

}