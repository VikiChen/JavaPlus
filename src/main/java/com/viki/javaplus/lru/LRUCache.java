package com.viki.javaplus.lru;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * LRU  算法  双向链表 + hashmap
 * @author chenyf
 */
public class LRUCache<K,V> {
    private final int cacheSize;
    private LinkedList<K> cacheList = new LinkedList<>();
    private HashMap<K,V> map = new HashMap<>();

    public LRUCache(int cacheSize){
        this.cacheSize = cacheSize;
    }

    public synchronized void put(K key,V val){
        if(!map.containsKey(key)){
            if(map.size()>cacheSize){
                removeLastElement();
            }
            cacheList.addFirst(key);
            map.put(key,val);
        }else {
            moveToFirst(key);
        }
    }

    public V get(K key){
        if(!map.containsKey(key)){
            return null;
        }else {
            moveToFirst(key);
            return map.get(key);
        }
    }

    private synchronized void moveToFirst(K key) {
        cacheList.remove(key);
        cacheList.addFirst(key);
    }

    private synchronized void removeLastElement() {
        K key = cacheList.removeLast();
        map.remove(key);
    }

}
