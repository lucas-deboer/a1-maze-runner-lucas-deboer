package ca.mcmaster.se2aa4.mazerunner;

import java.util.Objects;

public class Movement {
    //Let the vectors be represented as [x,y], with possible values -1,1. 1 == down/right, -1 == left/up
    public static int[] move(int[] coords, DIRECTION d){
        int[] tmp = new int[2];
        tmp[0] =  coords[0] + d.getValue()[0];
        tmp[1] =  coords[1] + d.getValue()[1];
        return tmp;
    }
    public static DIRECTION changeDirection(DIRECTION currDirection, char move){
        //determines new Direction based on current direction and the next move.
        if (currDirection == DIRECTION.NORTH){
            if(move == 'R') {
                currDirection = DIRECTION.EAST;
            }else{
                currDirection = DIRECTION.WEST;
            }
        }else if (currDirection == DIRECTION.SOUTH) {
            if(move == 'R') {
                currDirection = DIRECTION.WEST;
            }else{
                currDirection = DIRECTION.EAST;
            }
        }else if (currDirection == DIRECTION.EAST) {
            if(move == 'R') {
                currDirection = DIRECTION.SOUTH;
            }else{
                currDirection = DIRECTION.NORTH;
            }
        }else{//faced west
            if(move == 'R') {
                currDirection = DIRECTION.NORTH;
            }else{
                currDirection = DIRECTION.SOUTH;
            }
        }
        return currDirection;
    }
    
    public static String getDirection(Maze theMaze, DIRECTION currDirection, int[] position) {
        //determine which way to move. Decided using the right hand method.
        String newDirec;
        DIRECTION right = changeDirection(currDirection, 'R');
        DIRECTION left = changeDirection(currDirection, 'L');
        if (Objects.equals(theMaze.getTileValue(move(position, right)), "P")){//if the right position is open, travel there.
            newDirec = "R";
        }else if (Objects.equals(theMaze.getTileValue(move(position, currDirection)), "P")){//else continue forwards
            newDirec = "F";
        }else if(Objects.equals(theMaze.getTileValue(move(position, left)),"P")){//else go left
            newDirec = "L";
        }else{//else dead end, reverse direction
            newDirec = "RR";
        }
        return newDirec;
    }
    //need to make a function that can deal with the direction/direction changes/
    public static int[] traversal(Maze theMaze, MazePath path, int[] coords, DIRECTION currDirection) throws MazePathOutOfRange{
        //traverses the maze and returns the ending coordinates
        for(int i = 0; i < path.getPathLength(); i++){
            if(path.getPathCharacter(i) == 'F'){
                coords = Movement.move(coords,currDirection);
                if (coords[0] == theMaze.getColmns() || coords[0] < 0){
                    throw new MazePathOutOfRange("Path goes outside the maze");
                }
                if (Objects.equals(theMaze.getTileValue(coords), "W")){//if it moves into a wall, it is invalid
                    return new int[]{-1,-1};
                }
            }else{
                currDirection = changeDirection(currDirection, path.getPathCharacter(i));
            }
        }
        return coords;
    }
}

