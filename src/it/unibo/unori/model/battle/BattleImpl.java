package it.unibo.unori.model.battle;

import java.util.List;

import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.battle.exceptions.CharNotFoundException;
import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.items.Weapon;

/**
 * The principal Class modeling the Battle Mode.
 * It implements Battle Interface.
 */
public class BattleImpl implements Battle {
    
    private final List<Hero> squad;
    private final List<Hero> enemies;
    private boolean over;
    
    /**
     * Standard constructor for Class BattleImpl.
     * @param team my team.
     * @param en a List of Enemies.
     */
    public BattleImpl(final List<Hero> team, final List<Hero> en) {
        this.squad = team;
        this.enemies = en;
        this.over = false;
    }
    
    private void defeated(final Hero ch) {
        this.enemies.remove(ch);
    }
    
    private boolean isDefeated(final Hero ch) {
        return ch.getRemainingHP() == 0;
    }
    
    private void controlChar(final Hero ch) throws CharNotFoundException {
        if (!this.squad.contains(ch)) {
            throw new CharNotFoundException();
        }
    }
    
    @Override
    public void runAway(final Hero enemy, 
            final Hero my) throws CantEscapeException {
        try {
            this.controlChar(my);
        } catch (CharNotFoundException e) {
            e.printStackTrace();
        }
        if (BattleLogics.canEscape(my.getLevel(), enemy.getLevel())
            ) {
            this.over = true;
        } else {
            throw new CantEscapeException();
        }
    }

    @Override
    public int attack(final Hero enemy, final Hero my) {
        final int damage = 
                BattleLogics.getStandardDamage(my.getLevel(), my.getAttack());
        enemy.takeDamage(damage);
        if (this.isDefeated(enemy)) {
            this.defeated(enemy);
        }
        return damage;
    }

    @Override
    public String defend(final Hero character) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void openBag() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int specialAttack(final Hero my) {
        final int damage = 
                BattleLogics.specialAttackCalc(my.getLevel(), my.getAttack());
        this.enemies.forEach(e -> {
            e.takeDamage(damage);
            if (this.isDefeated(e)) {
                this.defeated(e);
            }
        });
        return damage;
    }

    @Override
    public int weaponAttack(final Weapon w, final Hero ch,
            final Hero enemy) {
        final int damage = 
                BattleLogics.weaponAttack(w.getDamage(), 
                        ch.getLevel(), ch.getAttack());
        enemy.takeDamage(damage);
        if (this.isDefeated(enemy)) {
            this.defeated(enemy);
        }
        return damage;
    }

    @Override
    public int magicAttack() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isOver() {
        return this.over;
    }

}
