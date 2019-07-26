package util;

import java.util.List;

public class TwoDCoordinate implements Coordinate {
    public int x;
    public int y;

    public TwoDCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean isValid(Object[][] axis) {
        return false;
    }

    @Override
    public List<Coordinate> getNeighbours(int direction) {
        return null;
    }

    @Override
    public int getManhattanDistance(Coordinate point) {
        TwoDCoordinate pointTwoD = (TwoDCoordinate) point;
        return Math.abs(this.x - pointTwoD.x) + Math.abs(this.y  - pointTwoD.y);
    }
}
