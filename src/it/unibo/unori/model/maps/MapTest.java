package it.unibo.unori.model.maps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Test;

import it.unibo.unori.model.maps.cell.CellFactory;
import it.unibo.unori.model.maps.cell.CellState;

/**
 * Class for testing method of the GameMap Class.
 * 
 *
 */
public class MapTest {

    /**
     * Test for the basic function of the map.
     * Test the dimension of a row, of a column and the correct initialization of the first and the last cell of
     * the map.
     */
    @Test
    public void testBasicFunction() {
        final GameMap map = new GameMapImpl();
        assertEquals(map.getRow(0).size(), 100);
        assertTrue(Optional.of(map.getCell(0, 0)).isPresent());
        assertTrue(Optional.of(map.getCell(99, 99)).isPresent());
        assertTrue(map.getCell(0, 0).getState().equals(CellState.FREE));
    }

    /**
     * Test for the exception of the input.
     * If the system throw exception for a wrong row input and a wrong column input in a single method,
     *  it will work for the others methods 
     */
    @Test
    public void testException() {
        final GameMap map = new GameMapImpl(50, 20);
        assertEquals(map.getRow(0).size(), 20);
        assertEquals(map.getColumn(0).size(), 50);
        try {
            map.getCell(50, 19);
            fail("System does not register the illegalargumentexception");
        } catch (IllegalArgumentException e){}
        catch (Exception e) {
            fail("System throws another Exception");
        }
        try {
            map.getCell(32, 22);
            fail("System does not register the illegalargumentexception");
        } catch (IllegalArgumentException e){}
        catch (Exception e) {
            fail("System throws another Exception");
        }
        try {
            map.getCell(-1, 19);
            fail("System does not register the illegalargumentexception");
        } catch (IllegalArgumentException e){}
        catch (Exception e) {
            fail("System throws another Exception");
        }
    }

    /**
     * test the setting methods of the GameMap for both the single cell and the group.
     */
    @Test
    public void testCellSetting() {
        final GameMap map = new GameMapImpl();
        final CellFactory fc = new CellFactory();
        map.setCell(50, 50, fc.getBlockedCell());
        assertEquals(map.getCell(50, 50).getState(), CellState.BLOCKED);
        map.setColumn(50, fc.getBlockedCell());
        assertTrue(map.getColumn(50).stream().allMatch(i -> i.getState().equals(CellState.BLOCKED)));
        map.setRow(0, fc.getBlockedCell());
        assertTrue(map.getRow(0).stream().allMatch(i -> i.getState().equals(CellState.BLOCKED)));
    }

}
