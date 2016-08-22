package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;
import it.unibo.unori.model.maps.exceptions.NoKeyFoundException;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;

/**
 * Class which design a cell with a chest inside in the game, 
 * which has a object inside and it need a key to be opened.
 * It has two fields: one to contain the object and one to signal if the object
 * has already been picked up.
 *
 */
public class ChestCellImpl extends SimpleCellImpl {

    /**
     * 
     */
    private static final long serialVersionUID = -7296644040007976176L;
    private static final int PRIME = 31;
    private final Item o;
    private boolean hasItem;

    /**
     * Constructor for the cell.
     * @param o
     *              object to put in the cell
     */
    public ChestCellImpl(final Item o) {
        super("res/sprites/map/chest.png", CellState.BLOCKED);
        this.o = o;
        this.hasItem = true;
    }

    @Override
    public Item openChest(final Bag b) throws NoObjectFoundException, 
                                              NoKeyFoundException,
                                              ItemNotFoundException {
        if (this.hasItem) {
            if (!b.containsKey()) {
                throw new NoKeyFoundException();
            }
            b.removeItem(ItemImpl.KEY);
            this.hasItem = false;
            return o;
        } else {
            throw new NoObjectFoundException();
    }

   }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + ((o == null) ? 0 : o.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof ChestCellImpl)) {
            return false;
        }
        final ChestCellImpl other = (ChestCellImpl) obj;
        if (o == null) {
            return other.o == null;
        } else {
            return o.equals(other.o); 
        }
    }
}
