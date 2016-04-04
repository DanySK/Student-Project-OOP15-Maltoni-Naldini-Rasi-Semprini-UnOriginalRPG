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

    /**
     * Set an entire row with an instance of a Cell.
     * it may be useful to set the border cell of the map
     * @param posX
     *              the row to set 
     *@param cell
     *              the cell to set 
     *@throws IllegalArgumentException if the row index is referred to a non existing row
     */
    public void setRow(int posX, Cell cell) throws IllegalArgumentException;

    /**
     * Set an entire  column with an instance of a Cell.
     * @param posY
     *          the column to set
     * @param cell
     *          the cell to set
     * @throws IllegalArgumentException if the row index is referred to a non existing column
     */
    public void setColumn(int posY, Cell cell)throws IllegalArgumentException;

    /**
     * Set the X coordinate of the initial cell.
     * @param initialX
     *                  value of the X coordinate to set
     * @param initialY
     *                  value of the Y coordinate to set
     * @throws IllegalArgumentException if the value does not belong to the map
     */
    public void setInitialCell(int initialX, int initialY) throws IllegalArgumentException;

    /**
     * method to return the X coordinate of the initial cell.
     * @return X coordinate of the initial cell 
     */
    public int getInitialX();

    /**
     *  method to return the Y coordinate of the initial cell.
     * @return Y coordinate of the initial cell 
     */
    public int getInitialY();

}
