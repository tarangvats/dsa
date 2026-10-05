public class Trie {

    public static void main(String[] args){
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");

        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("ap"));
        System.out.println(trie.startsWith("ba"));
    }
    static class Node {
        Node[] children;
        boolean endOfWord;

        public Node() {
            this.children = new Node[26];
            this.endOfWord = false;
        }
    }
    private final Node root = new Node();

    public void insert(String word){
            Node current = root;
            for(int i = 0; i<word.length(); i++){
                int index = word.charAt(i) - 'a';
                if(current.children[index]==null){
                    current.children[index] = new Node();
                }
                current = current.children[index];
                if(i==word.length()-1){
                    current.endOfWord = true;
                }
            }
        }
    public boolean search(String word){
        Node current = this.root;
        for(int i = 0; i<word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(current.children[index]==null){
                return false;
            }
            current = current.children[index];
            if(i==word.length()-1 && !current.endOfWord){
                return false;
            }
        }
        return true;
    }
    public Node walk(String word){
        Node current = this.root;
        int n = word.length();
        for(int i = 0; i< n ; i++){
            int index = word.charAt(i) - 'a';
            if(current.children[index]==null){
                return null;
            }
            current = current.children[index];
        }

        return current;
    }

    public boolean startsWith(String prefix){
        Node last = walk(prefix);
        if(last==null)
            return false;
        return true;
    }




}
