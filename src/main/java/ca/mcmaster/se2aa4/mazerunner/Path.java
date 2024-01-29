package ca.mcmaster.se2aa4.mazerunner;

public interface Path<T> {
    void export();
    Integer getPathLength() ;
    char getPathCharacter(int i) throws StringIndexOutOfBoundsException;
    void findPath(Maze theMaze);
}
