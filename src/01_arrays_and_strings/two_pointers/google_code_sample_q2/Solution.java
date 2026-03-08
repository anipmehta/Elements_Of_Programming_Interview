package google_code_sample_q2;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    public int solution(int[] plants, int capacity1, int capacity2) {
        // Starting with empty cans
        int canA = 0;
        int canB = 0;
        // Starting mine pointer with 0th index and watering from left to right
        int mine = 0;
        // Starting friend's pointer with last index and watering from right to left
        int friend = plants.length - 1;
        // Starting refill count with since we are given empty cans
        int refillCount = 0;
        // Iterating over each plant one by one
        while(mine < friend) {
            // if I am not able to water current plant
            if(canA < plants[mine]) {
                refillCount++;
                canA = capacity1;
            }
            // if friend not able to water current plant
            if(canB < plants[friend]) {
                refillCount++;
                canB = capacity2;
            }
            // watering plants and reducing capacity
            canA -= plants[mine];
            canB -= plants[friend];
            // moving to next plants
            mine++;
            friend--;
        }
        // for odd number of plants, meeting in the middle
        if(mine == friend) {
            return canA + canB < plants[mine] ? 1 + refillCount : refillCount;
        }
        return refillCount;
    }
}
