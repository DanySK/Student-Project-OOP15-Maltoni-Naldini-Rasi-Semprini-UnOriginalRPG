package it.unibo.unori.model.battle;

import java.util.List;

import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Weapon;

/**
 * The principal Class modeling the Battle Mode.
 * It implements Battle Interface.
 */
public class BattleImpl implements Battle {
    
    private final List<Hero> squad;
    private final List<Hero> enemies;
    private boolean over;
    private int beatenFriends;
    private Bag itemBag;
    
    /**
     * Standard constructor for Class BattleImpl.
     * @param team my team.
     * @param en a List of Enemies.
     * @param bag the Item Bag.
     */
    public BattleImpl(final List<Hero> team, final List<Hero> en, final Bag bag) {
        this.squad = team;
        this.enemies = en;
        this.itemBag = bag;
        this.over = false;
        this.beatenFriends = 0;
    }
    
    private void defeated(final Hero ch) {
        this.enemies.remove(ch);
    }
    
    private boolean isDefeated(final Hero ch) {
        return ch.getRemainingHP() == 0;
    }
    
    private int getMediumLevel() {
        int mediumLevel = 0;
        for (final Hero h : this.enemies) {
            mediumLevel += h.getLevel();
        }
        mediumLevel /= this.enemies.size();
        return mediumLevel;
    }
    
    @Override
    public String runAway(final Hero enemy, 
            final Hero my) throws CantEscapeException {
        if (BattleLogics.canEscape(my.getLevel(), enemy.getLevel())) {
            this.over = true;
            return "Sei riuscito a fuggire!";
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
    
    @Override
    public void acquireExp() {
        final List<Integer> points =
                BattleLogics.expAcquired(this.squad, this.getMediumLevel(),
                        this.squad.size() - this.beatenFriends);
        //TODO use Streams instead.
        final int index = 0;
        for (final Hero h : this.squad) {
            h.addExp(points.get(index));
        }
    }

}
