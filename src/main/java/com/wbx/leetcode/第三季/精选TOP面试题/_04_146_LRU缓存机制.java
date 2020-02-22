package com.wbx.leetcode.第三季.精选TOP面试题;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @describe：
 * @Date：2020/2/22 20:56
 * @author：wbx
 */
public class _04_146_LRU缓存机制 {

    class LRUCache {

        private int capacity;

        private Map<Integer, Integer> linkedHashMap;

        public LRUCache(int capacity) {
            linkedHashMap = new LinkedHashMap<>(capacity);
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer value = linkedHashMap.get(key);
            if (value == null) {
                return -1;
            } else {
                linkedHashMap.remove(key);
                linkedHashMap.put(key, value);
                return value;
            }
        }

        public void put(int key, int value) {
            Integer v = linkedHashMap.get(key);
            if (v != null) {
                linkedHashMap.remove(key);
            } else if (linkedHashMap.size() == capacity) {
                //检查缓存是否满了
                Iterator<Map.Entry<Integer, Integer>> iterator = linkedHashMap.entrySet().iterator();
                if (iterator.hasNext()) {
                    iterator.next();
                    iterator.remove();
                }

            }
            linkedHashMap.put(key, value);
        }
    }
}
