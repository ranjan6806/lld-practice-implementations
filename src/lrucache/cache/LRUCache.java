package lrucache.cache;

import lrucache.datastructure.DoublyLinkedList;
import lrucache.model.Node;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final DoublyLinkedList<K, V> usageList;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }

        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.usageList = new DoublyLinkedList<>();
    }

    public int size() {
        return cache.size();
    }

    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }

        usageList.moveToFront(node);
        return node.getValue();
    }

    public void put(K key, V value) {
        Node<K, V> existingNode = cache.get(key);
        if (existingNode != null) {
            existingNode.setValue(value);
            usageList.moveToFront(existingNode);
            return;
        }

        Node<K, V> node = new Node<>(key, value);
        cache.put(key, node);
        usageList.addFirst(node);

        if (cache.size() > capacity) {
            Node<K, V> lruNode = usageList.removeLast();
            cache.remove(lruNode.getKey());
        }
    }
}
