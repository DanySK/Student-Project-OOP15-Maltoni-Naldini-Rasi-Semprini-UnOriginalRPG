package it.unibo.unori.controller.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import it.unibo.unori.controller.json.JsonFileManager;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapFactory;

public class GameMapSerializationTest {
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test() throws IOException {
        final File test = folder.newFile();

        final GameMapFactory gmf = new GameMapFactory();
        final JsonFileManager jfm = new JsonFileManager();

        final GameMap fourNPCRoom = gmf.create4NPCRoomMap();

        jfm.saveMap(fourNPCRoom, test.getAbsolutePath());
        assertEquals(fourNPCRoom.getMapRows(), jfm.loadMap(test.getAbsolutePath()).getMapRows());
        assertEquals(fourNPCRoom.getMapColumns(), jfm.loadMap(test.getAbsolutePath()).getMapColumns());
    }

}
