package lrucache;

import lrucache.cache.LRUCache;

public class Main {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        System.out.println(cache.get(1));

        cache.put(4, "D");

        System.out.println(cache.get(2)); // null

        System.out.println(cache.get(1)); // A

        System.out.println(cache.get(3)); // C

        System.out.println(cache.get(4)); // D
    }
}
