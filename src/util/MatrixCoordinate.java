package util;

import java.util.ArrayList;
import java.util.List;

public class MatrixCoordinate implements Coordinate{
    public int row;
    public int col;
    public MatrixCoordinate(int row, int col){
        this.row = row;
        this.col = col;
    }
    public MatrixCoordinate(){};

    @Override
    public boolean isValid(Object[][] axis) {
        return !(this.row < 0 || this.col < 0 || this.row > axis.length - 1 || this.col > axis[row].length - 1);
    }

    public List<Coordinate> getNeighbours(int i) {
        List<Coordinate> list = new ArrayList<>();
        list.add(new MatrixCoordinate(this.row + 1, this.col));
        list.add(new MatrixCoordinate(this.row - 1, this.col));
        list.add(new MatrixCoordinate(this.row, this.col + 1));
        list.add(new MatrixCoordinate(this.row, this.col - 1));
        if(i==8){
            list.add(new MatrixCoordinate(this.row+1, this.col-1));
            list.add(new MatrixCoordinate(this.row+1, this.col+1));
            list.add(new MatrixCoordinate(this.row-1, this.col-1));
            list.add(new MatrixCoordinate(this.row-1, this.col+1));
        }
        return list;
    }
}

