package it.unibo.unori.model.maps;

import java.io.Serializable;

import it.unibo.unori.model.maps.cell.Cell;

/**
 * 
 * Interface to contains the basic methods for a game-map.
 * Serializable interface is added in order to save Map-Object on file
 *
 */
public interface GameMap extends Serializable {

    /**
     * Set the dimension of the current map. 
     * @param width
     *              the width of map in terms of number of cells
     * @param length
     *               the length of map in terms of number of cells
     */
    public void setDimension(int width, int length);

    /**
     * Get the cell at the specified position.
     * @param posX
     *              X coordinate of the cell to get
     * @param posY
     *              X coordinate of the cell to get
     * @return the cell at the specified coordinates
     * @throws IllegalArgumentException if the coordinates are invalid 
     */
    public Cell getCell(int posX, int posY) throws IllegalArgumentException;

    /**
     * Set the param Cell at the specified position.
     * @param posX
     *              X coordinate of the cell to get
     * @param posY
     *              X coordinate of the cell to get
     *@param cell
     *              cell to set
     * @throws IllegalArgumentException if the coordinates are invalid 
     */
    public void setCell(int posX, int posY, Cell cell) throws IllegalArgumentException;

}
