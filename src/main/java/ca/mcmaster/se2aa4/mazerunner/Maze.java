package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;


public class Maze {
    private static final Logger logger = LogManager.getLogger();
    ArrayList<ArrayList<String>> maze;
    int colmns;
    public Maze(Configuration config) {
        maze = new ArrayList<ArrayList<String>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(config.file));
            int l = 0, idx = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                maze.add(new ArrayList<String>());
                for (idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        maze.get(l).add("W");
                    } else if (line.charAt(idx) == ' ') {
                        maze.get(l).add("P");
                    }
                }
                l++;
            }
            colmns = idx;
        }catch (Exception e){
            logger.error("/!\\ An error has occured /!\\");
            throw new RuntimeException(e);
        }
    }

    public int findLeftHole() {
        int holeAt = 1;
        while(!Objects.equals(maze.get(holeAt).getFirst(), "P")){holeAt++;}
        return holeAt;
    }//find the opening on the left side of the maze

    public int findRightHole() {
        int holeAt = 1;
        while(!Objects.equals(maze.get(holeAt).getLast(), "P")){holeAt++;}
        return holeAt;
    }//find the opening on the right side of the maze
    
    //need to make a function that can deal with the direction/direction changes/
    public ArrayList traversal(MazePath path, ArrayList coords){
        // a class that just follows the instructions, it does not record anything
        return new ArrayList(){};
    }
}

