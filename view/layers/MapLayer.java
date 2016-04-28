package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapImpl;

import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.SingletonParty;

import javax.swing.SwingUtilities;

/**
 * 
 * Game map.
 *
 */
public class MapLayer extends Layer {
    private static final Position POSITION = new Position(10, 10);


    /**
     * @param party the party to be displayed.
     */
    public MapLayer(final Party party) {
        super();
    }

    /**
     * Tests the class.
     * @param args arguments
     */
    public static void main(final String... args) {
        final Party party = SingletonParty.getParty();
        final GameMap map = new GameMapImpl(60, 40, POSITION);

        party.setCurrentMap(map);

        final View view = new View();
        final Layer mapLayer = new MapLayer(party);

        view.push(mapLayer);
        view.resizeTo(mapLayer);

        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                view.setVisible(true);
            }
        });

        view.center();
    }
}