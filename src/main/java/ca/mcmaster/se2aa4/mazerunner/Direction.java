package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class Direction {
    //Let the vectors be represented as [x,y], with possible values -1,1. 1 == up/right, -1 == left/down
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
                currDirection[1] = -1;
            }else{
                currDirection[1] = 1;
            }
            currDirection[0] = 0;
        }else{//faced west
            if(move == 'R') {
                currDirection[1] = 1;
            }else{
                currDirection[1] = -1;
            }
            currDirection[0] = 0;
        }
        return currDirection;
    }
}
