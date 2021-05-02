package doordash.employee_free_time;


import java.util.*;

public class Solution {
    class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    class Job{
        public int jobIndex;
        public int eId;
        public Interval interval;
        public Job(int eid, int index, Interval interval){
            this.eId = eid;
            this.jobIndex = index;
            this.interval = interval;
        }

    }

        public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
           Queue<Job> pq = new PriorityQueue<>(schedule.size(), new Comparator<Job>() {
               @Override
               public int compare(Job o1, Job o2) {
                   return o1.interval.start - o2.interval.start;
               }
           });
           int current = Integer.MAX_VALUE;
           for (int i=0;i<schedule.size();i++){
              pq.offer(new Job(i, 0, schedule.get(i).get(0)));
              current = Math.min(current, schedule.get(i).get(0).start);
           }
           List<Interval> ans = new ArrayList<>();
           while(!pq.isEmpty()){
               Job job = pq.poll();
               if(job.interval.start > current){
                   ans.add(new Interval(current, job.interval.start));
               }
               int scheduleSize = schedule.get(job.eId).size();
               if(job.jobIndex < scheduleSize - 1){
                   pq.offer(new Job(job.eId, job.jobIndex+1, schedule.get(job.eId).get(job.jobIndex+1)));
               }
               current = Math.max(current, job.interval.end);

           }
            return ans;
        }
}
