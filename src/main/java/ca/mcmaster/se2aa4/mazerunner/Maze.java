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
    public int[] add(int[] coords, int[] direction){
        int[] tmp = new int[2];
        tmp[0] =  coords[0] + direction[0];
        tmp[1] =  coords[1] + direction[1];
        return tmp;
    }
    public String getTileValue(int[] position){
        return maze.get(position[1]).get(position[0]);
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
    public int[] traversal(MazePath path, int[] coords, int[] currDirection){
        for(int i = 0; i < path.canPath.length(); i++){
            if(path.canPath.charAt(i) == 'F'){
                coords = add(coords,currDirection);
                if (Objects.equals(this.getTileValue(coords), "W")){//if it moves into a wall, it is invalid
                    return new int[]{-1,-1};
                }
            }else{
                Direction.changeDirection(currDirection, path.canPath.charAt(i));
            }
        }
        return coords;
        // a class that just follows the instructions, it does not record anything
    }
}

