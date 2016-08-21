package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.exceptions.CantUseException;
import it.unibo.unori.controller.exceptions.UnexpectedStateException;
import it.unibo.unori.controller.state.BattleState;
import it.unibo.unori.controller.state.InGameMenuState;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.Bag;
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
    private final Bag sourceBag; // TODO check

    /**
     * Default constructor.
     * 
     * @param itemUsed
     *            the item that the player chose to use/equip by pressing the
     *            button
     * @param targetHero
     *            the hero that the player chose to use/equip the item on by
     *            pressing the button
     * @param sourceBag
     *            the bag the item to use is
     */
    public ObjectUseActionListener(final Item itemUsed, final Hero targetHero, final Bag sourceBag) {
        super();
        this.itemUsed = itemUsed;
        this.targetHero = targetHero;
        this.sourceBag = sourceBag; // TODO check
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        try {
            // If current state is a battle ...
            if (BattleState.class.isInstance(this.getController().getCurrentState())
                    && Potion.class.isInstance(this.itemUsed)) {
                final BattleState currentState = (BattleState) this.getController().getCurrentState();
                currentState.usePotion(targetHero, (Potion) itemUsed);
                // TODO dialog
                // ... else if it is not ...
            } else if (InGameMenuState.class.isInstance(this.getController().getCurrentState())) {
                final InGameMenuState currentState = (InGameMenuState) this.getController().getCurrentState();
                if (Potion.class.isInstance(this.itemUsed)) {
                    if (currentState.getBag().contains(this.itemUsed)) {
                        ((Potion) this.itemUsed).using(this.targetHero);
                        currentState.getBag().removeItem(itemUsed);
                    } else {
                        throw new ItemNotFoundException();
                    }
                } else if (Armor.class.isInstance(this.itemUsed)) {
                    try {
                        // Tries to set the armor
                        this.targetHero.setArmor((Armor) this.itemUsed);
                    } catch (ArmorAlreadyException e1) {
                        // If it can't, it stores current in bag and retries
                        final ArmorPieces piece = ((Armor) this.itemUsed).getArmorClass();
                        this.sourceBag.storeItem(this.targetHero.getArmor(piece)); // TODO check
                        try {
                            this.targetHero.unsetArmor(piece);
                            this.targetHero.setArmor((Armor) itemUsed);
                        } catch (NoArmorException | ArmorAlreadyException e) {
                            // If it can't set it again, there's a problem
                            this.getController().showError(e.getMessage());
                        }
                        this.sourceBag.removeItem(itemUsed);
                    }
                } else if (Weapon.class.isInstance(this.itemUsed)) {
                    try {
                        // Tries to set the weapon
                        this.targetHero.setWeapon((Weapon) this.itemUsed);
                    } catch (WeaponAlreadyException e) {
                        // If it can't, it stores current in bag and retries
                        try {
                            this.sourceBag.storeItem(this.targetHero.getWeapon());
                            this.targetHero.unsetWeapon();
                            this.targetHero.setWeapon((Weapon) this.itemUsed);
                        } catch (NoWeaponException | WeaponAlreadyException e1) {
                            // If it can't set it again, there's a problem
                            this.getController().showError(e.getMessage());
                        }
                        this.sourceBag.removeItem(itemUsed);
                    }
                } else {
                    throw new CantUseException();
                }
            } else {
                throw new UnexpectedStateException();
            }
        } catch (CantUseException | HeroDeadException | HeroNotDeadException e) {
            this.getController().showCommunication(e.getMessage());
        } catch (UnexpectedStateException | ItemNotFoundException e) {
            this.getController().showError(e.getMessage());
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
