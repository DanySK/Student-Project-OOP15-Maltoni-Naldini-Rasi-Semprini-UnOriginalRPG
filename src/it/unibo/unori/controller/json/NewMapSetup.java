package it.unibo.unori.controller.json;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapFactory;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellState;
import it.unibo.unori.model.maps.cell.MapCellImpl;
import it.unibo.unori.model.maps.cell.SimpleCellImpl;

public class NewMapSetup {

    public static void main(String[] args) {
        final JsonFileManager fileManager = new JsonFileManager();
        final GameMapFactory gmf = new GameMapFactory();
        final String grass = "res/sprites/map/grass.png";
        final String floor = "res/sprites/map/floor.png";
        final String earth = "res/sprites/map/earth.png";
        final String border = "res/sprites/map/border-1.png";
        final String ceiling = "res/sprites/map/ceiling.png";
        final String houseSubDirectory = "res/sprites/map/house";
        final String shopSubDirectory = "res/sprites/map/shop";
        final String churchSubDirectory = "res/sprites/map/church";
        final String rightWall = "/right.png";
        final String leftWall = "/left.png";
        final String centralWall = "/center.png";

        // Out of dungeon
        final GameMap village = gmf.getVillageMap();
        final GameMap church = gmf.createChurch();
        final GameMap house = gmf.create4NPCRoomMap();
        final GameMap shop = gmf.createShop();
        final GameMap passage = gmf.createAisle();
        final GameMap dungeonEntrance = gmf.createDungeonEntrance();

        // Setting floors
        NewMapSetup.setAllCells(village, grass);
        System.out.println("Villaggio settato");
        NewMapSetup.setAllCells(church, floor);
        System.out.println("Chiesa settata");
        NewMapSetup.setAllCells(house, floor);
        System.out.println("Casa settata");
        NewMapSetup.setAllCells(shop, floor);
        NewMapSetup.setAllCells(passage, grass);
        NewMapSetup.setAllCells(dungeonEntrance, earth);
        System.out.println("Tutto settato");

        // Setting borders
        setFencesBorders(village);
        System.out.println("Confini villaggio settato");
        setFencesBorders(passage);
        setFencesBorders(dungeonEntrance);
        NewMapSetup.setBorders(border, house);
        NewMapSetup.setBorders(border, shop);
        NewMapSetup.setBorders(border, church);
        System.out.println("Confini tutti settato");

        // Setting village and link to passage ...
        NewMapSetup.setVerticalLink(village, passage, new Position(8, village.getMapColumns() - 1),
                new Position(12, village.getMapColumns() - 1), new Position(3, 0), new Position(6, 0), grass, true);
        // ... shop and house ceilings ...
        System.out.println("Arrivato 1");
        for (int i = 2; i <= 5; i++) {
            village.setCell(new Position(4, i), new SimpleCellImpl(ceiling, CellState.BLOCKED));
            village.setCell(new Position(14, i), new SimpleCellImpl(ceiling, CellState.BLOCKED));
        }
        // ... house walls and door ...
        village.setCell(new Position(5, 2), new SimpleCellImpl(houseSubDirectory + rightWall, CellState.BLOCKED));
        village.setCell(new Position(5, 3), new SimpleCellImpl(houseSubDirectory + centralWall, CellState.BLOCKED));
        village.setCell(new Position(5, 5), new SimpleCellImpl(houseSubDirectory + leftWall, CellState.BLOCKED));
        NewMapSetup.setDoorLink(village, house, new Position(5, 4), new Position(5, 4), true);
        // ... shop walls and door ...
        village.setCell(new Position(15, 2), new SimpleCellImpl(shopSubDirectory + rightWall, CellState.BLOCKED));
        village.setCell(new Position(15, 3), new SimpleCellImpl(shopSubDirectory + centralWall, CellState.BLOCKED));
        village.setCell(new Position(15, 5), new SimpleCellImpl(shopSubDirectory + leftWall, CellState.BLOCKED));
        NewMapSetup.setDoorLink(village, shop, new Position(15, 4), new Position(5, 7), true);
        // ... church ceiling, walls and door ...
        for (int i = 12; i <= 14; i++) {
            village.setCell(new Position(4, i), new SimpleCellImpl(ceiling, CellState.BLOCKED));
            village.setCell(new Position(5, i), new SimpleCellImpl(ceiling, CellState.BLOCKED));
        }
        village.setCell(new Position(6, 12), new SimpleCellImpl(churchSubDirectory + rightWall, CellState.BLOCKED));
        village.setCell(new Position(7, 12), new SimpleCellImpl(churchSubDirectory + rightWall, CellState.BLOCKED));
        village.setCell(new Position(6, 13), new SimpleCellImpl(churchSubDirectory + centralWall, CellState.BLOCKED));
        village.setCell(new Position(6, 14), new SimpleCellImpl(churchSubDirectory + leftWall, CellState.BLOCKED));
        village.setCell(new Position(7, 14), new SimpleCellImpl(churchSubDirectory + leftWall, CellState.BLOCKED));
        NewMapSetup.setDoorLink(village, church, new Position(7, 13), new Position(10, 4), true);
        // TODO items
        // TODO NPCs

        // Setting house ...
        // TODO table
        // TODO items
        // TODO NPCs

        // Setting shop ...
        // TODO table
        // TODO items
        // TODO NPCs

        // Setting church ...
        // TODO benches
        // TODO items
        // TODO NPCs

        // Setting passage ...
        NewMapSetup.setVerticalLink(passage, dungeonEntrance, new Position(4, dungeonEntrance.getMapColumns() - 1),
                new Position(5, dungeonEntrance.getMapColumns() - 1), new Position(5, 0), new Position(6, 0), grass,
                true);
        // TODO items
        // TODO NPCs

        // Setting dungeon entrance
        // TODO dungeon
        // TODO items
        // TODO NPCs

    }

    private static void setDoorLink(GameMap firstMap, GameMap secondMap, Position doorPosition,
            Position entrancePosition, boolean setLink) {
        firstMap.setCell(doorPosition,
                setLink ? new MapCellImpl(secondMap, entrancePosition)
                        : new SimpleCellImpl("res/sprites/map/door.png", CellState.BLOCKED));
        secondMap.setCell(entrancePosition,
                setLink ? new MapCellImpl(firstMap, doorPosition)
                        : new SimpleCellImpl("res/sprites/map/black.png", CellState.BLOCKED));
    }

    private static void setVerticalLink(GameMap firstMap, GameMap secondMap, Position firstFirst, Position firstLast,
            Position secondFirst, Position secondLast, String floorPath, boolean setLink) {
        Position from;
        Position to;
        for (int i = firstFirst.getPosX(), j = secondFirst.getPosX(); i <= firstLast.getPosX()
                || j <= secondLast.getPosX(); i++, j++) {
            from = new Position(i, firstFirst.getPosY());
            to = new Position(j, secondFirst.getPosY());
            firstMap.setCell(from, setLink ? new MapCellImpl(secondMap, to)
                    : new SimpleCellImpl(floorPath, CellState.BLOCKED));
            secondMap.setCell(to, setLink ? new MapCellImpl(firstMap, from)
                    : new SimpleCellImpl(floorPath, CellState.BLOCKED));
        }
    }

    private static void setHorizontalLink(GameMap firstMap, GameMap secondMap, Position firstFirst, Position firstLast,
            Position secondFirst, Position secondLast, String floorPath, boolean setLink) {
        Position from;
        Position to;
        for (int i = firstFirst.getPosY(), j = secondFirst.getPosY(); i <= firstLast.getPosY()
                || j <= secondLast.getPosY(); i++, j++) {
            from = new Position(i, firstFirst.getPosX());
            to = new Position(j, secondFirst.getPosX());
            firstMap.setCell(from, setLink ? new MapCellImpl(secondMap, to)
                    : new SimpleCellImpl(floorPath, CellState.BLOCKED));
            secondMap.setCell(to, setLink ? new MapCellImpl(firstMap, from)
                    : new SimpleCellImpl(floorPath, CellState.BLOCKED));
        }
    }

    private static void setFencesBorders(final GameMap map) {
        final String horizontalFence = "res/sprites/map/fence/width.png";
        final String vericalFence = "res/sprites/map/fence/length.png";
        final Cell lowerLeftFence = new SimpleCellImpl("res/sprites/map/fence/lower-left.png", CellState.BLOCKED);
        final Cell lowerRightFence = new SimpleCellImpl("res/sprites/map/fence/lower-right.png", CellState.BLOCKED);
        final Cell upperLeftFence = new SimpleCellImpl("res/sprites/map/fence/upper-left.png", CellState.BLOCKED);
        final Cell upperRightFence = new SimpleCellImpl("res/sprites/map/fence/upper-right.png", CellState.BLOCKED);

        final int maxWidth = map.getMapRows()-1;
        final int maxLength = map.getMapColumns()-1;

        setHorizontalBorders(horizontalFence, map);
        setVerticalBorders(vericalFence, map);
        map.setCell(new Position(0, 0), upperLeftFence);
        map.setCell(new Position(maxWidth, 0), lowerLeftFence);
        map.setCell(new Position(0, maxLength), upperRightFence);
        map.setCell(new Position(maxWidth, maxLength), lowerRightFence);
    }

    private static void setBorders(final String bordersPath, final GameMap map) {
        setVerticalBorders(bordersPath, map);
        setHorizontalBorders(bordersPath, map);
    }

    private static void setVerticalBorders(final String bordersPath, final GameMap map) {
        map.setColumn(0, new SimpleCellImpl(bordersPath, CellState.BLOCKED));
        map.setColumn(map.getMapColumns() -1, new SimpleCellImpl(bordersPath, CellState.BLOCKED));
    }

    private static void setHorizontalBorders(final String bordersPath, final GameMap map) {
        map.setRow(0, new SimpleCellImpl(bordersPath, CellState.BLOCKED));
        map.setRow(map.getMapRows() -1, new SimpleCellImpl(bordersPath, CellState.BLOCKED));
    }

    private static void setAllCells(final GameMap map, final String path) {
        for (int i = 0; i < map.getMapRows(); i++) {
            for (int j = 0; j < map.getMapColumns(); j++) {
                map.getCell(new Position(i, j)).setFrame(path);
            }
        }
    }
}
