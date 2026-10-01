public class ProductSegmentTree {

    public static void main(String[] args){
        System.out.println("hello world");
    }
    private static class Node {
        int product;
        int[] prefixCount;

        Node(int k) {
            prefixCount = new int[k];
        }
    }

    private Node[] tree;
    private int[] nums;
    private int k;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.k = k;

        int n = nums.length;
        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int[] result = new int[queries.length];

        for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
            int index = queries[queryIndex][0];
            int value = queries[queryIndex][1];
            int start = queries[queryIndex][2];
            int remainder = queries[queryIndex][3];

            nums[index] = value;
            update(1, 0, n - 1, index);

            Node suffix = query(1, 0, n - 1, start, n - 1);
            result[queryIndex] = suffix.prefixCount[remainder];
        }

        return result;
    }

    private void build(int node, int left, int right) {
        if (left == right) {
            tree[node] = createLeaf(nums[left]);
            return;
        }

        int middle = left + (right - left) / 2;
        build(node * 2, left, middle);
        build(node * 2 + 1, middle + 1, right);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int left, int right, int index) {
        if (left == right) {
            tree[node] = createLeaf(nums[index]);
            return;
        }

        int middle = left + (right - left) / 2;
        if (index <= middle) {
            update(node * 2, left, middle, index);
        } else {
            update(node * 2 + 1, middle + 1, right, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        int middle = left + (right - left) / 2;

        if (queryRight <= middle) {
            return query(node * 2, left, middle, queryLeft, queryRight);
        }
        if (queryLeft > middle) {
            return query(node * 2 + 1, middle + 1, right, queryLeft, queryRight);
        }

        Node leftResult = query(node * 2, left, middle, queryLeft, queryRight);
        Node rightResult = query(node * 2 + 1, middle + 1, right, queryLeft, queryRight);
        return merge(leftResult, rightResult);
    }

    private Node createLeaf(int value) {
        Node leaf = new Node(k);
        leaf.product = value % k;
        leaf.prefixCount[leaf.product] = 1;
        return leaf;
    }

    private Node merge(Node left, Node right) {
        Node parent = new Node(k);
        parent.product = (int) ((long) left.product * right.product % k);

        for (int remainder = 0; remainder < k; remainder++) {
            parent.prefixCount[remainder] += left.prefixCount[remainder];

            int combinedRemainder = (int) ((long) left.product * remainder % k);
            parent.prefixCount[combinedRemainder] += right.prefixCount[remainder];
        }

        return parent;
    }

}
