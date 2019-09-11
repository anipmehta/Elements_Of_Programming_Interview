package airbnb_host_max_accomodation;

public class Solution {
    public static int input [][] = {
            {1,2,3},
            {5,1,2,6},
            {5,1,2,6,20,2}
    };
    public static int [] output = {4,11,27};


    public int maxNights(int [] A) {
        int [] cache = new int[A.length];
        return helper(A, 0, cache);
    }

    /**
     *
     * Recursive Equation
     *      C[i] = Math.max(A[i] + C[i+2], C[i+1])
     *      where C[i] is maxNights starting from index i in A
     * @param A
     *          input array
     * @param index
     *          currentIndex
     * @param cache
     *          cache[index] reperesents the maxNights startingt from that index in A
     * @return
     *         maximum totalNights
     */
    private int helper(int[] A, int index, int[] cache) {
        if(index > A.length-1) {
            return 0;
        }
        if(cache[index]==0) {
            cache[index] = Math.max(helper(A, index+1, cache), A[index] + helper(A, index + 2, cache));
        }
        return cache[index];
    }

}
