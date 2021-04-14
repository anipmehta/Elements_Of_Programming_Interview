package microsoft_preparation.sign_of_product;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        Solution solution = new Solution();
        System.out.println(solution.signOfProduct(new int[] {-2,3,5,-9}));
        System.out.println(solution.signOfProduct(new int[] {-2,3,0,-9}));
        System.out.println(solution.signOfProduct(new int[] {-2,3,-5,-9}));
    }

    public int signOfProduct(int [] arr){
        int negative = 0;
        for(int a : arr){
            if(a<0){
                negative++;
            }
            if(a==0){
                return 0;
            }
        }
        return negative % 2 == 0 ? 1 : -1;
    }
}
