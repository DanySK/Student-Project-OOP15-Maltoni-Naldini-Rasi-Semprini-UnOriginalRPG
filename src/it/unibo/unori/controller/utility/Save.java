package it.unibo.unori.controller.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Save {
    public static final String SETTINGS_FILE = "Settings.txt";
    public static final String STATISTICS_FILE = "Statistics.txt";

    private static final String STARTING_STRING = "--START--";
    private static final String ENDING_STRING = "--END--";

    /**
     * This method loads a text file and returns a list of lines contained
     * between starting and terminating line. If the requested file does not
     * exist, the method creates a void one and returns it; if it exists, but
     * does not match the expected structure, throws an exception.
     * 
     * @param fileName
     *            the file name in default path; probably you should use
     *            {@link #SETTINGS_FILE} or {@link #STATISTICS_FILE}
     * @return a list of the lines of the requested file
     * @throws CorruptedUtilityFileException
     *             if the file exists but does not contain the starting and/or
     *             the ending string
     * @throws IOException
     *             if the file does not exist
     */
    public static List<String> loadFromUtilityFile(String fileName) throws CorruptedUtilityFileException, IOException {
        ArrayList<String> lines;
        List<String> outputLines = new ArrayList<>();
        int i = 0;
        boolean start = false;

        lines = new ArrayList<String>(
                        Files.lines(FileSystems.getDefault().getPath(fileName)).collect(Collectors.toList()));
        while (start == false) {
            if (lines.get(i).equals(STARTING_STRING)) {
                start = true;
            } else if (i < lines.size()) {
                i++;
            }
            /* if starting string is not found, throws exception */
            else {
                throw new CorruptedUtilityFileException();
            }
        }

        while (start == true) {
            if (lines.get(i).equals(ENDING_STRING)) {
                start = false;
            } else if (i < lines.size()) {
                outputLines.add(lines.get(i));
                i++;
            }
            /* if ending string is not found, throws exception */
            else {
                throw new CorruptedUtilityFileException();
            }
        }
        return outputLines;
    }

    public static void createSettingsFile() throws IOException {
        PrintWriter output = new PrintWriter(new FileWriter(SETTINGS_FILE));

        output.print(new StringBuilder().append("--START--").append('\n').append("currentMap:0").append('\n')
                        .append("mapPosition:0,0").append('\n').append("char1:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0")
                        .append('\n').append("char2:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0").append('\n')
                        .append("char3:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0").append('\n')
                        .append("char4:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0").append('\n').append("inventory:0x0")
                        .append('\n').append("money:0").append('\n').append("--END--").append('\n').toString());
        output.close();
    }

    public static void createStatisticsFile() throws IOException {
        PrintWriter output = new PrintWriter(new FileWriter(STATISTICS_FILE));

        output.print(new StringBuilder().append("--START--").append('\n').append("newGames:0").append('\n')
                        .append("monstersMet:0").append('\n').append("monstersKilled:0").append('\n').append("bosses:0")
                        .append('\n').append("finalBosses:0").append('\n').append("weapons:0").append('\n')
                        .append("armors:0").append('\n').append("totalExp:0").append('\n').append("--END--")
                        .append('\n').toString());
        output.close();
    }
}
