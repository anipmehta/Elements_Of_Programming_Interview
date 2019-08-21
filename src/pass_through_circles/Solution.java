package pass_through_circles;

import util.Circle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    static double minY;
    static double maxY;
    static boolean pathExist = true;
    public static void main(String [] args){
        List<Circle> circles = new ArrayList<>();
        circles.add(new Circle(2,2,2));
        circles.add(new Circle(2, 4, 4));
        circles.add(new Circle(6,6, 6));
        System.out.println(isPathPossible(circles, 10));

    }
    public static boolean isPathPossible(List<Circle> circles, double height){
        Set<Circle> visited = new HashSet<>();
        Circle initial = circles.get(0);
        minY = initial.y - initial.radius;
        maxY = initial.y + initial.radius;
        helper(circles, circles.get(0), visited, height);
        if(minY <= 0 && maxY >= height){
            pathExist = false;
        }
        return pathExist;
    }
    public static void helper(List<Circle> circles, Circle current, Set<Circle> visited, double height){
        visited.add(current);
        for(Circle circle : circles){
            if(circle.equals(current) || visited.contains(circle)){
                continue;
            }
            if(circleOverlap(current, circle)){
                minY = Math.min(minY, Math.min(current.y - current.radius, circle.y - circle.radius));
                maxY = Math.max(maxY, Math.max(current.y + current.radius, circle.y + circle.radius));
                if(minY <= 0 && maxY >= height){
                    pathExist = false;
                    return;
                }
                helper(circles, circle, visited, height);
            }
        }
    }
    public static boolean circleOverlap(Circle c1, Circle c2) {
        return c1.radius + c2.radius >= Math.sqrt(Math.pow(c1.x - c2.x, 2) +
                Math.pow(c1.y - c2.y, 2));
    }
}