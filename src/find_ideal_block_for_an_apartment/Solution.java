package find_ideal_block_for_an_apartment;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public static void main(String [] args){
        List<List<String>> data = new ArrayList<>();
        String arr1 [] = {"Groceries", "Hospital", "College"};
        String arr2 [] = {"School", "Restaurants", "Pharmacy"};
        String arr3 [] = {"Food Trucks","","Police Station"};
        String arr4 [] = {"Shopping Mall", "Fire Station", "Hospital"};
        String arr5 [] = {"School", "Hospital", "Restaurants"};
        data.add(Arrays.asList(arr1));
        data.add(Arrays.asList(arr2));
        data.add(Arrays.asList(arr3));
        data.add(Arrays.asList(arr4));
        System.out.println(findOptimalBlock(data, Arrays.asList(arr5)));
    }
    public static int findOptimalBlock(List<List<String>> blockFacilities, List<String> facilities) {
        Map<Integer, Set<String>> blockToFacilities = new HashMap<>();
        for (int i = 0; i < blockFacilities.size(); i++) {
            blockToFacilities.put(i + 1, new HashSet<>(blockFacilities.get(i)));
        }
        int[] cache = new int[blockFacilities.size()];
        Arrays.fill(cache, 0);
        for (String facility : facilities) {
            for (int i = 0; i < blockFacilities.size(); i++) {
                cache[i] += helper(blockToFacilities, facility, i+1);
            }
        }
        int min_distance = Integer.MAX_VALUE;
        int ideal_block = -1;
        for(int i=0;i<cache.length;i++) {
            if (cache[i] < min_distance) {
                min_distance = cache[i];
                ideal_block = i;
            }
        }
        return ideal_block+1;
    }

    private static int helper(Map<Integer, Set<String>> blockToFacilities, String facility, int blockIndex) {
        if(blockIndex<0 || blockIndex>blockToFacilities.size()) {
            return Integer.MAX_VALUE;
        }
        if(blockToFacilities.get(blockIndex).contains(facility)){
                return 0;
            }
        return 1 + Math.min(helper(blockToFacilities, facility, blockIndex-1),
                    helper(blockToFacilities, facility, blockIndex+1));
    }
}
