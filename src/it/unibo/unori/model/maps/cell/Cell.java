package it.unibo.unori.model.maps.cell;

import java.io.Serializable;

import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.exceptions.NoKeyFoundException;
import it.unibo.unori.model.maps.exceptions.NoMapFoundException;
import it.unibo.unori.model.maps.exceptions.NoNPCFoundException;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;
import it.unibo.unori.model.menu.Dialogue;

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
    void setState(CellState state);

    /**
     * Get the state of the cell.
     * 
     * @return the state of the Cell
     */
    CellState getState();


    /**
     * Set a particular frame on the cell.
     * 
     * @param path
     *            path of frame to set from the view
     */
    void setFrame(String path);


    /**
     * Return the path of frame associated with the Cell.
     * @return
     *          a frame Object
     */
    String getFrame();

    /**
     * Get the object positioned in a cell, if present.
     * @return the Object to add to the inventory of the party
     * @throws NoObjectFoundException if the Object to get is absent.
     */
    Item getObject() throws NoObjectFoundException;

    /**
     * Created a dialogue with the NPC , if present.
     * @return a dialogue window with the NPC
     * @throws NoNPCFoundException if the NPC is not present
     */
    Dialogue talkToNpc() throws NoNPCFoundException;

    /**
     * Get the map contained in the cell , if present.
     * 
     * @return the map contained in the cell
     * @throws NoMapFoundException
     *             notify the type of Exception
     */
    GameMap getCellMap() throws NoMapFoundException;

    /**
     * Method to open the chest and get the item inside.
     * @param b
     *          the bag of the party
     * @return
     *          the item in the chest
     * @throws NoObjectFoundException if the chest is empty.
     * @throws NoKeyFoundException if the bag does not contain a key
     * @throws ItemNotFoundException if there's an error in the process of 
     *  removing the key
     * 
     */
    Item openChest(final Bag b) throws NoObjectFoundException, 
                                       NoKeyFoundException,
                                       ItemNotFoundException;


}
