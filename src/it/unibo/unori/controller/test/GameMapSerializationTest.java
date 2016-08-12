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
        File test = folder.newFile();

        final GameMapFactory gmf = new GameMapFactory();
        final JsonFileManager jfm = new JsonFileManager();
        
        final GameMap fourNPCRoom = gmf.create4NPCRoomMap();

        jfm.serializeJSON(fourNPCRoom, test.getAbsolutePath());
        assertEquals(fourNPCRoom.getMapLength(), jfm.deserializeJSON(GameMap.class, test.getAbsolutePath()).getMapLength());
        assertEquals(fourNPCRoom.getMapWidth(), jfm.deserializeJSON(GameMap.class, test.getAbsolutePath()).getMapWidth());
        
        System.out.println(jfm.gson.toJson(gmf.create4NPCRoomMap()));
    }

}
