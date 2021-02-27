class MagicDictionary {
  class Node {
    Node[] children = new Node[26];
    boolean isLeaf = false;
  }

  Node root;
  
  /** Initialize your data structure here. */
  public MagicDictionary() {
    root = new Node();
  }

  // time O(N * L) N is number of words, L is maximum of word
  // space O(26 ^ L) worst case
  public void buildDict(String[] dictionary) {
    for (String word : dictionary) {
      Node node = root;
      for (char c : word.toCharArray()) {
        if (node.children[c - 'a'] == null) node.children[c - 'a'] = new Node();
        node = node.children[c - 'a'];
      }
      node.isLeaf = true;
    }
  }

  public boolean search(String searchWord) {
    return search(searchWord, root, 0, 0);
  }
  
  // time O(26 ^ L)
  // space O(L) call stack
  private boolean search(String word, Node curr, int index, int count) {
    if (curr == null || count > 1) return false;
    
    if (index == word.length()) return curr.isLeaf && count == 1;
    
    for (int i = 0; i < 26; i++) { // backtrack  
      // if current char doesn't change, count stay the same. otherwise count + 1
      int newCount = word.charAt(index) - 'a' != i ? count + 1 : count;
      if (search(word, curr.children[i], index + 1, newCount)) return true; // found
    }
    return false; // not found
  }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */