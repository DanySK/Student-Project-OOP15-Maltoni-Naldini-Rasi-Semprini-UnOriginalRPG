package it.unibo.unori.model.maps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Test;

import it.unibo.unori.model.maps.cell.CellFactory;
import it.unibo.unori.model.maps.cell.CellState;
import it.unibo.unori.model.maps.exceptions.NoMapFoundException;

/**
 * Class for testing method of the GameMap Class.
 * 
 *
 */

//CHECKSTYLE DISABLE MagicNumber
public class MapTest {

    private final Position pos0 = new Position(0, 0);

    /**
     * Test for the basic function of the map.
     * Test the dimension of a row, of a column and the correct initialization of the first and the last cell of
     * the map.
     */
    @Test
    public void testBasicFunction() {
        final GameMap map = new GameMapImpl();
        assertEquals(map.getRow(0).size(), 100);
        assertEquals(map.getColumn(0).size(), 100);
        assertTrue(Optional.of(map.getCell(pos0)).isPresent());
        assertTrue(Optional.of(map.getCell(new Position(99, 99))).isPresent());
        assertTrue(map.getCell(pos0).getState().equals(CellState.FREE));
    }

    /**
     * Test for Exception.
     *This method tries to get a cell which do not belong to the Cell Matrix in the 
     *GameMap instance.
     *It also tries to get the map object of a cell which has no such item.
     */
    @Test
    public void testException() {
        final GameMap map = new GameMapImpl(50, 20);
        assertEquals(map.getRow(0).size(), 20);
        assertEquals(map.getColumn(0).size(), 50);
        try {
            map.getCell(new Position(50, 19));
            fail("System does not register the illegalargumentexception");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (Exception e) {
            fail("System throws another Exception");
        }
        try {
            map.getCell(new Position(32, 22));
            fail("System does not register the illegalargumentexception");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (Exception e) {
            fail("System throws another Exception");
        }
        try {
            map.getCell(new Position(-1, 19));
            fail("System does not register the illegalargumentexception");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (Exception e) {
            fail("System throws another Exception");
        }
        try {
            map.getCell(pos0).getCellMap();
          } catch (NoMapFoundException e) {
              System.out.println(e);
          } catch (Exception e) {
              fail("Wrong Exception thrown");
          }
    }

    /**
     * Test Setter methods for a single cell and a row/column.
     */
    @Test
    public void testCellSetting() {
        final GameMap map = new GameMapImpl();
        final CellFactory fc = new CellFactory();
        final Position p = new Position(50, 50);
        map.setCell(p, fc.getBlockedCell());
        assertEquals(map.getCell(p).getState(), CellState.BLOCKED);
        map.setColumn(50, fc.getBlockedCell());
        assertTrue(map.getColumn(50).stream().allMatch(i -> i.getState().equals(CellState.BLOCKED)));
        map.setRow(0, fc.getBlockedCell());
        assertTrue(map.getRow(0).stream().allMatch(i -> i.getState().equals(CellState.BLOCKED)));
    }

    /**
     * 
     */
    @Test
    public void testInitialCell() {
        final GameMap map = new GameMapImpl();
        final CellFactory fc = new CellFactory();
        assertEquals(map.getInitialCellPosition(), pos0);
        map.setRow(0, fc.getBlockedCell());
        assertEquals(map.getInitialCellPosition(), new Position(1, 0));
        final GameMap map2 = new GameMapImpl(50,25);
        try {
            map2.setInitialCellPosition(new Position(0, 40));
            fail("Method should throw an Exception...");
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException awaited");
        } catch (Exception e) {
            fail("Wrong Exception thrown");
        }
        assertEquals(new Position(0, 0), new Position(0, 0));
        assertFalse(new Position(0, 0).equals(map2));
        assertFalse(new Position(0, 0).equals(null));
        assertFalse(new Position(0, 0).equals(new Position(1, 0)));
        assertFalse(new Position(1, 0).equals(new Position(0, 1)));
    }

}
