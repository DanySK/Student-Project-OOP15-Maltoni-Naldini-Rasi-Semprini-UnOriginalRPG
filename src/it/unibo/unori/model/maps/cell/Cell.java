package it.unibo.unori.model.maps.cell;

import java.io.Serializable;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.exceptions.NoMapFoundException;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;

/**
 * 
 * A interface to define the basic method of a cell. A cell is the basic
 * component of a map, a map has a matrix composed of various cells Every cell
 * has a frame object inside , to represent the frame linked to the logical cell
 * The logical cell has a position , a state ,which can be occupied or free,and
 * various method to control it. Serializable interface is added in order to
 * make possible the saving of a cell on file.
 *
 */

public interface Cell extends Serializable {
    /**
     * Set a specific state on the cell.
     * 
     * @param state
     *            State to set on the cell
     */
    public void setState(CellState state);

    /**
     * Get the state of the cell.
     * 
     * @return the state of the Cell
     */
    public CellState getState();


    /**
     * Set a particular frame on the cell.
     * 
     * @param frame
     *            frame to set from the view
     */
    public void setFrame(Object frame);

    /**
     * Get the map contained in the cell , if present.
     * 
     * @return the map contained in the cell
     * @throws NoMapFoundException
     *             notify the type of Exception
     */
    public GameMap getCellMap() throws NoMapFoundException;

    /**
     * Return the frame associated with the Cell.
     * @return
     *          a frame Object
     */
    public Object getFrame();

    /**
     * Get the object positioned in a cell, if present.
     * @return the Object to add to the inventory of the party
     * @throws NoObjectFoundException if the Object to get is absent.
     */
    public Object getObject() throws NoObjectFoundException;

}
