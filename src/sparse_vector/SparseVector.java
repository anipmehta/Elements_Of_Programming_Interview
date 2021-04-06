package sparse_vector;

import java.util.HashMap;
import java.util.Map;

/*

Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

 */
class SparseVector {

    public Map<Integer, Integer> vector;

    SparseVector(int[] nums) {
        vector = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                continue;
            }
            vector.put(i, nums[i]);
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int product = 0;
        for(int key : vec.vector.keySet()){
            if(this.vector.containsKey(key)){
                product += (this.vector.get(key) * vec.vector.get(key));
            }
        }
        return product;
    }
}
