package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class MazePath {
     public enum Tiles {F,f,R,r,L,l}
    String canPath;
    public String toString(ArrayList<String> str){
        String string = str.toString();
        string = string.replace("[","");
        string = string.replace("]","");
        string = string.replace(", ","");
        return string;
    }
    public void findPath(Maze theMaze) {
        int x = 0, y = 2;
        ArrayList<String> path = new ArrayList<>();
        while (x != theMaze.colmns && theMaze.maze.get(y).get(x).equals("P")){
            path.add("F");
            x++;
        }
        //convert the arraylist to a string in canonical form
        canPath = toString(path);
    }//find a solution to the maze

    public void defactorize(Configuration config){
        ArrayList<String> path = new ArrayList<>();
        int count,j;
        String str = config.path;
        for (int i = 0; i < str.length(); i++){
            count = 1;
            if (!CollectionUtils.containsAny(List.of(str.charAt(i)), EnumSet.allOf(Tiles.class))){
                j = i+1;
                while(Character.isDigit(str.charAt(j))){
                    j++;                    
                }
                count =  Integer.parseInt(str.substring(i,j));
                i = j;
            }
            for(int k = 0; k< count; k++){path.add(String.valueOf(str.charAt(i)));}
        }
        
        canPath = toString(path);
    }//ensure the user input path is in canonical form
    
    public void verifyPath(Maze theMaze, Configuration config) {
    }//check if the user input path is valid
    public void export() {
        System.out.println(canPath);
    }//Display the end results (i.e. the valid path or if path is valid)
}
