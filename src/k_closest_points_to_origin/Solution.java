package k_closest_points_to_origin;


import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public class Point{
        int X;
        int Y;
        public Point(int x, int y){
            this.X = x;
            this.Y = y;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        Queue<Point> maxHeap = new PriorityQueue<>(k, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double a = distanceFromOrigin(o1);
                double b = distanceFromOrigin(o2);
                if(b>a){
                    return 1;
                }
                if(b<a){
                    return -1;
                }
                return 0;
            }
        });
        for(int [] pointArr : points){
            Point point = new Point(pointArr[0], pointArr[1]);
            maxHeap.offer(point);
            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }
        int [][] result = new int[k][];
        int i = 0;
        while (!maxHeap.isEmpty()){
            Point point = maxHeap.poll();
            result[i++] = new int[] {point.X, point.Y};
        }
        return result;
    }

    public double distanceFromOrigin(Point point){
        return Math.sqrt(Math.pow(point.X - 0,2) + Math.pow(point.Y-0, 2));
    }
}
