package google_code_sample_domino_rotation;

public class Solution {
    public static void main(String [] args){
        Solution solution = new Solution();
        System.out.println(solution.minDomino(new int[] {3,5,1,2,3}, new int[] {3,6,3,3,4}));
        System.out.println(solution.minDomino(new int[] {2,1,2,4,2,2}, new int[] {5,2,6,2,3,2}));
    }

    public int minDomino(int [] A, int [] B) {
        int minDominoA = helper(A, B, A[0]);
        if(minDominoA!=-1){
            return minDominoA;
        }
        return helper(A,B, B[0]);

    }

    private int helper(int[] A, int[] B, int first) {
        int rotateA=0;
        int rotateB=0;
        for(int i=0;i<A.length;i++) {
            if(first!=A[i] && first!=B[i]){
                return -1;
            }
            if(A[i]!=first){
                rotateA++;
            }
            if(B[i]!=first){
                rotateB++;
            }
        }
        return Math.min(rotateA, rotateB);
    }
}
