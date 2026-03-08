package reconstruct_itinerary;

import java.util.*;

public class Solution{
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> map = new HashMap<>();
        for(String [] ticket : tickets){
            if(!map.containsKey(ticket[0])){
                map.put(ticket[0], new ArrayList<>());
            }
            map.get(ticket[0]).add(ticket[1]);
        }
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }
        List<String> itinerary = new ArrayList<>();
        helper(map, itinerary, "JFK");
        return itinerary;
    }

    private void helper(Map<String, List<String>> map, List<String> itinerary, String origin) {
        if(!map.containsKey(origin)){
            return;
        }
        itinerary.add(origin);
        String next = map.get(origin).get(0);
        map.get(origin).remove(0);
        helper(map, itinerary, next);
        map.get(origin).add(0, next);
    }
}
