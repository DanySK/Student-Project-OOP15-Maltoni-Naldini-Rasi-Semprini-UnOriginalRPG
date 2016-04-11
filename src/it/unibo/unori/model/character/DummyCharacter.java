package it.unibo.unori.model.character;

import it.unibo.unori.model.menu.DummyMenu;

public class DummyCharacter implements Character {
/**
     * 
     */
    private static final long serialVersionUID = -1306119386793011379L;

/**
 * something.
 * @return something.
 */
    public DummyMenu createMenu() {
        return new DummyMenu();
    }

@Override
public int getRemainingHP() {
    // TODO Auto-generated method stub
    return 0;
}

@Override
public int getTotalHP() {
    // TODO Auto-generated method stub
    return 0;
}

@Override
public int getAttack() {
    // TODO Auto-generated method stub
    return 0;
}

@Override
public int getDefense() {
    // TODO Auto-generated method stub
    return 0;
}

@Override
public int getMagicAtk() {
    // TODO Auto-generated method stub
    return 0;
}

@Override
public int getMagicDef() {
    // TODO Auto-generated method stub
    return 0;
}

@Override
public int getSpeed() {
    // TODO Auto-generated method stub
    return 0;
}

@Override
public int getLevel() {
    // TODO Auto-generated method stub
    return 0;
}

@Override
public int getExpTot() {
    // TODO Auto-generated method stub
    return 0;
}

@Override
public void addExp(int expAcquired) {
    // TODO Auto-generated method stub
    
}

@Override
public int getRemainingExp() {
    // TODO Auto-generated method stub
    return 0;
}

}
