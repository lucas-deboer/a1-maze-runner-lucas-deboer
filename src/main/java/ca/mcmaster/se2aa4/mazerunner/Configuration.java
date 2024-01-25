package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

//change to a record
public class Configuration {
    private static final Logger logger = LogManager.getLogger();
    String file;
    String path;
    public Configuration (String f, String p){
        file = f; path = p;
    }
    public static Configuration load(String[] args) {
        //loading user parameters
        try {
            Options options = new Options();
            options.addOption("i", true, "Input file that contains the maze");
            options.addOption("p", true, "Path that user wants verified");

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            String input = cmd.getOptionValue("i");
            String path = cmd.getOptionValue("p");
            return new Configuration(input,path);//use a generic case for now

        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            throw new RuntimeException(e);
        }
    }
}
