package google_code_sample_water_plants;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    public int refills(int [] plants, int X, int Y){
        if(plants.length==1){
            return 1;
        }
        int count = 0;
        int low=0;
        int high=plants.length-1;
        int canA = 0;
        int canB = 0;
        while(low<high){
            if(canA < plants[low]){
                count++;
                canA = X;
            }
            if(canB < plants[high]){
                count++;
                canB = Y;
            }
            canA -= plants[low++];
            canB -= plants[high--];
        }
        if(low==high){
            return canA + canB < plants[low] ?  count + 1 : count;
        }
        return count;
    }
}
