package it.unibo.unori.controller.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.unori.controller.exceptions.CorruptedUtilityFileException;

/**
 * Utility class that provides static methods for load/save data from/to file.
 */
public final class Save {
    /**
     * Static parameter for standard text save file.
     */
    public static final String SAVE_FILE = "Save.txt";

    /**
     * Static parameter for standard text statistics file.
     */
    public static final String STATISTICS_FILE = "Stats.txt";

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
     *            the file name in default path; probably you should use {@link #SAVE_FILE} or {@link #STATISTICS_FILE}
     * @return a list of the lines of the requested file
     * @throws CorruptedUtilityFileException
     *             if the file exists but does not contain the starting and/or the ending string
     * @throws IOException
     *             if the file does not exist
     */
    public static List<String> loadFromUtilityFile(final String fileName)
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

        for (; i < lines.size(); i++) {
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
     * This method creates a new Save file in given path with all parameters initialized to 0.
     * 
     * @param folderPath
     *            the path in which you want to create the save file named {@link #SAVE_FILE}
     * @throws IOException
     *             if the named file exists but is a directory rather than a regular file, does not exist but cannot be
     *             created, or exist but cannot be opened
     */
    public static void createSaveFile(final String folderPath) throws IOException {
        final PrintWriter output = new PrintWriter(new FileWriter(folderPath + File.separator + SAVE_FILE));

        output.print(new StringBuilder().append("--START--").append('\n').append("currentMap:0").append('\n')
                        .append("mapPosition:0,0").append('\n').append("char1:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0")
                        .append('\n').append("char2:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0").append('\n')
                        .append("char3:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0").append('\n')
                        .append("char4:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0").append('\n').append("inventory:0x0")
                        .append('\n').append("money:0").append('\n').append("--END--").append('\n').toString());
        output.close();
    }

    /**
     * This method creates a new Statistics file in given path with all statistics initialized to 0.
     * 
     * @param folderPath
     *            the path in which you want to create the save file named {@link #STATISTICS_FILE}
     * @throws IOException
     *             if the named file exists but is a directory rather than a regular file, does not exist but cannot be
     *             created, or exist but cannot be opened
     */
    public static void createStatsFile(final String folderPath) throws IOException {
        final PrintWriter output = new PrintWriter(new FileWriter(folderPath + File.separator + STATISTICS_FILE));

        output.print(new StringBuilder().append("--START--").append('\n').append("newGames:0").append('\n')
                        .append("monstersMet:0").append('\n').append("monstersKilled:0").append('\n').append("bosses:0")
                        .append('\n').append("finalBosses:0").append('\n').append("weapons:0").append('\n')
                        .append("armors:0").append('\n').append("totalExp:0").append('\n').append("--END--")
                        .append('\n').toString());
        output.close();
    }
}
