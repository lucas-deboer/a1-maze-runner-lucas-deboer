package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public record Configuration(String file, String path) {
    private static final Logger logger = LogManager.getLogger();
    public static Configuration load(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "Input file that contains the maze");
        options.addOption("p", true, "Path that user wants verified");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            String input = cmd.getOptionValue("i");
            String path = cmd.getOptionValue("p");
            
            if (!cmd.getArgList().isEmpty()){
                throw new IllegalArgumentException();
            }
            return new Configuration(input,path);
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred /!\\");
            throw new RuntimeException(e);
        }
    }
}
