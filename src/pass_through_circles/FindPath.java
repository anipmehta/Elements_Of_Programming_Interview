package pass_through_circles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindPath{
    double minY;
    double maxY;
    public boolean isPathPossible(List<Circle> circles, double height){
        Set<Circle> visited = new HashSet<>();
        return true;
    }
    public void helper(List<Circle> circles, Circle current, Set<Circle> visited, double height){
        visited.add(current);
        for(Circle circle : circles){
            if(circle.equals(current) || visited.contains(circle)){
                return;
            }
            if(circleOverlap(current, circle)){
                minY = Math.min(current.y - current.radius, circle.y - circle.radius);
                maxY = Math.min(current.y + current.radius, circle.y + circle.radius);
                if(minY < 0 && maxY > height){

                }
            }
        }
    }
    public boolean circleOverlap(Circle c1, Circle c2) {
        return c1.radius + c2.radius <= Math.sqrt(Math.pow(c1.x - c2.x, 2) +
                Math.pow(c1.y - c2.y, 2));
    }
}
class Circle{
    double x;
    double y;
    double radius;
}