package demoLinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现LRUCache类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity初始化 LRU 缓存
 * int get(int key)如果关键字key存在于缓存中，则返回关键字的值，否则返回-1。
 * void put(int key, int value)如果关键字key已经存在，则变更其数据值value；如果不存在，则向缓存中插入该组key-value。
 * 如果插入操作导致关键字数量超过capacity，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以O(1)的平均时间复杂度运行。
 */
public class LRUCache146 {
}

class LRUCache {
    class DLinkNode {
        int key;
        int value;
        DLinkNode prev;
        DLinkNode next;

        public DLinkNode() {
        }

        public DLinkNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    private Map<Integer, DLinkNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 创建伪头部和伪尾部节点
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果key存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            // 如果key不存在，创建一个新的节点
            DLinkNode newNode = new DLinkNode(key, value);
            // 添加到哈希表
            cache.put(key, newNode);
            // 添加到双向链表的头部
            addToHead(newNode);
            size++;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                size--;
            }
        } else {
            // 如果key存在，先通过哈希表定位，再修改value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkNode removeTail() {
        DLinkNode node = tail.prev;
        removeNode(node);
        return node;
    }
}
