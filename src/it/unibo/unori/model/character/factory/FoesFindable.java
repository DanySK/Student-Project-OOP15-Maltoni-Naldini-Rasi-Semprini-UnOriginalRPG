package it.unibo.unori.model.character.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibo.unori.controller.json.FoeSetup;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Weapon;

public enum FoesFindable {
    
    FOLLETTO(Status.NONE),
    GNOMO_DA_GIARDINO(Status.ASLEEP),
    DEMONE(Status.BLEEDING),
    DRAGO(Status.POISONED),
    SPIRITO(Status.CURSED),
    BAMBINO(Status.BLEEDING),
    STREGONE(Status.CURSED),
    EROE_CADUTO(Status.ASLEEP);
    
    private final Status immunity;
    private final Map<Statistics, Integer> map;
    private final List<MagicAttackInterface> magics = new ArrayList<>();
    private final Weapon weap;
    
    private FoesFindable(final Status immunity, final String filePath) {
        FoeSetup js = new FoeSetup();
        
        this.immunity = immunity;
        this.map = js.getBasicStats(filePath);
        this.magics.add(js.getBasicMagic(filePath));
        this.weap = js.getBasicWeapon(filePath);
    }
    
    private FoesFindable(final Status immunity) {
        this.immunity = immunity;
        this.map = FoesFactory.getBasicStats();
        this.magics.add(FoesFactory.getBasicMag());
        this.weap = FoesFactory.getBasicWeap();
    }
    
    public Status getImmunity() {
        return this.immunity;
    }

    public Map<Statistics, Integer> getMap() {
        return map;
    }

    public Weapon getWeap() {
        return weap;
    }
    
    public List<MagicAttackInterface> getMagic() {
        return this.magics;
    }
}
