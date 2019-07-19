package util;

import java.util.HashMap;
import java.util.Map;

public class HeapItem {
    Map<Object, Object> keysDict;
    public HeapItem(){
        keysDict = new HashMap<>();
    }
    public Object getValue(Object key){
        if(!keysDict.containsKey(key)){
            return null;
        }
        return keysDict.get(key);
    }
    public void addKey(Object key, Object value){
        keysDict.put(key, value);
    }
}
