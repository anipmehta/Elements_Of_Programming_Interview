package is_my_restaurant_first_come_first_serve;

public class Solution {
    public static void main(String [] args){
       int [] takeOut = {1,5,6};
       int [] dineIn = {2,4,7};
       int [] processedOrders = {1,2,4,6,5,7};
       int [] processedOrders2 = {1,2,4,5,6,7};

       System.out.println(checkFCFS(takeOut, dineIn, processedOrders));
       System.out.println(checkFCFS(takeOut, dineIn, processedOrders2));

    }
    public static boolean checkFCFS(int [] takeOut, int [] dineIn, int [] processedOrders){
        int takeOutIndex = 0;
        int dineInIndex = 0;
        for(int order : processedOrders){
            if(takeOut[takeOutIndex]!=order && dineIn[dineInIndex]!=order){
                return false;
            }
            if(takeOut[takeOutIndex]==order){
                if(takeOutIndex<takeOut.length-1){
                    takeOutIndex++;
                    continue;
                }
            }
            else if(dineIn[dineInIndex]==order){
                if(dineInIndex<dineIn.length-1){
                    dineInIndex++;
                    continue;
                }
            }
        }
        return true;
    }
}
