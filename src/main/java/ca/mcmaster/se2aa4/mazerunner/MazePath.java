package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazePath implements Path<String>{
    private static final Logger logger = LogManager.getLogger();
    private final static Character[] VALIDINPUTS = new Character[]{'F','f','L','l','R','r',' ','\n'};
    private String canPath = "";
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
        logger.info("Converting the path input to canonical form");
        ArrayList<String> path = new ArrayList<>();
        int count,j;
        String str = config.path();
        for (int i = 0; i < str.length(); i++){
            count = 1;
            if (!CollectionUtils.containsAny(List.of(str.charAt(i)), VALIDINPUTS)){//if character isn't in tiles, it is a number
                j = i+1;
                while(Character.isDigit(str.charAt(j))){//get the entire number
                    j++;
                }
                count =  Integer.parseInt(str.substring(i,j));
                while(str.charAt(j) == ' ' || str.charAt(j) == '\n')j++; //pass over spaces and new lines
                i = j;
            }
            for(int k = 0; k < count; k++){//add the character to the path count times.
                if (str.charAt(i)!= ' ' && str.charAt(i) != '\n') path.add(String.valueOf(str.charAt(i)).toUpperCase());
            }
        }
        canPath = toString(path);
        length = canPath.length();
        logger.info("Conversion complete.");
    }//ensure the user input path is in canonical form
    @Override
    public Integer getPathLength(){return length;}
    @Override
    public char getPathCharacter(int i){return canPath.charAt(i);}
    @Override
    public void export() {
        if (valid == null){ //output if the program found a path
            int j;
            for (int i = 0; i < canPath.length(); i = i + j) {//print path in factorized form
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
        }else{ //output if the program verified a path
            if (valid){ System.out.println("correct path");}
            else{System.out.println("incorrect path");}
        }
    }
    @Override
    public void findPath(Maze theMaze) {
        logger.info("Finding path through the maze.");
        int[] pos = new int[]{0,theMaze.getLeftHole()};
        DIRECTION direction = DIRECTION.EAST;
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
        logger.info("Path found.");
    }//find a solution to the maze
    public void verifyPath(Maze theMaze, Configuration config) throws MazePathOutOfRange {
        logger.info("Verifying the given path.");
        defactorize(config);
        int[] start = new int[]{0,theMaze.getLeftHole()};
        int[] end = new int[]{theMaze.getColmns() - 1,theMaze.getRightHole()};
        DIRECTION currDirection = DIRECTION.EAST;
        //check from east to west
        valid = Arrays.equals(Movement.traversal(theMaze, this, start, currDirection), end);
        if(!valid){// if path is not valid east to west, check west to east.
            currDirection = DIRECTION.WEST;
            valid = Arrays.equals(Movement.traversal(theMaze, this, end, currDirection), start);
        }
        logger.info("Validation process complete.");
    }//check if the user input path is valid
}
