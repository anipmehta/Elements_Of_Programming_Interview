package analyze_user_website_3_seq_pattern;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    class Visit{
        public int time;
        public String user;
        public String website;
        public Visit(int time, String user, String website){
            this.time = time;
            this.user = user;
            this.website = website;
        }
    }
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Visit> visits = new ArrayList<>();
        for(int i = 0;i< username.length;i++){
            visits.add(new Visit(timestamp[i], username[i], website[i]));
        }
        Collections.sort(visits, new Comparator<Visit>() {
            @Override
            public int compare(Visit o1, Visit o2) {
                return o1.time - o2.time;
            }
        });
        Map<String, List<Visit>> userVisits = new HashMap<>();
        for(Visit visit : visits){
            if(!userVisits.containsKey(visit.user)){
                userVisits.put(visit.user, new ArrayList<>());
            }
            userVisits.get(visit.user).add(visit);
        }
        Map<String, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        String maxSeq = "";
        for(Map.Entry<String, List<Visit>> entry : userVisits.entrySet()){
            for(String seq : get3seq(entry.getValue())){
                map.put(seq, map.getOrDefault(seq, 0) + 1);
                if(map.get(seq) > max) {
                    System.out.println(map.get(seq));
                    max = map.get(seq);
                    maxSeq = seq;
                }
                if(map.get(seq) == max && seq.compareTo(maxSeq) < 0){
                    maxSeq = seq;
                }
            }
        }

        String [] result = maxSeq.split(" ");
        List<String> ans = new ArrayList<>();
        for(String res : result){
            ans.add(res);
        }
        return ans;
    }

    private Set<String> get3seq(List<Visit> value) {
        int len = value.size();
        Set<String> res = new HashSet<>();
        for(int i = 0;i<len;i++){
            for(int j=i+1;j<len;j++){
                for(int k=j+1 ; k<len;k++){
                    res.add(new String(value.get(i).website + " " + value.get(j).website + " " + value.get(k).website));
                }
            }
        }
        return res;
    }
}
