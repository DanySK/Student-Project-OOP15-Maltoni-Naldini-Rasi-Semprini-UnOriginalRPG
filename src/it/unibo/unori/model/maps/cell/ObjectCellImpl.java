package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;

/**
 * Cell Implementation to handle a collectable Gameobject. 
 *
 */
public class ObjectCellImpl extends SimpleCellImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1077983786031256996L;
    private final Item obj;

    /**
     * Constructor.
     * @param path
     *              path of frame to set in the cell
     * @param obj
     *              object to set in the cell
     */
    public ObjectCellImpl(final String path, final Item obj) {
        super(path, CellState.BLOCKED);
        this.obj = obj;
    }

    /**
     * Override the getObject method of SimpleCellImpl.
     */
    @Override
    public Item getObject() throws NoObjectFoundException {
        return this.obj;
    }


}
