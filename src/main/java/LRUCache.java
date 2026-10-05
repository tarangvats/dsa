import java.util.HashMap;
import java.util.Map;
public class LRUCache {
    public static void main(String[] args){
        System.out.println("Hello world");
        LRUCache lruCache = new LRUCache(4);
        lruCache.put(1, 10);
        lruCache.put(2, 20);
        lruCache.put(3, 30);
        lruCache.put(4, 40);
        lruCache.put(5, 50);
        System.out.println(lruCache.get(2));
        lruCache.display();
    }
    class Node{
        int key;
        int value;
        Node next;
        Node prev;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
    private int capacity;
    private Map<Integer, Node>map = new HashMap<>();
    private Node head;
    private Node tail;

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    public int get(int key){
        if(!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        deleteNodeFromCache(node);
        addNodeAfterHead(node);
        return node.value;
    }
    public void addNodeAfterHead(Node node){
        Node topNode = head.next;
        node.next = topNode;
        head.next = node;
        node.prev = head;
        topNode.prev=node;
        map.put(node.key, node);
    }

    public void deleteNodeFromCache(Node node){
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public void put(int key, int value){
        if(map.containsKey(key)){
            Node node = map.get(key);
            deleteNodeFromCache(node);
            addNodeAfterHead(node);
            node.value = value;
            map.put(key, node);
        }
        else{
            if(this.capacity == map.size()){
                map.remove(tail.prev.key);
                deleteNodeFromCache(tail.prev);
            }

            Node node = new Node(key, value);
            map.put(key, node);
            addNodeAfterHead(node);

        }


    }
    public void display(){
        Node current = head.next;
        while(current != tail){
            System.out.print("[" + current.key + ":" + current.value + "] ");
            current = current.next;
        }
        System.out.println();
    }
}
