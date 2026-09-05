public class BinarySearchTree {


    public static void main(String[] args){
        BinarySearchTree bst = new BinarySearchTree();
        int[] nums = {10, 5, 15, 3, 7, 12, 18};
        bst.populatedSorted(nums);
        bst.display();
    }

    public static class Node{
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value){
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 0;
        }

        public int getValue(){
            return value;
        }
    }


    private Node root;

    public int height(Node node){
        if(node==null)
            return -1;
        return node.height;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public void insert(int value){
        root = insert(root, value);
    }

    private Node insert(Node node, int value){
        if(node==null){
            node = new Node(value);
            return node;
        }

        if(value<node.value)
            node.left = insert(node.left, value);
        if(value>node.value)
            node.right = insert(node.right, value);
        node.height = Math.max(height(node.left),height(node.right)) + 1;
        return node;
    }

    public boolean balanced(){
        return balanced(root);
    }

    private boolean balanced(Node node){

        if(node==null)
            return true;

        return Math.abs(height(node.left) - height(node.right)) <=1;
    }


    public void populate(int[] nums){
        for(int i = 0; i<nums.length; i++){
            insert(nums[i]);
        }
    }

    public void display(){
        display(root,"Root Node: ");
    }

    private void display(Node node, String details){
        if(node==null)
            return;
        System.out.println(details+ node.getValue());
        display(node.left, "Left child of  "+ node.getValue()+" : ");
        display(node.right,"Right child of "+ node.getValue()+" : ");

    }

    public void populatedSorted(int[] nums) {
        populatedSorted(nums, 0, nums.length);

    }

    private void populatedSorted(int[] nums, int start , int end){
        if(start>=end) return;

        int mid = start + (end-start)/2;
        this.insert(nums[mid]);
        populatedSorted(nums, start, mid);
        populatedSorted(nums, mid +1, end);

    }

    public BinarySearchTree(){

    }
}
