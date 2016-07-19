package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.exceptions.NoMapFoundException;
import it.unibo.unori.model.maps.exceptions.NoNPCFoundException;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;
import it.unibo.unori.model.menu.Dialogue;
import it.unibo.unori.model.menu.DummyMenu;

/**
 * Implementation of the Cell interface.
 * Has 2 private field : frame, state.
 * frame is the object to graphical represent the cell
 * state is the current state of the cell 
 * Three methods raise an Exception, because CellImpl is the implementation 
 * for a simple free or blocked cell, no advanced mechanism is developed.
 *
 */
public class SimpleCellImpl implements Cell {

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
    public SimpleCellImpl(final Object frame, final CellState state) {
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

    @Override
    public Object getObject() throws NoObjectFoundException {
        throw new NoObjectFoundException();
    }

    @Override
    public DummyMenu talkToNpc() throws NoNPCFoundException {
        throw new NoNPCFoundException();
    }

    @Override
    public Dialogue interactCell() {
        return new Dialogue("Non c'è niente qui! Meglio andare avanti!");
    }

}
