package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class MazePath {
    private enum Tiles {F,f,R,r,L,l}
    String canPath;
    public String toString(ArrayList<String> str){
        String string = str.toString();
        string = string.replace("[","");
        string = string.replace("]","");
        string = string.replace(", ","");
        return string;
    }
    public void findPath(Maze theMaze) {
        int[] pos = new int[]{0,theMaze.findLeftHole()};
        int[] direction = new int[]{1,0};
        ArrayList<String> path = new ArrayList<>();
        while (pos[0] < theMaze.colmns - 1){
            String newDirec = Direction.getDirection(theMaze,direction,pos);
            path.add(newDirec);
            if (newDirec.equals("R") || newDirec.equals("L")){
                Direction.changeDirection(direction,newDirec.charAt(0));
            }
            if(!newDirec.equals("F")){path.add("F");}
            pos = theMaze.add(pos,direction);
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
        //call theMaze.traversal facing east
        //call theMaze.traversal facing west
        //if one is correct, print correct. else print incorrect
    }//check if the user input path is valid
    public void export() {
        int j;
        for(int i = 0; i < canPath.length(); i = i + j){
            j = 1;
            while ((i+j) < canPath.length() && canPath.charAt(i + j) == canPath.charAt(i)){
                j++;
            }
            if (j != 1){System.out.print(j);}
            System.out.print(canPath.charAt(i));
        }
        System.out.println();
    }//Display the end results (i.e. the valid path or if path is valid)
}
