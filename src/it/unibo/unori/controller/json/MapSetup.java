package it.unibo.unori.controller.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.character.NpcImpl;
import it.unibo.unori.model.items.WeaponFactory;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapFactory;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.cell.NPCCellImpl;
import it.unibo.unori.model.maps.cell.ObjectCellImpl;
import it.unibo.unori.model.menu.Dialogue;

public class MapSetup {
    public static final String FOUR_NPC_ROOM = "res/4NPCRoom.json";
    public static final String SHOP = "res/Shop.json";
    public static final String CHURCH = "res/Church-.json";
    public static final String VILLAGE = "res/Village.json";
    public static final String PASSAGE = "res/Passage.json";
    public static final String DUNGEON_ENTRANCE = "res/DungeonEntrance.json";
    
    private final Map<String, GameMap> maps;
    private final JsonFileManager fileManager;
    
    public MapSetup() {
        this.maps = new HashMap<>();
        this.fileManager = new JsonFileManager();
    }
    
    public static void main(String[] args) {
        final GameMapFactory gmf = new GameMapFactory();
        
        
    }
    
    public GameMap loadFullyLinkedMap() throws IOException {
        
        
        return null;
    }

    public GameMap load4NPCRoom() throws IOException {
        return this.checkAndLoad(FOUR_NPC_ROOM);
    }


    public GameMap loadShop() throws IOException {
        return this.checkAndLoad(SHOP);
    }


    public GameMap loadChurch() throws IOException {
        return this.checkAndLoad(CHURCH);
    }


   public GameMap loadVillage() throws IOException {
       return this.checkAndLoad(VILLAGE);
    }


   public GameMap loadPassage() throws IOException {
       return this.checkAndLoad(PASSAGE);
   }


   public GameMap loadDungeonEntrance() throws IOException {
       return this.checkAndLoad(DUNGEON_ENTRANCE);
   }
   
   private GameMap checkAndLoad(final String path) throws IOException {
       if (!this.maps.containsKey(path)) {
           this.maps.put(path, fileManager.loadMap(path));
       }
       return this.maps.get(path);
   }
}
