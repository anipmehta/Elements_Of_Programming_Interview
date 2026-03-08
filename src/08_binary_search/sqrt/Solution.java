package microsoft_preparation.sqrt;

import java.math.BigInteger;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        Solution solution = new Solution();
        System.out.println(solution.mySqrt(2147395599));
    }

    public int mySqrt(int x) {
        int start = 0;
        int end = x;
        while(start<=end){
            int mid = start + (end - start)/2;
            BigInteger b1 = BigInteger.valueOf(mid);
            b1 = b1.multiply(b1);
            if(b1.intValue()==x){
                return mid;
            }
            if(b1.longValue() <x){
                start = mid+1;
            }
            else{
                end = mid-1;
            }
        }
        return end;
    }
}
