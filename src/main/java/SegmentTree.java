public class SegmentTree {
    public static void main(String []args){
        int[] arr = {3,8,6,7,-2,-8,4,9};
        SegmentTree segmentTree = new SegmentTree(arr);
        segmentTree.display();
        System.out.println(segmentTree.query(1,6));
    }

    public static class Node{
        int data;
        int startInterval;
        int endInterval;
        Node left;
        Node right;
        public Node(int startInterval, int endInterval){
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }

    private Node root;

    public SegmentTree(int[] arr){
        this.root = this.constructTree(arr, 0, arr.length-1);
    }

    private Node constructTree(int[] arr, int start, int end){
        // Leaf node
        if(start==end){
            Node leaf = new Node(start, end);
            leaf.data = arr[start];
            return leaf;
        }

        // Create new node with intex you are at

        Node node  = new Node(start,end);
        int mid = start + (end-start)/2;

        node.left = this.constructTree(arr, start, mid);
        node.right = this.constructTree(arr, mid+1, end);


        node.data = node.left.data + node.right.data;
        return node;
    }

    public int query(int qsi, int qei){
        return this.query(this.root,qsi,qei);
    }

    // 1. Check whether index lies in interval [a,b]
    // 2. If yes, check child nodes, if range is out, no change in value, just return
    // 3. in the end, you will reach leaf as recursion will update the tree.

    private int query(Node node, int qsi, int qei){
        if(node.startInterval>=qsi && node.endInterval<=qei)
            return node.data;
        else if(node.startInterval>qei || node.endInterval<qsi)
            return 0;
        else
            return this.query(node.left, qsi,qei) + this.query(node.right,qsi,qei);
    }

    public void display(){
        display(this.root);
    }
    private void display(Node node){
        String str = "";

        if(node.left!=null){
            str = str + "Interval=["+ node.left.startInterval+"-"+node.left.endInterval+"] and data:"+ node.left.data+"+ ->";

        }
        else
            str = str +"No left child";

        // for current node

        str = str + "Interval=["+node.startInterval+"-"+node.endInterval+"] and data:"+node.data+"+->";

        if(node.right!=null)
            str = str + "Interval=["+ node.right.startInterval+"-"+node.right.endInterval+"] and data:"+ node.right.data+"+ ->";
        else
            str = str +"No right child";
        System.out.println(str+"\n");

        if(node.left!=null)
            display(node.left);
        if(node.right!=null)
            display(node.right);
    }


}
