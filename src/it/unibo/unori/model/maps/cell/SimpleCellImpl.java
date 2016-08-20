package it.unibo.unori.model.maps.cell;

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
 * Implementation of the Cell interface.
 * Has 2 private field : Path and State.
 * Path is the path of the image of the cell
 * State is the current state of the cell 
 * Three methods raise an Exception, because CellImpl is the implementation 
 * for a simple free or blocked cell, no advanced mechanism is developed.
 *
 */
public class SimpleCellImpl implements Cell {

    /**
     * 
     */
    private static final long serialVersionUID = -8981471810277191248L;
    private static final int PRIME = 31;
    private String path;
    private CellState state;



    /**
     * Basic Constructor for CellImpl.
     * 
     * @param path
     *            the path of frame to associate
     * @param state
     *            the state to associate
     */
    public SimpleCellImpl(final String path, final CellState state) {
        this.path = path;
        this.state = state;
    }

    @Override
    public void setState(final CellState state) {
        this.state = state;
    }

    @Override
    public CellState getState() {
        return this.state;
    }


    @Override
    public void setFrame(final String path) {
        this.path = path;
    }

    @Override
    public GameMap getCellMap() throws NoMapFoundException {
            throw new NoMapFoundException();
     }

    @Override
    public String getFrame() {
        return this.path;
    }

    @Override
    public Item getObject() throws NoObjectFoundException {
        throw new NoObjectFoundException();
    }

    @Override
    public DialogueInterface talkToNpc() throws NoNPCFoundException {
        throw new NoNPCFoundException();
    }

    @Override
    public Item openChest(final Bag b) throws NoObjectFoundException, NoKeyFoundException, ItemNotFoundException {
        throw new NoObjectFoundException();
    }

    @Override
    public Foe getBoss() throws IllegalStateException {
        throw new IllegalStateException();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = PRIME * result + ((path == null) ? 0 : path.hashCode());
        result = PRIME * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SimpleCellImpl)) {
            return false;
        }
        final SimpleCellImpl other = (SimpleCellImpl) obj;
        if (path == null) {
            if (other.path != null) {
                return false;
            }
            return state.equals(other.state);
        } else {
            return path.equals(other.path) && state.equals(other.state); 
        }
    }


}
