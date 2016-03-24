package it.unibo.unori.model.maps;

import java.io.Serializable;
import java.util.List;

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

    /**
     * Get, as list, the row specified.
     * @param posX
     *              the row to get
     * @return a list of cell
     * @throws IllegalArgumentException if the row does not exist
     */
    public List<Cell> getRow(int posX) throws IllegalArgumentException;
    
    /**
     * Get , as list , the column specified.
     * @param posY
     *             the column to get
     * @return a list of cell
     * @throws IllegalArgumentException if the column does not exist 
     */
    public List<Cell> getColumn(int posY) throws IllegalArgumentException;

}
