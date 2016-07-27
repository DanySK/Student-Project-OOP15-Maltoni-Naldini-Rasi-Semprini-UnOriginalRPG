package it.unibo.unori.model.battle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.model.character.exceptions.MaxHeroException;
import it.unibo.unori.model.character.jobs.Jobs;

/**
 * Class for testing the Battle Mode.
 *
 */
public class BattleTest {
    
    private Battle battle;
    private HeroTeam team = new HeroTeamImpl();
    private List<Foe> enemies = new ArrayList<>();
    
    /**
     * Method to test the only initialization of the Battle.
     * @throws MaxHeroException 
     * @throws IllegalArgumentException 
     */
    @Test
    public void testInitialization() throws IllegalArgumentException, MaxHeroException {
        
        team.addHero(new HeroImpl("Primo", Jobs.DUMP));
        assertEquals(team.getAliveHeroes().size(), 1);
        team.addHero(new HeroImpl("Secondo", Jobs.DUMP));
        assertEquals(team.getAliveHeroes().size(), 2);
        team.addHero(new HeroImpl("Terzo", Jobs.DUMP));
        assertEquals(team.getAliveHeroes().size(), 3);
        team.addHero(new HeroImpl("Quarto", Jobs.DUMP));
        assertEquals(team.getAliveHeroes().size(), 4);
        
        enemies.add(new FoeImpl(1, "Primo Nemico", "", Jobs.DUMP.getInitialStats()));
        enemies.add(new FoeImpl(1, "Secondo Nemico", "", Jobs.DUMP.getInitialStats()));
        enemies.add(new FoeImpl(1, "Terzo Nemico", "", Jobs.DUMP.getInitialStats()));
        enemies.add(new FoeImpl(1, "Quarto Nemico", "", Jobs.DUMP.getInitialStats()));
        enemies.add(new FoeImpl(1, "Quinto Nemico", "", Jobs.DUMP.getInitialStats()));
    }
    
    /**
     * Method to catch possible and certain Exceptions when calling determinate methods.
     */
    @Test
    public void testExceptions() {
        try {
            team.addHero(new HeroImpl("Quinto", Jobs.DUMP));
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException!!");
            e.printStackTrace();
        } catch (MaxHeroException e) {
            e.printStackTrace();
        }
    }
}
