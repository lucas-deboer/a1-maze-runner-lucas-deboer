package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

import org.apache.commons.collections4.CollectionUtils;

public class MazePath {
    private final static Character[] TILES = new Character[]{'F','f','L','l','R','r',' ','\n'};
    private String canPath;
    private Boolean valid = null;
    private int length;
    private static String toString(ArrayList<String> str){
        String string = str.toString();
        string = string.replace("[","");
        string = string.replace("]","");
        string = string.replace(", ","");
        return string;
    }
    private void defactorize(Configuration config){
        ArrayList<String> path = new ArrayList<>();
        int count,j;
        String str = config.path();
        for (int i = 0; i < str.length(); i++){
            count = 1;
            if (!CollectionUtils.containsAny(List.of(str.charAt(i)), TILES)){
                j = i+1;
                while(Character.isDigit(str.charAt(j))){
                    j++;
                }
                count =  Integer.parseInt(str.substring(i,j));
                while(str.charAt(j) == ' ' || str.charAt(j) == '\n')j++;
                i = j;
            }
            for(int k = 0; k < count; k++){
                if (str.charAt(i)!= ' ' && str.charAt(i) != '\n') path.add(String.valueOf(str.charAt(i)).toUpperCase());
            }
        }
        canPath = toString(path);
        length = canPath.length();
    }//ensure the user input path is in canonical form
    
    public int getPathLength(){return length;}
    public char getPathCharacter(int i){return canPath.charAt(i);}

    public void findPath(Maze theMaze) {
        int[] pos = new int[]{0,theMaze.getLeftHole()};
        Movement.DIRECTION direction = Movement.DIRECTION.EAST;
        ArrayList<String> path = new ArrayList<>();
        
        while (pos[0] < theMaze.getColmns() - 1){
            String newDirec = Movement.getDirection(theMaze,direction,pos);
            path.add(newDirec);
            if (newDirec.equals("RR")){
                direction = Movement.changeDirection(direction,newDirec.charAt(0));
                direction = Movement.changeDirection(direction,newDirec.charAt(0));
            }else if (newDirec.equals("R") || newDirec.equals("L")){
                direction = Movement.changeDirection(direction,newDirec.charAt(0));
            }
            if(!newDirec.equals("F")){path.add("F");}
            pos = Movement.move(pos,direction);
        }
        //convert the arraylist to a string in canonical form
        canPath = toString(path);
    }//find a solution to the maze
    public void verifyPath(Maze theMaze, Configuration config) throws MazePathOutOfRange {
        defactorize(config);
        int[] start = new int[]{0,theMaze.getLeftHole()};
        int[] end = new int[]{theMaze.getColmns() - 1,theMaze.getRightHole()};
        Movement.DIRECTION currDirection = Movement.DIRECTION.EAST;
        valid = Arrays.equals(Movement.traversal(theMaze, this, start, currDirection), end);
        if(!valid){
            currDirection = Movement.DIRECTION.WEST;
            valid = Arrays.equals(Movement.traversal(theMaze, this, end, currDirection), start);
        }
    }//check if the user input path is valid
    public void export() {
        if (valid == null){
            int j;
            for (int i = 0; i < canPath.length(); i = i + j) {
                j = 1;
                while ((i + j) < canPath.length() && canPath.charAt(i + j) == canPath.charAt(i)) {
                    j++;
                }
                if (j != 1) {
                    System.out.print(j);
                }
                System.out.print(canPath.charAt(i));
            }
            System.out.println();
        }else{
            if (valid){ System.out.println("correct path");}
            else{System.out.println("incorrect path");}
        }
    }
}
