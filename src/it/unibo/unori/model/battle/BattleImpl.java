package it.unibo.unori.model.battle;

import java.util.List;

import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;

/**
 * The principal Class modeling the Battle Mode.
 * It implements Battle Interface.
 */
public class BattleImpl implements Battle {

    private final List<Hero> squad;
    private final List<Foe> enemies;
    private boolean over;
    private int beatenFriends;
    private final Bag itemBag;
    
    /**
     * Standard constructor for Class BattleImpl.
     * @param team my team.
     * @param en a List of Enemies.
     * @param bag the Item Bag.
     */
    public BattleImpl(final List<Hero> team, final List<Foe> en, final Bag bag) {
        this.squad = team;
        this.enemies = en;
        this.itemBag = bag;
        this.over = false;
        this.beatenFriends = 0;
    }
    
    private Character defeated(final Character ch) {
        if (ch instanceof Foe) {
            this.enemies.remove(ch);
        } else if (ch instanceof Hero) {
            this.beatenFriends++;
            this.squad.remove(ch);
        }
        return ch;
    }
    
    private boolean isDefeated(final Character ch) {
        return ch.getRemainingHP() == 0;
    }
    
    private int getMediumLevel() {
        int mediumLevel = 0;
        for (final Foe h : this.enemies) {
            mediumLevel += h.getLevel();
        }
        mediumLevel /= this.enemies.size();
        return mediumLevel;
    }
    
    @Override
    public String runAway(final Foe enemy, 
            final Hero my) throws CantEscapeException {
        if (BattleLogics.canEscape(my.getLevel(), enemy.getLevel())) {
            this.over = true;
            return "Sei riuscito a fuggire!";
        } else {
            throw new CantEscapeException();
        }
    }

    @Override
    public int attack(final Foe enemy, final Hero my) {
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
    public int usePotion(final Hero my, final Potion toUse) throws ItemNotFoundException {
        if (this.itemBag.contains(toUse)) {
            my.restoreDamage(toUse.getRestore());
            return toUse.getRestore();
        } else {
            throw new ItemNotFoundException();
        }
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
    public int useMagicAttack(final MagicAttack m, final Hero my, final Foe enemy) {
        //TODO Mana Points are enough? if yes then...
        my.consumeMP(m.getMPRequired());
        //TODO A lot of things.
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
        int index = 0;
        for (final Hero h : this.squad) {
            h.addExp(points.get(index));
            index++;
        }
    }

    @Override
    public List<Foe> getEnemies() {
        return this.enemies;
    }

    @Override
    public List<Hero> getSquad() {
        return this.squad;
    }
}
