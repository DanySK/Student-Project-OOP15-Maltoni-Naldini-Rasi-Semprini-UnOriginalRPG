package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.exceptions.CantUseException;
import it.unibo.unori.controller.exceptions.NotValidStateException;
import it.unibo.unori.controller.state.BattleState;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;
import it.unibo.unori.controller.state.InGameMenuState;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.exceptions.HeroDeadException;
import it.unibo.unori.model.items.exceptions.HeroNotDeadException;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;

/**
 * This ActionListener should be added to selection buttons in object use menus
 * (in {@link it.unibo.unori.view.layers.InGameMenuLayer} and
 * {@link it.unibo.unori.view.layers.BattleLayer}) for trigger the use/equipment
 * of the specified item on the specified hero.
 */
public class ObjectUseActionListener extends AbstractUnoriActionListener {
    private final Item itemUsed;
    private final Hero targetHero;

    /**
     * Default constructor.
     * 
     * @param item
     *            the item that the player chose to use/equip by pressing the
     *            button
     * @param target
     *            the hero that the player chose to use/equip the item on by
     *            pressing the button
     */
    public ObjectUseActionListener(final Item item, final Hero target) {
        super();
        this.itemUsed = item;
        this.targetHero = target;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        try {
            // If current state is a battle ...
            if (this.getController().getCurrentStateClass().equals(BattleState.class)
                    && Potion.class.isInstance(this.itemUsed)) {
                final BattleState currentState = (BattleState) this.getController().getCurrentState();
                currentState.getModel().usePotion(targetHero, (Potion) itemUsed);
                // TODO dialog
                // ... else if it is not ...
            } else if (this.getController().getCurrentStateClass().equals(InGameMenuState.class)) {
                final InGameMenuState currentState = (InGameMenuState) this.getController().getCurrentState();
                if (Potion.class.isInstance(this.itemUsed)) {
                    if (currentState.getBag().contains(this.itemUsed)) {
                        ((Potion) this.itemUsed).using(this.targetHero);
                        // TODO new Dialogue("Hai usato " + toUse.getName() + "
                        // su " + my.getName() + "!");
                    } else {
                        throw new ItemNotFoundException();
                    }
                } else if (Armor.class.isInstance(this.itemUsed)) {
                    try {
                        this.targetHero.unsetArmor(((Armor) this.itemUsed).getArmorClass());
                    } catch (NoArmorException e) {
                        /*
                         * If this exception is thrown, it's not an error, but
                         * simply the hero has no Armor of that ArmorPiece
                         * equipped.
                         */
                    }
                    try {
                        this.targetHero.setArmor(((Armor) this.itemUsed));
                    } catch (ArmorAlreadyException e) {
                        /*
                         * If after armor removal it can't already equip the
                         * armor, there is a problem.
                         */
                        throw new CantUseException(
                                "Impossibile rimuovere l'armatura precedente e settare quella attuale. Riprovare");
                    }
                } else if (Weapon.class.isInstance(this.itemUsed)) {
                    try {
                        this.targetHero.unsetWeapon();
                    } catch (NoWeaponException e) {
                        /*
                         * If this exception is thrown, it's not an error, but
                         * simply the hero has no Weapon equipped.
                         */
                    }
                    try {
                        this.targetHero.setWeapon((Weapon) this.itemUsed);
                    } catch (WeaponAlreadyException e) {
                        /*
                         * If after armor removal it can't already equip the
                         * weapon, there is a problem.
                         */
                        throw new CantUseException(
                                "Impossibile rimuovere l'arma precedente e settare quella attuale. Riprovare");
                    }
                } else {
                    throw new CantUseException();
                }
            } else {
                throw new NotValidStateException();
            }
        } catch (CantUseException | HeroDeadException | HeroNotDeadException e) {
            System.out.println("Error in ObjectUseActionListener:");
            this.getController().showError(e.getMessage(), ErrorSeverity.MINOR);
            e.printStackTrace();
        } catch (NotValidStateException | ItemNotFoundException e) {
            System.out.println("Error in ObjectUseActionListener:");
            this.getController().showError(e.getMessage(), ErrorSeverity.SERIUOS);
            e.printStackTrace();
        }
    }

    /**
     * Returns the item the button that implements this ActionListener is
     * associated to.
     * 
     * @return the item
     */
    public Item getItemUsed() {
        return itemUsed;
    }

    /**
     * Returns the hero the button that implements this ActionListener is
     * associated to.
     * 
     * @return the hero
     */
    public Hero getTargetHero() {
        return targetHero;
    }
}
