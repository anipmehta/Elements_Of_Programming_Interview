package util;

import java.util.List;

public interface Coordinate{
    public boolean isValid(Object [][] axis);
    public List<Coordinate> getNeighbours(int direction);
    public int getManhattanDistance(Coordinate point);
}
