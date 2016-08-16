package it.unibo.unori.model.maps.cell;

import java.io.Serializable;

import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.exceptions.NoKeyFoundException;
import it.unibo.unori.model.maps.exceptions.NoMapFoundException;
import it.unibo.unori.model.maps.exceptions.NoNPCFoundException;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;
import it.unibo.unori.model.menu.DialogueInterface;

/**
 * 
 * A interface to define the basic method of a cell. A cell is the basic
 * component of a map, a map has a matrix composed of various cells, every cell
 * has a path of the resource of its image.
 * The logical cell has a state ,which can be blocked or free, and
 * various method to control and interact with it. 
 * Serializable interface is added in order to make possible 
 * the saving of a cell on file.
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
     * Set the path of the image of the cell.
     * 
     * @param path
     *            path of frame to set from the view
     */
    void setFrame(String path);


    /**
     * Return the path of the image associated with the Cell.
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
     * Initiate a dialogue with the NPC , if present.
     * @return dialogue of the NPC in that position
     * @throws NoNPCFoundException if the NPC is not present
     */
    DialogueInterface talkToNpc() throws NoNPCFoundException;

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

    /**
     * Return the boss in the cell, when the party interact with the cell.
     * @return
     *          a Foe object
     * @throws IllegalStateException if there's no boss in the cell.
     */
    Foe getBoss() throws IllegalStateException;


}
