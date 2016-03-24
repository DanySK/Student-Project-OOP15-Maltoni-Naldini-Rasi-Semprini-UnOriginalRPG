package it.unibo.unori.model.maps;

import java.util.List;
import java.util.stream.Stream;

import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellFactory;

/**
 * 
 *Class to create a generic Map Object to customize later.
 *Every map in the game is a instance of this class
 *The map contains a matrix of Cell object, which represent the physical map for 
 *the party to walk in.
 *
 */
public class GameMapImpl implements GameMap {

    /**
     * 
     */
    private static final long serialVersionUID = -887928696341560842L;
    private static final int STDCELLS = 100; 
    private final Cell[][] floorMap;

    /**
     * Constructor for a standard map.
     * the standard dimension of a map is 100 x 100
     */
    public GameMapImpl() {
        this.floorMap = new Cell[100][100];
        this.initializeMap(STDCELLS);
    }

    @SuppressWarnings("unused")
    /**
     * private method to initialize the map
     * @param size
     *              the size of the row of the matrix
     */
    private void initializeMap(final int size) {
        for (Cell[] elem : this.floorMap) {
            elem = Stream.generate(new CellFactory() :: getFreeCell).limit(size)
                                                    .toArray(Cell[] :: new);
        }
    }
    
    public GameMapImpl(final int width, final int length) {
        this.floorMap = new Cell[length][width];
        this.initializeMap(length);
    }

    @Override
    public Cell getCell(int posX, int posY) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCell(int posX, int posY, Cell cell) throws IllegalArgumentException {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Cell> getRow(int posX) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Cell> getColumn(int posY) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

}
