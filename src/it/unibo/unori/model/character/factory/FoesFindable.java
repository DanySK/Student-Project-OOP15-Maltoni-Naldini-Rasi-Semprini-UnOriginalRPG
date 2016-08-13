package it.unibo.unori.model.character.factory;

import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.Status;

public enum FoesFindable {
    
    FOLLETTO(1, "Folletto", Status.NONE, "");
    
    private final Foe f;
    
    private FoesFindable(final int ia, final String name, final Status immunity,
            final String battleFrame) {
        if(ia == 1) {
            this.f = new FoeImpl(ia, name, battleFrame, FoesFactory.getBasicStats().getY(),
                    FoesFactory.getBasicStats().getX(), immunity);
        } else {
            this.f = new FoeImpl(ia, name, battleFrame, FoesFactory.getGrowingStats(ia).getY(),
                FoesFactory.getGrowingStats(ia).getX(), immunity);
        }
    }
    
    @Override
    public String toString() {
        return this.f.getName();
    }
    
    public Foe getThis() {
        return this.f;
    }
}
