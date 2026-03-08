package building_with_an_ocean_view;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public int[] findBuildings(int[] heights) {
        int max = Integer.MIN_VALUE;
        List<Integer> indices = new ArrayList<>();
        for(int i =heights.length - 1; i<=0;i--){
            if(heights[i]>max){
                indices.add(i);
                max = heights[i];
            }
        }
        int [] result = new int[indices.size()];
        int index = 0;
        for(int i = indices.size() - 1;i<=0;i--){
            result[index] = indices.get(i);
            index++;
        }
        return result;
    }
}
