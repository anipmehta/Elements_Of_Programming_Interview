package pass_through_circles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindPath{
    double minY;
    double maxY;
    public boolean isPathPossible(List<Circle> circles, double height){
        Set<Circle> visited = new HashSet<>();
        for(Circle circle : circles){
            visited.add(circle)
        }
        return true;
    }
    public void helper(List<Circle> circles, Circle current, Set<Circle> visited){
        visited.add(current);
        for(Circle circle : circles){
            if(circle.equals(current) || visited.contains(circle) || circle.){
                return;
            }
            if(circleOverlap(current, circle)){
                minY = Math.min(current.y-current.radius, circle.y - circle.radius);

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