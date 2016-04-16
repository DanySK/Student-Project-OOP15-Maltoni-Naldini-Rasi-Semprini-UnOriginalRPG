package it.unibo.unori.model.battle;

import java.util.List;

import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.items.Weapon;

/**
 * The principal Class modeling the Battle Mode.
 * It implements Battle Interface.
 */
public class BattleImpl implements Battle {
    
    private final List<Character> squad;
    private final List<Character> enemies;
    
    /**
     * Standard constructor for Class BattleImpl.
     * @param team my team.
     * @param en a List of Enemies.
     */
    public BattleImpl(final List<Character> team, final List<Character> en) {
        this.squad = team;
        this.enemies = en;
    }
    
    private int getNextChar() {
        return 0;
    }
    
    private int getNextEnemy() {
        return 0;
    }
    
    @Override
    public void runAway() throws CantEscapeException {
        if (BattleLogics.canEscape(
                this.squad.get(this.getNextChar()).getLevel(),
                this.enemies.get(this.getNextEnemy()).getLevel())
            ) {
            //TODO end battle
        } else {
            throw new CantEscapeException();
        }
    }

    @Override
    public int attack(Character enemy) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String defend(Character character) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void openBag() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int specialAttack() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int weaponAttack(Weapon w) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int magicAttack() {
        // TODO Auto-generated method stub
        return 0;
    }

}
