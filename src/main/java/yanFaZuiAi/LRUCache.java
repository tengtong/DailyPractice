package yanFaZuiAi;

import java.util.*;

//构造一个LRU
//1. 用一个数组来存储数据，给每一个数据项标记一个访问时间戳，每次插入新数据项的时候，先把数组中存在的数据项的时间戳自增，并将新数据项的时间戳置为0并插入到数组中。
//   每次访问数组中的数据项的时候，将被访问的数据项的时间戳置为0。当数组空间已满时，将时间戳最大的数据项淘汰。
//2. 利用一个链表来实现，每次新插入数据的时候将新数据插到链表的头部；每次缓存命中（即数据被访问），则将数据移到链表头部；
//   那么当链表满的时候，就将链表尾部的数据丢弃。
//3. 利用链表和hashmap。当需要插入新的数据项的时候，如果新数据项在链表中存在（一般称为命中），则把该节点移到链表头部
//   如果不存在，则新建一个节点，放到链表头部，若缓存满了，则把链表最后一个节点删除即可。
//   在访问数据的时候，如果数据项在链表中存在，则把该节点移到链表头部，否则返回-1。这样一来在链表尾部的节点就是最近最久未访问的数据项。
//
// 对于第一种方法，需要不停地维护数据项的访问时间戳，另外，在插入数据、删除数据以及访问数据时，时间复杂度都是O(n)。
// 对于第二种方法，链表在定位数据的时候时间复杂度为O(n)。
// 所以在一般使用第三种方式来是实现LRU算法，hashMap的定位数据是O(1)、链表的增删是O(1)

//思路：hashMap + 双向链表（需要手动实现双向链表）
//     实现原理：通过双向链表的增删便捷性，来维护缓存队列，但是有个链表不能实现O(1)的查找效率，只能靠遍历自身
//             所以在此外还维护了一张map，通过<key,node>来实现节点的快速定位
//            （链表中的节点既是整张链表的一个中间节点，也是一个独立的节点，故可以这样实现，但是数组就不行，数组是一个整体，中间元素并不独立）
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    //map里面，存key-value，value存的是一个双向链表节点
    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    //属性中记录了头、尾节点的位置//头尾是个空节点，不存任何值
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();//tail尾巴
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    //插入到头节点
    private void addToHead(DLinkedNode node) {
        //双向链表，可以再任意节点进行插入、删除
        //这里直接插在头节点之前
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    //移除Node
    private void removeNode(DLinkedNode node) {
        //双向链表直接对node的前后节点进行拼接即可，不需要遍历一遍链表
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        //1、先从链表中移除该节点
        removeNode(node);
        //2、再将该节点添加到链表头部
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}

