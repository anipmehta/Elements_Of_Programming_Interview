package odd_even_jump;

import java.util.*;

public class Solution2 {
    public ArrayList<String> popularNToys(int numToys, int topToys, List<String> toys, int numQuotes,
                                          List<String> quotes) {
        Map<String, Integer> map = new HashMap<>();
        for(String quote : quotes) {
            for(String toy : toys){
                if(quote.toLowerCase().contains(toy.toLowerCase())){
                    map.put(toy, map.getOrDefault(toy, 0) + 1);
                }
            }
        }
        PriorityQueue<HeapHelper> minHeap = new PriorityQueue<>(topToys, new Comparator<HeapHelper>() {
            @Override
            public int compare(HeapHelper o1, HeapHelper o2) {
                if(o1.count == o2.count) {
                    return o2.toy.compareTo(o1.toy);
                }
                return o1.count - o2.count;
            }
        });
        int topItems = topToys > numToys ? map.size() : topToys;
        for(String toy : map.keySet()) {
            minHeap.offer(new HeapHelper(toy, map.get(toy)));
            if(minHeap.size() > topItems){
                minHeap.poll();
            }
        }
        ArrayList<String> topToysList = new ArrayList<>();
        while(!minHeap.isEmpty()){
            topToysList.add(minHeap.poll().toy);
        }
        Collections.reverse(topToysList);
        return topToysList;
    }
    class HeapHelper{
        public String toy;
        public int count;
        public HeapHelper(String toy, int count) {
            this.toy = toy;
            this.count = count;
        }
    }
    public static void main(String [] args) {
        Solution2 solution2 = new Solution2();
        String [] quotes = {"Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
                "The new Elmo dolls are super high quality",
                "Expect the Elsa dolls to be very popular this year, Elsa!",
                "Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
                "For parents of older kids, look into buying them a drone",
                "Warcraft is slowly rising in popularity ahead of the holiday season elsa"};
        String [] toys = {"elmo", "elsa", "legos", "drone", "tablet", "warcraft"};
        for(String toy : solution2.popularNToys(6, 8, Arrays.asList(toys), 6, Arrays.asList(quotes))){
            System.out.println(toy);
        }
    }
}
