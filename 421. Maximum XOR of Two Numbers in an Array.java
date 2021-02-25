class Solution {
  class Node { 
    Node[] children = new Node[2]; 
  }
  
  // time O(n) n is the number of nums. space O(2 ^ 32) worst case
  public int findMaximumXOR(int[] nums) {
    // corner case
    if (nums == null || nums.length == 0) return 0;
    
    // build the trie
    Node root = new Node();
    for (int num : nums) {
      Node curr = root;
      for (int i = 31; i >= 0; i--) {
        int currDigit = (num >> i) & 1;
        if (curr.children[currDigit] == null) curr.children[currDigit] = new Node();
        curr = curr.children[currDigit];
      }
    }
    
    // compute xor result for each num
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      int res = 0;
      Node curr = root;
      for (int i = 31; i >= 0; i--) {
        int currDigit = (num >> i) & 1;
        // currDigit ^ 1 can be replaced by currDigit == 0 ? 1 : 0
        if (curr.children[currDigit ^ 1] != null) { 
          res += (1 << i);
          curr = curr.children[currDigit ^ 1];
        } else {
          curr = curr.children[currDigit]; // skipping means adding 0
        }
      }
      max = Math.max(max, res);
    }
    return max;
  }
}