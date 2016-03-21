package it.unibo.unori.model.maps;

/**
 * 
 * Describe the states which a cell can have.
 *
 */
public enum CellState {
    /**
     * Describe the 3 state of a Cell.
     * FREE : The cell is free and the party can walk on it
     * BLOCKED : The cell is blocked and the party can't walk on it
     * LINKED : The cell contains a map , when the party walks on it, a new map will be loaded
     */
    FREE, BLOCKED, LINKER;
}
