class Trie {
  class Node {
    Node[] children;
    boolean isLeaf;
    
    Node () {
      children = new Node[26];
      isLeaf = false;
    }
  }

  Node root;
  
  /** Initialize your data structure here. */
  public Trie() {
    root = new Node();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    insert(word, root);
  }
  
  private void insert(String word, Node node) {
    // base case
    if (word.length() == 0) {
      node.isLeaf = true;
      return;
    }
    
    char curr = word.charAt(0);
    int index = curr - 'a';
    if (node.children[index] == null) node.children[index] = new Node();
    insert(word.substring(1), node.children[index]);
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    return search(word, root);
  }
  
  private boolean search(String word, Node node) {
    // base case
    if (word.length() == 0) return node.isLeaf;
    
    char curr = word.charAt(0);
    int index = curr - 'a';
    if (node.children[index] == null) return false;
    return search(word.substring(1), node.children[index]);
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    return startsWith(prefix, root);
  }
  
  private boolean startsWith(String prefix, Node node) {
    // base case
    if (prefix.length() == 0) return true;
    
    char curr = prefix.charAt(0);
    int index = curr - 'a';
    if (node.children[index] == null) return false;
    return startsWith(prefix.substring(1), node.children[index]);
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */