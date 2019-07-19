package util;

import java.util.List;

public class TwoDCoordinate implements Coordinate {
    public int x;
    public int y;

    public TwoDCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getManhattanDistance(TwoDCoordinate a){
        return Math.abs(this.x - a.x) + Math.abs(this.y  - a.y);
    }
    @Override
    public boolean isValid(Object[][] axis) {
        return false;
    }

    @Override
    public List<Coordinate> getNeighbours(int direction) {
        return null;
    }
}
