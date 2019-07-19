package util;

import java.util.ArrayList;
import java.util.List;

public class Coordinate {
    public int row;
    public int col;
    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }
    public Coordinate(){};

    public boolean isValid(Character[][] a) {
        return !(this.row < 0 || this.col < 0 || this.row > a.length - 1 || this.col > a[row].length - 1);
    }

    public List<Coordinate> getNeighbours(int i) {
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(this.row + 1, this.col));
        list.add(new Coordinate(this.row - 1, this.col));
        list.add(new Coordinate(this.row, this.col + 1));
        list.add(new Coordinate(this.row, this.col - 1));
        return list;
    }
}

