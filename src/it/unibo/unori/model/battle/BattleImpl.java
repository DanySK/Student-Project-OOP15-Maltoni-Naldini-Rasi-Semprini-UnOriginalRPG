package it.unibo.unori.model.battle;

import java.util.List;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.items.Weapon;

/**
 * The principal Class modeling the Battle Mode.
 * It implements Battle Interface.
 */
public class BattleImpl implements Battle {
    
    private List<Character> squad;
    
    /**
     * Standard constructor for Class BattleImpl.
     * @param team my team.
     */
    public BattleImpl(final List<Character> team) {
        this.squad = team;
    }
    
    @Override
    public void runAway() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int attack(Object enemy) {
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
