package it.unibo.unori.model.maps.cell;

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
        return new CellImpl(new Object(), CellState.FREE);
    }
}
