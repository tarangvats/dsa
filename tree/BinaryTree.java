import java.util.Scanner;

public class BinaryTree {
    private final Scanner scanner = new Scanner(System.in);
    private Node root;

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insertRoot();
        tree.prettyDisplay();
    }

    private void insertRoot() {
        System.out.print("Enter root value: ");
        int value = scanner.nextInt();
        root = new Node(value);
        populate(root);
    }

    private void populate(Node node) {
        if (node == null) return;

        System.out.print("Add left child to " + node.value + "? (y/n): ");
        String leftChoice = scanner.next();
        if (leftChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter left child value: ");
            int leftValue = scanner.nextInt();
            node.left = new Node(leftValue);
            populate(node.left);
        }

        System.out.print("Add right child to " + node.value + "? (y/n): ");
        String rightChoice = scanner.next();
        if (rightChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter right child value: ");
            int rightValue = scanner.nextInt();
            node.right = new Node(rightValue);
            populate(node.right);
        }
    }

    public void display() {
        display(root, "");
    }

    private void display(Node node, String indent) {
        if (node == null) return;
        System.out.println(indent + node.value);
        display(node.left, indent + "\t");
        display(node.right, indent + "\t");
    }

    public void prettyDisplay() {
        prettyDisplay(root, 0);
    }

    private void prettyDisplay(Node node, int level) {
        if (node == null) return;

        prettyDisplay(node.right, level + 1);

        if (level != 0) {
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.println("|------> " + node.value);
        } else {
            System.out.println(node.value);
        }

        prettyDisplay(node.left, level + 1);
    }
}