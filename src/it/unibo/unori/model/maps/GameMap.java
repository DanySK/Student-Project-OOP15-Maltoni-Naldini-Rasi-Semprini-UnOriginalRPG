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
     * Getter for length of the map.
     * @return length of the map
     */
    int getMapLength();
    /**
     * Getter for width of the map.
     * @return width of the map
     */
    int getMapWidth();
    /**
     * Get the cell at the specified position.
     * @param pos
     *              position of the cell to get
     * @return the cell at the specified coordinates
     * @throws IllegalArgumentException if the coordinates are invalid 
     */
    Cell getCell(Position pos) throws IllegalArgumentException;

    /**
     * Set the Cell at the specified position.
     * @param pos
     *              position of the cell to set
     *@param cell
     *              cell to set
     * @throws IllegalArgumentException if the coordinates are invalid 
     */
    void setCell(Position pos, Cell cell) throws IllegalArgumentException;

    /**
     * Get, as list, the row specified.
     * @param posX
     *              the row to get
     * @return a list of cell
     * @throws IllegalArgumentException if the row does not exist
     */
    List<Cell> getRow(int posX) throws IllegalArgumentException;

    /**
     * Get, as list , the column specified.
     * @param posY
     *             the column to get
     * @return a list of cell
     * @throws IllegalArgumentException if the column does not exist 
     */
    List<Cell> getColumn(int posY) throws IllegalArgumentException;

    /**
     * Set an entire row with an instance of a Cell.
     *@param posX
     *              the row to set 
     *@param cell
     *              the cell to set 
     *@throws IllegalArgumentException if the row index is referred to a non existing row
     */
    void setRow(int posX, Cell cell) throws IllegalArgumentException;

    /**
     * Set an entire  column with an instance of a Cell.
     * @param posY
     *          the column to set
     * @param cell
     *          the cell to set
     * @throws IllegalArgumentException if the row index is referred to a non existing column
     */
    void setColumn(int posY, Cell cell)throws IllegalArgumentException;

    /**
     * Set the position of the initial cell.
     * @param initialCell
     *                  position of the initial cell
     * @throws IllegalArgumentException if the value does not belong to the map
     */
    void setInitialCellPosition(Position initialCell) throws IllegalArgumentException;

    /**
     * method to return the position of the initial cell.
     * @return position of the initial cell 
     */
    Position getInitialCellPosition();

    /**
     * Replace the cell in the first position with the cell in the second position.
     * @param toRemove
     *          position of the cell to remove
     * @param toSet
     *          position of the cell to set
     * @throws IllegalArgumentException if any of the position is invalid
     */
    void replaceCell(Position toRemove, Position toSet) throws IllegalArgumentException;

    /**
     * Method to get the resource locator for each cell in the map.
     * @return
     *          A matrix containing the paths
     */
    String[][] getFrames();


}
