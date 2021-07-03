package yanFaZuiAi;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCacheExtendsLinkedHashMap extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    //初始化
    public LRUCacheExtendsLinkedHashMap(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    //get方法，若没有返回 -1
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    //put方法
    public void put(int key, int value) {
        super.put(key, value);
    }

    //remove方法，当size溢出
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
