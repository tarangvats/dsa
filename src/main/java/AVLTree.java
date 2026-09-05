public class AVLTree {
    public static void main(String[] args){
        System.out.println("Hello World");
        int[] nums = {10,20,30,40,50,60,70,80,90};
        AVLTree avlTree = new AVLTree();
        avlTree.populatedSorted(nums);
        avlTree.display();
        System.out.println(avlTree.height(avlTree.root));
    }

    private Node root;

    public static class Node{
        private int value;
        private int height;
        private Node left;
        private Node right;

        public Node(int value){
            this.value = value;
            this.height = 0;
        }
        public int getValue(){
            return value;
        }

    }

    public int height(Node node){
        if(node==null)
            return -1;
        return node.height;
    }



    public boolean isEmpty(){
        return this.root==null;
    }

    public void insert(int value){
        root = insert(value, root);
    }


    private Node insert(int value, Node node){
        if(node==null)
        {
            node = new Node(value);
            return node;
        }

        if(node.getValue() < value){
            node.right = insert(value, node.right);

        }
        if(node.getValue() > value){
            node.left = insert(value, node.left);
        }

        node.height = Math.max(height(node.left),height(node.right)) + 1;

        return node;
    }

    public void populate(int[] nums){
        populate(nums, root);
    }

    private void populate(int[] nums, Node node){
        for(int i = 0; i<nums.length; i++){
            insert(nums[i]);
        }
    }

    public void display(){
        display(root, "Root Node: ");
    }

    private void display(Node node, String details){
        if(node==null)
            return;
        System.out.println(details + node.getValue());
        display(node.left, "Left Child of "+node.getValue()+" : ");
        display(node.right, "Right Child of "+node.getValue()+" : ");
    }

    public boolean balanced(){
        return balanced(root);
    }

    private boolean balanced(Node node){

        if(node==null)
            return true;

        return Math.abs(height(node.left) - height(node.right)) <=1;
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

    public Node rotateRight(Node p){
        Node c = p.left;
        Node t = c.right;

        c.right = p;
        p.left = t;
        p.height = Math.max(height(p.left),height(p.right)) + 1;
        c.height = Math.max(height(c.left),height(c.right)) + 1;

        return c;
    }

    public Node rotateLeft(Node c){
        Node p = c.right;
        Node t = p.left;

        p.left = c;
        c.right = t;
        c.height = Math.max(height(c.left),height(c.right)) + 1;
        p.height = Math.max(height(p.left),height(p.right)) + 1;

        return p;
    }

    public Node rotate(Node node){
        if(balanced(node))
            return node;
        if(height(node.left) - height(node.right) >1){ // Left Heavy
            if(height(node.left.left) - height(node.left.right) >0){
                return rotateRight(node);
            }
            if((height(node.left.left) - height(node.left.right))<0){
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        if(height(node.right) - height(node.left) > 1){
            if(height(node.right.right) - height(node.right.left) >0)
                return rotateLeft(node);
            if(height(node.right.right) - height(node.right.left)<0){
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

        }

        return rotate(node);
    }



    public AVLTree() {
        // Constructor implementation
    }
}
