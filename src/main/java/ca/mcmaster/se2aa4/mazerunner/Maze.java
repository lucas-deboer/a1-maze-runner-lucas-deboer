package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Maze {
    private static final Logger logger = LogManager.getLogger();
    ArrayList<ArrayList<String>> maze;
    int rows;
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
            rows = l;
            colmns = idx;
            
        }catch (Exception e){
            logger.error("/!\\ An error has occured /!\\");
            throw new RuntimeException(e);
        }
    }

    public void findLeftHole() {
    }//find the opening on the left side of the maze

    public void findRightHole() {
    }//find the opening on the right side of the maze

    public ArrayList traversal(MazePath path, ArrayList coords){
        return new ArrayList(){};
    }
}
