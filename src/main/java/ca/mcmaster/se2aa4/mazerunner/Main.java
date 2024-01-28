package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            logger.info("Maze analysis beginning.");
            Configuration config = Configuration.load(args);
            Maze theMaze = new Maze(config);
            MazePath path = new MazePath();
            
            if (config.path() != null) {path.verifyPath(theMaze, config);}
            else {path.findPath(theMaze);}
            
            path.export(); //outputs the path or the validity of path
            logger.info("Maze analysis complete.");
        }catch (MazePathOutOfRange e){ //catch if the path goes "outside" the maze
            System.out.println("incorrect path");
        }catch (Exception e){
            logger.error("/!\\ An error has occurred /!\\");
        }

    }
}
