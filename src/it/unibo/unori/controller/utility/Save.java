package it.unibo.unori.controller.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.unori.controller.exceptions.CorruptedUtilityFileException;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;

/**
 * Utility class that provides static methods for load/save data from/to file.
 */
public final class Save {
    /**
     * Static parameter for standard text save file.
     */
    public static final String SAVE_TEXT_FILE = "Save.txt";

    /**
     * Static parameter for standard DAT object serialization save file.
     */
    public static final String SAVE_DAT_FILE = "Save.dat";

    /**
     * Static parameter for standard text statistics file.
     */
    public static final String STATISTICS_TEXT_FILE = "Stats.txt";

    /**
     * Static parameter for standard DAT object serialization statistics file.
     */
    public static final String STATISTICS_DAT_FILE = "Stats.dat";

    private static final String STARTING_STRING = "--START--";
    private static final String ENDING_STRING = "--END--";

    private Save() {
        // Empty private constructor, because this is an utility class
    }

    /**
     * This method loads a text file and returns a list of lines contained between starting and terminating line. If the
     * requested file does not exist, the method creates a void one and returns it; if it exists, but does not match the
     * expected structure, throws an exception.
     * 
     * @param fileName
     *            the file name in default path; probably you should use {@link #SAVE_TEXT_FILE} or
     *            {@link #STATISTICS_TEXT_FILE}
     * @return a list of the lines of the requested file
     * @throws CorruptedUtilityFileException
     *             if the file exists but does not contain the starting and/or the ending string
     * @throws IOException
     *             if the file does not exist
     */
    public static List<String> loadFromUtilityTextFile(final String fileName)
                    throws CorruptedUtilityFileException, IOException {
        ArrayList<String> lines;

        int i;
        boolean check = false;

        lines = new ArrayList<String>(
                        Files.lines(FileSystems.getDefault().getPath(fileName)).collect(Collectors.toList()));
        for (i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(STARTING_STRING)) {
                check = true;
                break;
            }
        }

        if (!check) {
            /* if starting string is not found, throws exception */
            throw new CorruptedUtilityFileException();
        }

        final List<String> outputLines = new ArrayList<>();

        for (i++; i < lines.size(); i++) {
            if (lines.get(i).equals(ENDING_STRING)) {
                check = false;
                break;
            } else {
                outputLines.add(lines.get(i));
            }
        }

        if (check) {
            /* if ending string is not found, throws exception */
            throw new CorruptedUtilityFileException();
        }

        return outputLines;
    }

    /**
     * It restores a previously saved game.
     * @param folderPath the path where to find the Save.dat file
     * @return the time played in milliseconds
     * @throws FileNotFoundException if the file does not exist, is a directory rather than a regular file, or for some other reason cannot be opened for reading.
     * @throws IOException if any of the usual Input/Output related exceptions.
     * @throws ClassNotFoundException if class of a serialized object cannot be found.
     */
    public static Double loadGame(final String folderPath) throws FileNotFoundException, IOException, ClassNotFoundException {
        final File saveFile = new File(folderPath + File.separator + SAVE_DAT_FILE);
        final ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(saveFile)));

        final Object p = input.readObject();
        if (p.getClass().equals(Party.class)) {
            SingletonParty.loadParty((Party) p);
        }

        final Double d = input.readDouble();

        input.close();

        return d;
    }

    /**
     * This method creates a new Save file in given path with all parameters initialized to 0.
     * 
     * @param folderPath
     *            the path in which you want to create the save file named {@link #SAVE_TEXT_FILE}
     * @throws IOException
     *             if the named file exists but is a directory rather than a regular file, does not exist but cannot be
     *             created, or exist but cannot be opened
     */
    public static void createSaveTextFile(final String folderPath) throws IOException {
        final File saveFile = new File(folderPath + File.separator + SAVE_TEXT_FILE);
        final PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(saveFile), "UTF-8"));

        output.print(new StringBuilder().append("--START--").append('\n').append("currentMap:0").append('\n')
                        .append("mapPosition:0,0").append('\n').append("char1:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0")
                        .append('\n').append("char2:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0").append('\n')
                        .append("char3:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0").append('\n')
                        .append("char4:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0").append('\n').append("inventory:0x0")
                        .append('\n').append("money:0").append('\n').append("--END--").append('\n').toString());
        output.close();
    }

    /**
     * This method creates a new binary empty Save file. If the file exists, it deletes it.
     * 
     * @param folderPath
     *            the path in which you want to create the save file named {@link #SAVE_DAT_FILE}
     * @return the newly created file
     * @throws IOException
     *             if an I/O error occurred
     * @throws DefaultPathTakenException
     *             if exist something (like a directory) that makes impossible to create an utility file (like the
     *             SaveGame) because of the name and the path are the same
     */
    public static File createSaveDatFile(final String folderPath) throws IOException, DefaultPathTakenException {
        final File saveFile = new File(folderPath + File.separator + SAVE_DAT_FILE);

        while (!saveFile.createNewFile()) {
            if (!saveFile.delete()) {
                throw new DefaultPathTakenException();
            }
        }

        return saveFile;
    }

    /**
     * This method saves the state of the game by serializing the Party object (containing position on map, statistics,
     * etc...) and the time played.
     * 
     * @param party
     *            the party object
     * @param playedTime
     *            the time played in milliseconds
     * @param folderPath
     *            the path where to save file
     * @throws FileNotFoundException
     *             if the file exists but is a directory rather than a regular file, does not exist but cannot be
     *             created, or cannot be opened for any other reason
     * @throws IOException
     *             if an I/O error occurred in creating the save file
     * @throws DefaultPathTakenException
     *             if exist something (like a directory) that makes impossible to create an utility file (like the
     *             SaveGame) because of the name and the path are the same
     */
    public static void saveGame(final Party party, final double playedTime, final String folderPath)
                    throws FileNotFoundException, IOException, DefaultPathTakenException {
        final ObjectOutputStream output = new ObjectOutputStream(
                        new BufferedOutputStream(new FileOutputStream(createSaveDatFile(folderPath))));

        output.writeObject(party);
        output.writeDouble(playedTime);

        output.close();
    }

    /**
     * This method saves the state of the game by serializing the Party object (containing position on map, statistics,
     * etc...) and the time played. It saves in default path.
     * 
     * @param party
     *            the party object
     * @param playedTime
     *            the time played in milliseconds
     * @throws FileNotFoundException
     *             if the file exists but is a directory rather than a regular file, does not exist but cannot be
     *             created, or cannot be opened for any other reason
     * @throws IOException
     *             if an I/O error occurred in creating the save file
     * @throws DefaultPathTakenException
     *             if exist something (like a directory) that makes impossible to create an utility file (like the
     *             SaveGame) because of the name and the path are the same
     */
    public static void saveGame(final Party party, final double playedTime)
                    throws FileNotFoundException, IOException, DefaultPathTakenException {
        saveGame(party, playedTime, "res"); // TODO not sure about "res"
    }

    /**
     * This method creates a new Statistics file in given path with all statistics initialized to 0.
     * 
     * @param folderPath
     *            the path in which you want to create the save file named {@link #STATISTICS_TEXT_FILE}
     * @throws IOException
     *             if the named file exists but is a directory rather than a regular file, does not exist but cannot be
     *             created, or exist but cannot be opened
     */
    public static void createStatsTextFile(final String folderPath) throws IOException {
        final File statsFile = new File(folderPath + File.separator + STATISTICS_TEXT_FILE);
        final PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(statsFile), "UTF-8"));

        output.print(new StringBuilder().append("--START--").append('\n').append("newGames:0").append('\n')
                        .append("monstersMet:0").append('\n').append("monstersKilled:0").append('\n').append("bosses:0")
                        .append('\n').append("finalBosses:0").append('\n').append("weapons:0").append('\n')
                        .append("armors:0").append('\n').append("totalExp:0").append('\n').append("--END--")
                        .append('\n').toString());
        output.close();
    }

}
