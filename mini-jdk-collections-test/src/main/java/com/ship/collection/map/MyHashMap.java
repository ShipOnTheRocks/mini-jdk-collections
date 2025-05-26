package com.ship.collection.map;


import java.util.Map;
import java.util.Set;

/**
 * MyHashMap
 *
 * @author mac
 * @version 1.0 2025/5/26 19:42
 */
public class MyHashMap<K,V>  {
    // 默认初始化容量
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    // 最大容量
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    // 默认负载因子
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    // 链表转红黑树阈值
    private static final int TREEIFY_THRESHOLD = 8;

    // 红黑树退化成链表阈值
    private static final int UNTREEIFY_THRESHOLD = 6;

    // 链表转红黑树的最小数组长度
    private static final int MIN_TREEIFY_CAPACITY = 64;

    // Node 定义
    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }


    }

    // TreeNode 定义
    static final class TreeNode<K, V>  {
        TreeNode<K,V> parent;  // red-black tree links
        TreeNode<K,V> left;

        TreeNode<K,V> right;
        TreeNode<K,V> prev;    // needed to unlink next upon deletion
        boolean red;
    }

    // Node数组
    transient Node<K, V>[] table;

    transient Set<Map.Entry<K,V>> entrySet;

    // size
    transient int size;

    // modCount
    transient int modCount;

    // threshold
     int threshold;

    // 负载因子
    final float loadFactor;


    public MyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    public MyHashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
//        putMapEntries(m, false);
    }

    /**
     * 容量始终保持2的幂次方
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }



}
