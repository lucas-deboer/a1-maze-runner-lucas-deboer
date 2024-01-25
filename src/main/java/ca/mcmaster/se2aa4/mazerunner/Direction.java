package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;
import java.util.Objects;

public class Direction {
    //Let the vectors be represented as [x,y], with possible values -1,1. 1 == down/right, -1 == left/up
    public static int[] changeDirection(int[] currDirection, char move){
        if (Arrays.equals(currDirection,new int[]{0,1})){//faced North
            if(move == 'R') {
                currDirection[0] = 1;
            }else{
                currDirection[0] = -1;
            }
            currDirection[1] = 0;
        }else if (Arrays.equals(currDirection,new int[]{0,-1})) {//faced south
            if(move == 'R') {
                currDirection[0] = -1;
            }else{
                currDirection[0] = 1;
            }
            currDirection[1] = 0;
        }else if (Arrays.equals(currDirection,new int[]{1,0})) {//faced east
            if(move == 'R') {
                currDirection[1] = 1;
            }else{
                currDirection[1] = -1;
            }
            currDirection[0] = 0;
        }else{//faced west
            if(move == 'R') {
                currDirection[1] = -1;
            }else{
                currDirection[1] = 1;
            }
            currDirection[0] = 0;
        }
        return currDirection;
    }
    public static char getDirection(Maze theMaze, int[] currDirection, int[] position) {
        char newDirec;
        int[] right = changeDirection(new int[]{currDirection[0],currDirection[1]}, 'R');
        int[] left = changeDirection(new int[]{currDirection[0],currDirection[1]}, 'L');
        if (Objects.equals(theMaze.getTileValue(theMaze.add(position, right)), "P")){//if the right position is open, travel there.
            newDirec = 'R';
            currDirection = right;
        }else if (Objects.equals(theMaze.getTileValue(theMaze.add(position, currDirection)), "P")){//else continue forwards
            newDirec = 'F';
        }else if(Objects.equals(theMaze.getTileValue(theMaze.add(position, left)),"P")){//else go left
            newDirec = 'R';
            currDirection = left;
        }else{//else dead end, reverse direction
            newDirec = 'R';
            currDirection = right;
        }
        return newDirec;
    }
}
