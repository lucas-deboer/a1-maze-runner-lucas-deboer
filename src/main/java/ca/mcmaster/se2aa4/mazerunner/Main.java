package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        //reads in the arguments that the user passed in to configure project
        Configuration config = Configuration.load(args);

        //converts provided Maze text file to a usable format
        Maze theMaze = new Maze(config);
        
        //find the left and right openings in the maze
        theMaze.findLeftHole();
        theMaze.findRightHole();

        MazePath path = new MazePath();
        if(config.path != null){path.verifyPath(theMaze,config);}//find the solution to the maze
        else{path.findPath(theMaze);}//or verify the input
        
        // final outputs (i.e. either the correct path or if the provided path is valid
        path.export();

    }
}
