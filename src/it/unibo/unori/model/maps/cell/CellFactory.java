package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.items.WeaponFactory;

/**
 * 
 * A Factory class to create the most common kind of Cells.
 * It has only a few methods, in order to build automatically a few kind of cells
 *
 */
public class CellFactory {

    /**
     * create and return a Free cell object.
     * @return
     *        a standard free cell 
     */
    public Cell getFreeCell() {
        return new SimpleCellImpl("", CellState.FREE);
    }

    /**
     * create and return a Blocked cell object.
     * @return
     *         a standard blocked cell object
     */
    public  Cell getBlockedCell() {
        return new SimpleCellImpl("", CellState.BLOCKED);
    }

    /**
     * Create and return a Cell with a object inside.
     * @return
     *          a  cell containing a sword
     */
    public Cell getObjectCell() {
        return new ObjectCellImpl(WeaponFactory.getStdSword());
    }

    /**
     * Create a chest cell.
     * @return
     *          a simple chest cell, containing a nail
     */
    public Cell getChestCell() {
        return new ChestCellImpl("", WeaponFactory.getChiodo());
    }
}
