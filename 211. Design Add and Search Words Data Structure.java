class WordDictionary {
  // time O(26^m) m is the number of dots
  // space O(n) n is the length of the word. it's used by call stack
  class Node {
    Node[] children = new Node[26];
    boolean isLeaf = false;
  }

  Node root;
  
  /** Initialize your data structure here. */
  public WordDictionary() {
    root = new Node();
  }

  public void addWord(String word) {
    addWord(word, root);
  }
  
  private void addWord(String word, Node node) {
    if (word.length() == 0) {
      node.isLeaf = true;
      return;
    }
    
    char curr = word.charAt(0);
    int index = curr - 'a';
    if (node.children[index] == null) node.children[index] = new Node();
    addWord(word.substring(1), node.children[index]);
  }

  public boolean search(String word) {
    return search(word, root);
  }
  
  private boolean search(String word, Node node) {
    if (word.length() == 0) return node.isLeaf;
    
    char curr = word.charAt(0);
    if (curr == '.') {
      // check all letters
      for (int i = 0; i < 26; i++) {
        if (node.children[i] != null && search(word.substring(1), node.children[i])) {
          return true;
        }
      }
      return false;
    } else {
      int index = curr - 'a';
      if (node.children[index] == null) return false;
      return search(word.substring(1), node.children[index]);
    }
  }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */