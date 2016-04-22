package it.unibo.unori.model.character;

/**
 * Class to design a generic Character.
 *
 */
public class CharacterImpl implements Character {

    /**
     * 
     */
    private static final long serialVersionUID = -95447626445744515L;


    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
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
    public void consumeMP(int mpToConsume) {
        // TODO Auto-generated method stub

    }

    @Override
    public void takeDamage(int damage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void restoreDamage(int hpToRestore) {
        // TODO Auto-generated method stub

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
    public int getFireDefense() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getThunderDefense() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getIceDefense() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getFireAtk() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getThunderAttack() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getIceAttack() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Enumeration with names of statistics.
     *
     */
    public enum Statistics {
        /**
         * Names of the statistics.
         */
        TOTALHP, TOTALMP, FIREATK, FIREDEF, THUNDERATK, THUNDERDEF, ICEATK, ICEDEF
    }
}


