package it.unibo.unori.model.battle;

import java.util.List;

import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.character.exceptions.CharNotFoundException;
import it.unibo.unori.model.items.Weapon;

/**
 * The principal Class modeling the Battle Mode.
 * It implements Battle Interface.
 */
public class BattleImpl implements Battle {
    
    private final List<Character> squad;
    private final List<Character> enemies;
    private boolean over;
    
    /**
     * Standard constructor for Class BattleImpl.
     * @param team my team.
     * @param en a List of Enemies.
     */
    public BattleImpl(final List<Character> team, final List<Character> en) {
        this.squad = team;
        this.enemies = en;
        this.over = false;
    }
    
    private void defeated(final Character ch) {
        this.enemies.remove(ch);
    }
    
    private boolean isDefeated(final Character ch) {
        return ch.getRemainingHP() == 0;
    }
    
    private void controlChar(final Character ch) throws CharNotFoundException {
        if (!this.squad.contains(ch)) {
            throw new CharNotFoundException();
        }
    }
    
    @Override
    public void runAway(final Character enemy, 
            final Character my) throws CantEscapeException {
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
    public int attack(final Character enemy, final Character my) {
        final int damage = 
                BattleLogics.getStandardDamage(my.getLevel(), my.getAttack());
        enemy.attacking(damage);
        if (this.isDefeated(enemy)) {
            this.defeated(enemy);
        }
        return damage;
    }

    @Override
    public String defend(final Character character) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void openBag() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int specialAttack(final Character my) {
        final int damage = 
                BattleLogics.specialAttackCalc(my.getLevel(), my.getAttack());
        this.enemies.forEach(e -> {
            e.attacking(damage);
            if (this.isDefeated(e)) {
                this.defeated(e);
            }
        });
        return damage;
    }

    @Override
    public int weaponAttack(final Weapon w, final Character ch,
            final Character enemy) {
        final int damage = 
                BattleLogics.weaponAttack(w.getDamage(), 
                        ch.getLevel(), ch.getAttack());
        enemy.attacking(damage);
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
