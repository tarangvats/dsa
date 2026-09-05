# dsa
Small collection of data-structure examples (Java).

Project layout (standard Maven)
--------------------------
- Source root: src/main/java — put Java packages and classes here.
  - Current entry point: src/main/java/BinaryTree.java (class BinaryTree in the default package).
- Tests (if added): src/test/java
- Build output: target/ (Maven) or any custom -d output for javac.
- Build tool: Maven (pom.xml targets Java 17)

Notes on organization:
- Prefer placing classes in a named package (e.g. com.example) and mirroring that under src/main/java (src/main/java/com/example/BinaryTree.java). This makes IDE and Maven behavior consistent.
- If you prefer a single quick file for exercises, src/main/java/BinaryTree.java is acceptable but not recommended for larger projects.

Quick start
-----------
Using Maven (if installed):

  mvn compile
  mvn exec:java

Or with javac/java directly:

  javac src/main/java/BinaryTree.java -d out
  java -cp out BinaryTree

Notes
-----
- The program is interactive: it reads integer values and y/n prompts from the console and prints a "pretty" tree view.
- Java 17 is the target in pom.xml.
- If your IDE doesn't show the class, ensure `src/main/java` is marked as a Sources Root.

Cheat sheet — Trees (quick review)
----------------------------------
BinaryTree (general)
- Definition: each node has up to two children (no ordering constraint).
- Node structure: value, left, right.
- Traversals: preorder (root,left,right), inorder (left,root,right), postorder (left,right,root), level-order (BFS using a queue).
- Use cases: expression trees, structural problems, generic tree algorithms.
- Implementation note: recursive traversals are simple; iterative versions use stack/queue. All ops visit O(n) nodes.

Binary Search Tree (BST)
- Definition: left subtree values < node < right subtree values (conventionally).
- Operations: search/insert/delete — average O(log n), worst O(n) for skewed trees.
- Deletion: handle three cases — leaf, one child (replace), two children (replace with inorder successor or predecessor then remove it).
- Implementation tips: keep methods recursive or iterative; be careful to preserve ordering invariant when removing nodes.

AVL Tree (self-balancing BST)
- Definition: BST with a balance factor (height(left)-height(right)) maintained in {-1,0,1} for every node.
- Guarantees O(log n) for search/insert/delete by rebalancing with rotations.
- Rotations: single (left or right) and double (left-right, right-left) rotations to restore balance.
- Implementation hints: store node height, update after modifications, detect first unbalanced ancestor and apply appropriate rotation.

Study tips
----------
- Implement insert/search/delete and all traversals for BinaryTree/BST first.
- For AVL, add height bookkeeping and implement rotations; test on small sequences that trigger each rotation.
- Visualize with small examples and write unit tests for edge cases (empty tree, single node, skewed insertions).
