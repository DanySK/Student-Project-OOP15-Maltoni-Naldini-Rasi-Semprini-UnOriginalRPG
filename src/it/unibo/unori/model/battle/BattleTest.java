package it.unibo.unori.model.battle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.FoeSquadImpl;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.model.character.exceptions.MaxFoesException;
import it.unibo.unori.model.character.exceptions.MaxHeroException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.BagImpl;

/**
 * Class for testing the Battle Mode.
 *
 */
public class BattleTest {
    
    private Battle battle;
    private HeroTeam team = new HeroTeamImpl();
    private FoeSquad enemies = new FoeSquadImpl();
    
    /**
     * Method to test the only initialization of the Battle.
     * @throws MaxHeroException 
     * @throws IllegalArgumentException 
     * @throws MaxFoesException 
     * @throws NoWeaponException 
     */
    @Test
    public void testInitialization() throws IllegalArgumentException, 
    MaxHeroException, MaxFoesException, NoWeaponException {
        
        team.addHero(new HeroImpl("Primo", Jobs.DUMP));
        assertEquals(team.getAliveHeroes().size(), 1);
        team.addHero(new HeroImpl("Secondo", Jobs.DUMP));
        assertEquals(team.getAliveHeroes().size(), 2);
        team.addHero(new HeroImpl("Terzo", Jobs.DUMP));
        assertEquals(team.getAliveHeroes().size(), 3);
        team.addHero(new HeroImpl("Quarto", Jobs.DUMP));
        assertEquals(team.getAliveHeroes().size(), 4);

        enemies.addFoe(new FoeImpl(1, "Primo Nemico", "", Jobs.DUMP.getInitialStats()));
        assertEquals(enemies.getAliveFoes().size(), 1);
        enemies.addFoe(new FoeImpl(1, "Secondo Nemico", "", Jobs.DUMP.getInitialStats()));
        assertEquals(enemies.getAliveFoes().size(), 2);
        enemies.addFoe(new FoeImpl(1, "Terzo Nemico", "", Jobs.DUMP.getInitialStats()));
        assertEquals(enemies.getAliveFoes().size(), 3);
        enemies.addFoe(new FoeImpl(1, "Quarto Nemico", "", Jobs.DUMP.getInitialStats()));
        assertEquals(enemies.getAliveFoes().size(), 4);
        
        this.battle = new BattleImpl(this.team, this.enemies, new BagImpl());
        battle.setHeroOnTUrn(battle.getSquad().getFirstHeroOnTurn());
        battle.setFoeOnTurn(battle.getEnemies().getFirstFoeOnTurn());
        System.out.println("" + this.battle.getFoeOnTurn().getRemainingHP());
        System.out.println("" + this.battle.getHeroOnTurn().getAttack());
        final int firstDamage  = battle.attack(battle.getFoeOnTurn(), battle.getHeroOnTurn());
        System.out.println("" + firstDamage);
        System.out.println("" + this.battle.getFoeOnTurn().getRemainingHP());
        assertEquals(this.battle.getEnemies().getAliveFoes().size(), 3);
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
        try {
            enemies.addFoe(new FoeImpl(1, "Quinto Nemico", "", Jobs.DUMP.getInitialStats()));
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException!!");
            e.printStackTrace();
        } catch (MaxFoesException e) {
            e.printStackTrace();
        }
    }
}
