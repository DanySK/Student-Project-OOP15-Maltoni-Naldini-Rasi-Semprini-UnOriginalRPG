package it.unibo.unori.model.maps;

import java.util.ArrayList;
import java.util.Arrays;
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
        this.floorMap = new Cell[STDCELLS][STDCELLS];
        this.initializeMap();
    }

    /**
     * Constructor for specified width and length maps.
     * @param width
     *              width of the map to build
     * @param length
     *              length of the map to build
     */
    public GameMapImpl(final int width, final int length) {
        this.floorMap = new Cell[width][length];
        this.initializeMap();
    }


    /**
     * private method to initialize the map
     * @param size
     *              the size of the row of the matrix
     */ 
    private void initializeMap() {
        for (int i = 0; i < this.floorMap.length; i++) {
            this.floorMap[i] = Stream.generate(new CellFactory() :: getFreeCell)
                                     .limit(this.floorMap[0].length)
                                     .toArray(Cell[] :: new);
        }

    }

    /**
     * Method to compare two length.
     * @param pos1
     *              the first position
     * @param pos2
     *              the second position
     * @return true if pos1 is greater than pos2, or if pos1 is lower than 0
     */
    private boolean checkPosition(final int pos1, final int pos2) {
        return (pos1 >= pos2 || pos1 < 0);
    }

    @Override
    public Cell getCell(final int posX, final int posY) throws IllegalArgumentException {
        if (checkPosition(posX, this.floorMap.length) 
                || checkPosition(posY, this.floorMap[0].length)) {
            throw new IllegalArgumentException();
        }
        return this.floorMap[posX][posY];
    }

    @Override
    public void setCell(final int posX, final int posY, final Cell cell) throws IllegalArgumentException {
        if (checkPosition(posX, this.floorMap.length) 
                || checkPosition(posY, this.floorMap[0].length)) {
            throw new IllegalArgumentException();
        }
        this.floorMap[posX][posY] = cell;

    }

    @Override
    public List<Cell> getRow(final int posX) throws IllegalArgumentException {
        if (checkPosition(posX, this.floorMap.length)) {
            throw new IllegalArgumentException();
        }
        return Arrays.asList(this.floorMap[posX]);
    }

    @Override
    public List<Cell> getColumn(final int posY) throws IllegalArgumentException {
        if (checkPosition(posY, this.floorMap[0].length)) {
            throw new IllegalArgumentException();
        }
        final List<Cell> list = new ArrayList<>();
        for (int i = 0; i < this.floorMap.length; i++) {
           list.add(this.floorMap[i][posY]); 
        }
        return list;
    }

}
