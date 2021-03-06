package it.unibo.unori.model.maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellFactory;
import it.unibo.unori.model.maps.cell.CellState;

/**
 * 
 *Class to create a generic Map Object to customize later.
 *Every map in the game is a instance of this class
 *The map contains a matrix of Cell object, which represent the physical map for 
 *the party to walk on.
 *
 */
public class GameMapImpl implements GameMap {

    /**
     * 
     */
    private static final long serialVersionUID = -887928696341560842L;
    private static final int STDCELLS = 100; 
    private static final int PRIME = 31;
    private final Cell[][] floorMap;
    private Position initialPosition;
    private final boolean battleState;

    /**
     * Constructor for a standard map.
     * the standard dimension of a map is 100 x 100
     */
    public GameMapImpl() {
        this(STDCELLS, STDCELLS);
    }

    /**
     * Constructor for specified width and length maps.
     * @param width
     *              width of the map to build
     * @param length
     *              length of the map to build
     */
    public GameMapImpl(final int width, final int length) {
        this(width, length, true);
    }

    /**
     * Constructor for a map with specified dimensions and initial position.
     * @param width
     *              width of the map to build
     * @param length
     *              length of the map to build
     * @param pos
     *              initial cell of map.
     * @throws IllegalArgumentException if the position does not belong to the map 
     *                                  or if the party cannot be on that cell.
     * 
     */
    public GameMapImpl(final int width, final int length, 
                       final Position pos) throws IllegalArgumentException {
        this(width, length);
        this.setInitialCellPosition(pos);
    }

    /**
     * Constructor for specified width and length maps.
     * @param width
     *              width of the map to build
     * @param length
     *              length of the map to build
     * @param battleState
     *              parameter to activate the random encounter in the map
     */
    public GameMapImpl(final int width, final int length,
            final boolean battleState) {
        this.floorMap = new Cell[width][length];
        this.battleState = battleState;
        this.initializeMap();
    }




    //Private method to initialize the matrix of cells.
    private void initializeMap() {
        for (int i = 0; i < this.floorMap.length; i++) {
            this.floorMap[i] = Stream.generate(new CellFactory() :: getFreeCell)
                                     .limit(this.floorMap[0].length)
                                     .toArray(Cell[] :: new);
        } 
        this.initialPosition = new Position(0, 0);
    }

  /*Private method change the initial position if the previous initial position become Blocked
   * If return on the initialCell, it means that every cell is blocked, so it throws 
   * an IllegalStateException
   */
    private void fixInitialCellPosition() throws IllegalStateException {
        int initialX = this.initialPosition.getPosX();
        int initialY = this.initialPosition.getPosY();
        while (this.getCell(new Position(initialX, initialY)).getState().equals(CellState.BLOCKED)) {
            if (initialY == this.floorMap[0].length - 1) {
                initialY = 0;
                initialX++;
                if (initialX == this.getMapRows()) {
                    initialX = 0;
                }
            } else {
                initialY++;
            }
            if (this.initialPosition.equals(new Position(initialX, initialY))) {
                throw new IllegalStateException();
            }
        }
        this.initialPosition = new Position(initialX, initialY);
    }

    /**
     * Method to compare two length.
     * @param pos1
     *              the first position
     * @param pos2
     *              the second position
     * @return true if pos1 is greater than pos2 or if pos1 is lower than 0.
     */
    private boolean checkPosition(final int pos1, final int pos2) {
        return pos1 >= pos2 || pos1 < 0;
    }

    @Override
    public Cell getCell(final Position pos) throws IllegalArgumentException {
        if (checkPosition(pos.getPosX(), this.floorMap.length) 
                || checkPosition(pos.getPosY(), this.floorMap[0].length)) {
            throw new IllegalArgumentException();
        }
        return this.floorMap[pos.getPosX()][pos.getPosY()];
    }

    @Override
    public void setCell(final Position pos, final Cell cell) throws IllegalArgumentException {
        if (checkPosition(pos.getPosX(), this.floorMap.length) 
                || checkPosition(pos.getPosY(), this.floorMap[0].length)) {
            throw new IllegalArgumentException();
        }
        this.floorMap[pos.getPosX()][pos.getPosY()] = cell;
        this.fixInitialCellPosition();

    }

    @Override
    public List<Cell> getRow(final int posX) throws IllegalArgumentException {
        if (checkPosition(posX, this.floorMap.length)) {
            throw new IllegalArgumentException();
        }
        return Arrays.asList(this.floorMap[posX]);
    }

    @Override
    public List<Cell> getColumn(final int posY) throws IllegalArgumentException {
        if (checkPosition(posY, this.floorMap[0].length)) {
            throw new IllegalArgumentException();
        }
        final List<Cell> list = new ArrayList<>();
        for (int i = 0; i < this.floorMap.length; i++) {
           list.add(this.floorMap[i][posY]); 
        }
        return list;
    }

    @Override
    public void setRow(final int posX, final Cell cell) throws IllegalArgumentException {
        if (checkPosition(posX, this.getMapRows())) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < this.floorMap[posX].length; i++) {
            this.floorMap[posX][i] = cell;
        }
        this.fixInitialCellPosition();
    }

    @Override
    public void setColumn(final int posY, final Cell cell) throws IllegalArgumentException {
        if (checkPosition(posY, this.getMapColumns())) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < this.floorMap.length; i++) {
            this.floorMap[i][posY] = cell;
        }
        this.fixInitialCellPosition();
    }

    @Override
    public void replaceCell(final Position toRemove, final Position toSet) {
        this.setCell(toRemove, this.getCell(toSet));
    }


    @Override
    public final void setInitialCellPosition(final Position pos) 
                                                   throws IllegalArgumentException {
        if (this.checkPosition(pos.getPosX(), this.floorMap.length)) {
            throw new IllegalArgumentException();
        }
        if (this.checkPosition(pos.getPosY(), this.floorMap[0].length)) {
            throw new IllegalArgumentException();
        }

        if (this.getCell(pos).getState().equals(CellState.BLOCKED)) {
          throw new IllegalArgumentException();
        }
        this.initialPosition = pos;
    }

    @Override
    public Position getInitialCellPosition() {
        return this.initialPosition;
    }

    @Override
    public int getMapColumns() {
        return this.floorMap[0].length;
    }

    @Override
    public int getMapRows() {
        return this.floorMap.length;
    }

    @Override
    public String[][] getFrames() {
        final String[][] map = new String[this.floorMap.length]
                                         [this.floorMap[0].length];
        for (int i = 0; i < this.floorMap.length; i++) {
            for (int j = 0; j < this.floorMap[0].length; j++) {
                map[i][j] = this.floorMap[i][j].getFrame(); 
            }
        }
        return map;
    }

    @Override
    public boolean isBattleState() {
        return this.battleState;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = PRIME * result + (battleState ? 1231 : 1237);
        result = PRIME * result + Arrays.deepHashCode(floorMap);
        result = PRIME * result + ((initialPosition == null) ? 0 : initialPosition.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false; 
        }
        if (!(obj instanceof GameMapImpl)) {
            return false;
        }
        final GameMapImpl other = (GameMapImpl) obj;
        return this.battleState == other.battleState 
                &&  Arrays.deepEquals(this.floorMap, other.floorMap);

   }
}
