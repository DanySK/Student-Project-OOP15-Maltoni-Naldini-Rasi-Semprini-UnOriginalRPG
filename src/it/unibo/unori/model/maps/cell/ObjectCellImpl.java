package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;

/**
 * Extends SimpleCellImpl, handle a object on the map. 
 *
 */
public class ObjectCellImpl extends SimpleCellImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1077983786031256996L;
    private static final int PRIME = 31;
    private final Item obj;

    /**
     * Constructor.
     * @param obj
     *              object to set in the cell
     */
    public ObjectCellImpl(final Item obj) {
        super("res/sprites/map/item.png", CellState.BLOCKED);
        this.obj = obj;
    }

    @Override
    public Item getObject() throws NoObjectFoundException {
        return this.obj;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + ((obj == null) ? 0 : obj.hashCode());
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
        if (!(obj instanceof ObjectCellImpl)) {
            return false;
        }
        final ObjectCellImpl other = (ObjectCellImpl) obj;
        if (this.obj == null) {
            return other.obj == null;
        } else  {
            return this.obj.equals(other.obj);
        }
    }


}
