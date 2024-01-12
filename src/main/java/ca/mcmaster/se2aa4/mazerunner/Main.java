package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
//Parse exception
public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        System.out.println("** Starting Maze Runner");
        try {
            Options options = new Options();
            options.addOption("i",true,"The file that contains the maze");
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            String input = cmd.getOptionValue("i");

            System.out.println("**** Reading the maze from file " + input);
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }
        } catch(Exception e) {
            System.err.println("/!\\ An error has occured /!\\");
        }
        System.out.println("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        System.out.println("** End of MazeRunner");
    }
}
