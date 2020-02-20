package com.wbx.leetcode.链表;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @describe： https://leetcode-cn.com/problems/lru-cache/
 * @Date：2019/12/29 22:10
 * @author：wbx
 */
public class _146_LRU缓存机制 {

    class LRUCache {

        private int capacity;

        private Map<Integer, Integer> map = new LinkedHashMap();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (map.keySet().contains(key)) {
                Integer result = map.get(key);
                map.remove(key);
                //保证最近访问的放在尾部
                map.put(key, result);
                return result;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.keySet().contains(key)) {
                map.remove(key);
            } else if (map.size() == capacity) {//容量已经超出，移除头元素
                //删除头结点
                Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
                iterator.next();
                iterator.remove();
            }

            //保证最近访问的放在尾部
            map.put(key, value);
        }
    }


}
