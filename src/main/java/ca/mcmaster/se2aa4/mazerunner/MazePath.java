package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class MazePath {
    String canPath;
    public void findPath(Maze theMaze) {
        int x = 0, y = 2;
        ArrayList<String> path = new ArrayList<>();
        while (x != theMaze.colmns && theMaze.maze.get(y).get(x).equals("P")){
            path.add("F");
            x++;
        }
        //convert the arraylist to a string in canonical form
        canPath = path.toString();
        canPath = canPath.replace("[","");
        canPath = canPath.replace("]","");
        canPath = canPath.replace(", ","");
    }//find a solution to the maze

    public void defactorize(Configuration config){
    }//ensure the user input path is in canonical form
    public void verifyPath(Maze theMaze, Configuration config) {
    }//check if the user input path is valid
    public void export() {
    }//Display the end results (i.e. the valid path or if path is valid)
}
