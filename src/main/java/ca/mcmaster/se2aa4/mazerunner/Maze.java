package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;


public class Maze {
    private static final Logger logger = LogManager.getLogger();
    private final ArrayList<ArrayList<String>> maze;
    private final int colmns;
    private int leftOpeningRow;
    private int rightOpeningRow;

    public Maze(Configuration config) {
        maze = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(config.file()));
            int l = 0, idx = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                maze.add(new ArrayList<>());
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
            findLeftHoles();
            findRightHoles();
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred /!\\");
            throw new RuntimeException(e);
        }
    }

    private void findLeftHoles() {
        int holeAt = 1;
        while (!Objects.equals(maze.get(holeAt).getFirst(), "P")) {
            holeAt++;
        }
        leftOpeningRow = holeAt;
    }//find the opening on the left side of the maze
    private void findRightHoles() {
        int holeAt = 1;
        while (!Objects.equals(maze.get(holeAt).getLast(), "P")) {
            holeAt++;
        }
        rightOpeningRow = holeAt;
    }//find the opening on the right side of the maze

    // all the getters
    public String getTileValue(int[] position) {
        return maze.get(position[1]).get(position[0]);
    }
    public int getColmns() {
        return colmns;
    }
    public int getLeftHole() {
        return leftOpeningRow;
    }
    public int getRightHole() {
        return rightOpeningRow;
    }
}