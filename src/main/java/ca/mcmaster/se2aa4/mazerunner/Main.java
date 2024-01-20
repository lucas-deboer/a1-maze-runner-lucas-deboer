package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.module.Configuration;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
//Parse exception
public class Main {


    public static void main(String[] args) {
        // reads in the arguments that the user passed in to configure project
        Configuration config = Configuration.load(args);

        // converts provided text file to a usable format
        Maze theMaze = new Maze(config.file());

        //find the starting point of the maze
        theMaze.findStart();

        // find a solution to the maze

        //if no -p flag
        MazePath path = new MazePath.findPath(theMaze);

        //else if -p flag
        //Read in user path
        MazePath path = new MazePath(config.path());
        //defactorize the path
        path.defactorize();

        //verify the user input path
        MazePath.verfiy(theMaze, path);

        // final outputs (i.e. either the correct path or if the provided path is valid
        path.export();

    }
}
