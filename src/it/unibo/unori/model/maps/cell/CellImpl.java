package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.exceptions.NoMapFoundException;

/**
 * Implementation of the Cell interface.
 * Has 3 private field : frame, state,and maptolink
 * frame is the object to graphical represent the cell
 * state is the current state of the cell 
 *
 */
public class CellImpl implements Cell {

    /**
     * 
     */
    private static final long serialVersionUID = -8981471810277191248L;
    private Object frame;
    private CellState state;


    /**
     * Basic Constructor for CellImpl.
     * 
     * @param frame
     *            the frame to associate
     * @param state
     *            the state to associate
     */
    public CellImpl(final Object frame, final CellState state) {
        this.frame = frame;
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
    public void setFrame(final Object frame) {
        this.frame = frame;
    }

    @Override
    public GameMap getCellMap() throws NoMapFoundException {
            throw new NoMapFoundException();
     }

    @Override
    public Object getFrame() {
        return this.frame;
    }

}
