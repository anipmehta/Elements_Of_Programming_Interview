package google_code_sample_q1;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public int solution(int[] A, int[] B) {
        // write your code in Java SE 8
        int makingAEqual = minRotations(A, B, A[0]);
        if(makingAEqual!=-1){
            return makingAEqual;
        }
        return minRotations(A, B, B[0]);
    }
    private int minRotations(int [] A, int [] B, int comparator) {
        int rotationA = 0;
        int rotationB = 0;
        for (int i=0;i<A.length;i++) {
            if(comparator != A[i] && comparator != B[i]) {
                return -1;
            }
            if(A[i] != comparator) {
                rotationA++;
            }
            if(B[i] != comparator) {
                rotationB++;
            }
        }
        return Math.min(rotationA, rotationB);
    }
}
