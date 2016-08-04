package it.unibo.unori.model.battle;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.FoeSquadImpl;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.model.character.exceptions.MaxFoesException;
import it.unibo.unori.model.character.exceptions.MaxHeroException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;

/**
 * Class for testing the Battle Mode.
 *
 */
public class BattleTest {
    
    private Battle battle;
    private HeroTeam team = new HeroTeamImpl();
    private FoeSquad enemies = new FoeSquadImpl();
    
    private void setBattle(final HeroTeam h, final Bag bag, final FoeSquad en) {
        this.battle = new BattleImpl(h, en, bag);
    }
    
    /**
     * Method to test the only initialization of the Battle.
     * @throws MaxHeroException 
     * @throws IllegalArgumentException 
     * @throws MaxFoesException 
     * @throws NoWeaponException 
     * @throws BarNotFullException 
     */
    @Test
    public void testInitialization() throws IllegalArgumentException, 
    MaxHeroException, MaxFoesException, NoWeaponException, BarNotFullException {
        
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
        
        this.setBattle(this.team, new BagImpl(), this.enemies);
        battle.setHeroOnTUrn(battle.getSquad().getFirstHeroOnTurn());
        battle.setFoeOnTurn(battle.getEnemies().getFirstFoeOnTurn());
        System.out.println("" + this.battle.getHeroOnTurn().getRemainingHP());
        System.out.println("" + this.battle.getHeroOnTurn().getAttack());
        final String firstDamage  = battle.attack(true);
        System.out.println(this.battle.getFoeOnTurn().getSpeed() + " "
                + this.battle.getHeroOnTurn().getSpeed());
        System.out.println(firstDamage);
        System.out.println("" + this.battle.getHeroOnTurn().getRemainingHP());
        System.out.println(this.battle.getHeroOnTurn().getCurrentBar());
        battle.setFoeOnTurn(battle.getEnemies().getAliveFoes().get(0));
        final String secndDamage  = battle.attack(true);
        System.out.println(secndDamage);
        System.out.println(this.battle.getHeroOnTurn().getCurrentBar());
        this.battle.getHeroOnTurn().setCurrentBar(100);
        System.out.println(battle.specialAttack());
        assertEquals(this.battle.getEnemies().getAliveFoes().size(), 0);
        assertTrue(this.battle.isOver());
        System.out.println(battle.getOutCome());
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
        try {
            battle.getOutCome();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
