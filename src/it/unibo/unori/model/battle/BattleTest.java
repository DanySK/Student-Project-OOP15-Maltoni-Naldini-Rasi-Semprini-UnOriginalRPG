package it.unibo.unori.model.battle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.NotDefendableException;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.battle.utility.MagicGenerator;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.FoeSquadImpl;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.MaxFoesException;
import it.unibo.unori.model.character.exceptions.MaxHeroException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.character.factory.FoesFindable;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;
import it.unibo.unori.model.items.WeaponFactory;
import it.unibo.unori.model.menu.DialogueInterface;

/**
 * Class for testing the Battle Mode.
 *
 */
public class BattleTest {
    
    private Battle battle;
    private HeroTeam team = new HeroTeamImpl();
    private FoeSquad enemies = new FoeSquadImpl();
    private final Bag bag = new BagImpl();
    
    private void setBattle() {
        this.battle = new BattleImpl(this.team, this.enemies, this.bag);
        battle.getPresentation().generate();
    }
    
    private void setTeams() throws IllegalArgumentException, MaxHeroException, MaxFoesException {
        team.addHero(new HeroImpl("Primo", Jobs.CLOWN));
        assertEquals(team.getAliveHeroes().size(), 1);
        team.addHero(new HeroImpl("Secondo", Jobs.COOK));
        assertEquals(team.getAliveHeroes().size(), 2);
        team.addHero(new HeroImpl("Terzo", Jobs.MAGE));
        assertEquals(team.getAliveHeroes().size(), 3);
        team.addHero(new HeroImpl("Quarto", Jobs.RANGER));
        assertEquals(team.getAliveHeroes().size(), 4);

        enemies.addFoe(new FoeImpl(1, "Primo Nemico", "", FoesFindable.GNOMO_DA_GIARDINO));
        assertEquals(enemies.getAliveFoes().size(), 1);
        enemies.addFoe(new FoeImpl(1, "Secondo Nemico", "", FoesFindable.GNOMO_DA_GIARDINO));
        assertEquals(enemies.getAliveFoes().size(), 2);
        enemies.addFoe(new FoeImpl(1, "Terzo Nemico", "", FoesFindable.GNOMO_DA_GIARDINO));
        assertEquals(enemies.getAliveFoes().size(), 3);
        enemies.addFoe(new FoeImpl(1, "Quarto Nemico", "", FoesFindable.GNOMO_DA_GIARDINO));
        assertEquals(enemies.getAliveFoes().size(), 4);
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
        this.setTeams();
        this.setBattle();
        try {
            battle.getOutCome();
        } catch (IllegalStateException e) {
            System.out.println("La lotta non è ancora finita");
        }  catch (Exception other) {
            fail("OTHER EXCEPTION!!");
        }
        System.out.println(battle.setHeroOnTUrn(battle.getSquad().getFirstHeroOnTurn()));
        System.out.println(battle.setFoeOnTurn(battle.getEnemies().getFirstFoeOnTurn()));
        System.out.println("" + this.battle.getHeroOnTurn().getRemainingHP());
        System.out.println("" + this.battle.getHeroOnTurn().getAttack());
        final DialogueInterface firstDamage  = battle.attack(true);
        System.out.println(this.battle.getFoeOnTurn().getSpeed() + " "
                + this.battle.getHeroOnTurn().getSpeed());
        System.out.println(firstDamage);
        System.out.println("" + this.battle.getHeroOnTurn().getRemainingHP());
        System.out.println(this.battle.getHeroOnTurn().getCurrentBar());
        battle.setFoeOnTurn(battle.getEnemies().getAliveFoes().get(0));
        final DialogueInterface secndDamage  = battle.attack(true);
        secndDamage.generate();
        System.out.println(this.battle.getHeroOnTurn().getCurrentBar());
        this.battle.getHeroOnTurn().setCurrentBar(100);
        System.out.println(battle.specialAttack());
        assertEquals(this.battle.getEnemies().getAliveFoes().size(), 0);
        assertTrue(this.battle.isOver());
        battle.acquireExp().generate();
        System.out.println(battle.getOutCome());
        
    }
    
    /**
     * Method to test other features of Battle.
     * @throws NoWeaponException 
     */
    @Test
    public void testOtherDynamics() throws NoWeaponException {
        try {
            this.setTeams();
        } catch (IllegalArgumentException | MaxHeroException | MaxFoesException e) {
            fail("Errore di qualche tipo");
        }
        this.setBattle();
        System.out.println(battle.setHeroOnTUrn(battle.getSquad().getFirstHeroOnTurn()));
        
        try {
            System.out.println(battle.defend(battle.getSquad().getAliveHeroes().get(2)));
        } catch (NotDefendableException e) {
            System.out.println("Errore");
        }
        assertTrue(battle.getSquad().getAliveHeroes().get(2).isDefended());
        System.out.println(battle.setFoeOnTurn(battle.getEnemies().getFirstFoeOnTurn()));
        
        System.out.println(battle.getHeroOnTurn().getRemainingHP());
        System.out.println(battle.attack(false));
        System.out.println(battle.getHeroOnTurn().getAttack());
        try {
            battle.getHeroOnTurn().unsetWeapon();
            battle.getHeroOnTurn().setWeapon(WeaponFactory.getMazza());
        } catch (WeaponAlreadyException e) {
            e.printStackTrace();
        }
        System.out.println(battle.getHeroOnTurn().getWeapon().getPhysicalAtk());
        System.out.println(battle.getHeroOnTurn().getRemainingHP());
        assertFalse(battle.getSquad().getAliveHeroes().get(2).isDefended());
        System.out.println(battle.attack(true));
        System.out.println(this.battle.getEnemies().getAliveFoes());
        this.battle.getHeroOnTurn().addSpell(
                MagicGenerator.getMedium(this.battle.getHeroOnTurn().getJob()));
        try {
            this.battle.useMagicAttack(
                    MagicGenerator.getMedium(this.battle.getHeroOnTurn().getJob()),
                    this.battle.getFoeOnTurn(), true).generate();
        } catch (NotEnoughMPExcpetion e) {
            e.printStackTrace();
        } catch (MagicNotFoundException e) {
            fail("Magia non aggiunta correttamente!!");
        }
        System.out.println(this.battle.getEnemies().getAliveFoes());
        try {
            this.battle.useMagicAttack(
                    MagicGenerator.getMedium(this.battle.getHeroOnTurn().getJob()),
                    this.battle.getFoeOnTurn(), true).generate();
        } catch (NotEnoughMPExcpetion e) {
            e.printStackTrace();
        } catch (MagicNotFoundException e) {
            fail("Magia non aggiunta correttamente!!");
        }
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
            enemies.addFoe(new FoeImpl(1, "Quinto Nemico", "", FoesFindable.GNOMO_DA_GIARDINO));
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException!!");
            e.printStackTrace();
        } catch (MaxFoesException e) {
            e.printStackTrace();
        }
    }
}
