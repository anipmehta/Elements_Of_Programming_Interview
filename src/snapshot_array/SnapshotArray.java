package snapshot_array;

import java.util.*;

public class SnapshotArray {
    private Map<Integer, List<Node>> indexToSnapshotValues;
    int snapshotId;

    class Node{
        int snapshotId;
        int value;

        public Node(int id, int value){
            this.snapshotId = id;
            this.value = value;
        }
    }

    public SnapshotArray(int length) {
        indexToSnapshotValues = new HashMap<>();
        snapshotId = 0;
        for(int i=0;i<length;i++){
            indexToSnapshotValues.put(i, new ArrayList<>());
            indexToSnapshotValues.get(i).add(new Node(snapshotId, 0));
        }
    }

    public void set(int index, int val) {
        List<Node> values = indexToSnapshotValues.get(index);
        if(values.get(values.size() - 1).snapshotId !=snapshotId){
            values.add(new Node(snapshotId, val));
        }
        else{
            values.get(values.size()-1).value = val;
        }
    }

    public int snap() {
        snapshotId++;
        return snapshotId - 1;
    }

    public int get(int index, int snap_id) {
        if(!indexToSnapshotValues.containsKey(index)){
            return -1;
        }
        List<Node> values = indexToSnapshotValues.get(index);
        int actualIndex = Collections.binarySearch(values, new Node(snap_id, -1), new Comparator<Node>() {
            public int compare(Node a, Node b){
                return a.snapshotId - b.snapshotId;
            }
        });
        if(actualIndex < 0){
            actualIndex = -actualIndex;
        }
        return values.get(actualIndex).value;
    }
}
