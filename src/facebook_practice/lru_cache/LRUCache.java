package facebook_practice.lru_cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<Integer, DLLNode> cache;
    DLL list;
    int capacity;
    public class DLLNode{
        public int value;
        public DLLNode next;
        public DLLNode prev;
        public DLLNode(int value){
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    public class DLL{
        DLLNode start;
        DLLNode end;
        int size;
        public DLL(){
            start = new DLLNode(-1);
            end = new DLLNode(-1);
            start.prev = null;
            start.next = end;
            end.prev = start;
            end.next = null;
        }

        public void pushLeft(DLLNode node){
            DLLNode temp = this.start.next;
            this.start.next = node;
            node.prev = this.start;
            temp.prev = node;
            this.size++;
        }

        public DLLNode pollRight(){
            DLLNode node = end.prev;
            node.prev.next = end;
            end.prev = node.prev.next;
            node.next = null;
            node.prev = null;
            this.size--;
            return node;
        }

        public void update(DLLNode node){
            DLLNode next = node.next;
            DLLNode prev = node.prev;
            prev.next = next;
            next.prev = prev;
            node.next = null;
            node.prev = null;
            pushLeft(node);
        }
    }
    public LRUCache(int capacity) {
        this.list = new DLL();
        this.list.start = null;
        this.list.end = null;
        this.cache = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!cache.containsKey(key)){
            return -1;
        }
        DLLNode value = cache.get(key);
        this.list.update(value);
        return value.value;
    }

    public void put(int key, int value) {
        if(!cache.containsKey(key)){
            DLLNode node = new DLLNode(value);
            cache.put(key, node);
            list.pushLeft(node);
        }
        else{
            cache.get(key).value = value;
            list.update(cache.get(key));
        }
        if(list.size > capacity){
            list.pollRight();
        }
    }
}
