package it.unibo.unori.model.character.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.unori.controller.json.FoeSetup;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Weapon;

/**
 * Enumeration that defines the types of Foes findable.
 *
 */
public enum FoesFindable {
    
    /**
     * Types of Foes.
     * Folletto.
     */
    FOLLETTO(Status.NONE, FoeSetup.DEFAULT),
    
    /**
     * Gnomo da giardino.
     */
    GNOMO_DA_GIARDINO(Status.ASLEEP, FoeSetup.DEFAULT),
    
    /**
     * Demone.
     */
    DEMONE(Status.BLEEDING, FoeSetup.DEFAULT),
    
    /**
     * Drago.
     */
    DRAGO(Status.POISONED, FoeSetup.DEFAULT),
    
    /**
     * Spirito.
     */
    SPIRITO(Status.CURSED, FoeSetup.DEFAULT),
    
    /**
     * Bamabino.
     */
    BAMBINO(Status.BLEEDING, FoeSetup.DEFAULT),
    
    /**
     * Stregone.
     */
    STREGONE(Status.CURSED, FoeSetup.DEFAULT),
    
    /**
     * Eroe caduto.
     */
    EROE_CADUTO(Status.ASLEEP, FoeSetup.DEFAULT);
    
    private final Status immunity;
    private final Map<Statistics, Integer> map;
    private final List<MagicAttackInterface> magics = new ArrayList<>();
    private final Weapon weap;
    
    FoesFindable(final Status immune, final String filePath) {
        FoeSetup js = new FoeSetup();
        this.immunity = immune;
        this.map = js.getBasicStats(filePath);
        this.magics.add(js.getBasicMagic(filePath));
        this.weap = js.getBasicWeapon(filePath);
    }
    
    FoesFindable(final Status immune) {
        this.immunity = immune;
        this.map = FoesFactory.getBasicStats();
        this.magics.add(FoesFactory.getBasicMag());
        this.weap = FoesFactory.getBasicWeap();
    }
    
    /**
     * Getter method that returns the immunity of the Foe.
     * @return the immunity Status.
     */
    public Status getImmunity() {
        return this.immunity;
    }
    
    /**
     * Getter method that returns the basic Map of Statistics for this Foe.
     * @return a Map of Statistics, Integer. Defensive copy.
     */
    public Map<Statistics, Integer> getMap() {
        return new HashMap<>(map);
    }
    
    /**
     * Getter method that returns the basic Weapon for the type of Foe.
     * @return the basic Weapon equipped.
     */
    public Weapon getWeap() {
        return weap;
    }
    
    /**
     * Getter method that returns the list of magic for the type of Foe.
     * @return the list of magics. Defensive copy.
     */
    public List<MagicAttackInterface> getMagic() {
        return new ArrayList<>(this.magics);
    }
}
