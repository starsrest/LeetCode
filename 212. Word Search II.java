class Solution {
  class Node {
    Node[] children = new Node[26];
    String word = null;
  }
  
  char[][] board;
  List<String> res;
  int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  int m, n;
  
  public List<String> findWords(char[][] board, String[] words) {
    this.board = board;
    res = new ArrayList<>();
    m = board.length;
    n = board[0].length;
    
    // use words to construct trie
    Node root = new Node();
    for (String word : words) {
      Node node = root; // for each word, start from root
      for (char ch : word.toCharArray()) {
        int index = ch - 'a';
        if (node.children[index] == null) node.children[index] = new Node();
        node = node.children[index]; // update node to its child node
      }
      node.word = word; // store the word at leaf
    }
    
    // backtracking
    for (int r = 0; r < m; r++) {
      for (int c = 0; c < n; c++) {
        backtrack(r, c, root);
      }
    }
    return res;
  }
  
  
  private void backtrack(int r, int c, Node parent) {
    if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] == '*' 
        || parent.children[board[r][c] - 'a'] == null) return;
    
    char ch = board[r][c];
    Node child = parent.children[ch - 'a'];
    
    if (child.word != null) {
      res.add(child.word);
      child.word = null; // avoid same word appear in res list
    }
    
    board[r][c] = '*';
    for (int[] dir : dirs) {
      backtrack(r + dir[0], c + dir[1], child);
    }
    board[r][c] = ch;
  }
}