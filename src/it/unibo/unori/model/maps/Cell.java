package it.unibo.unori.model.maps;

import java.io.Serializable;

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
    public void setState(Object state);

    /**
     * Get the state of the cell.
     * 
     * @return the state of the Cell
     */
    public Object getState();

    /**
     * Set a specific Map in the cell as map to link.
     * 
     * @param map
     *            the map to link
     */
    public void setLinkedMap(Map map);

    /**
     * Set a particular frame on the cell.
     * 
     * @param frame
     *            frame to set from the view
     */
    public void setFrame(Object frame);

}
