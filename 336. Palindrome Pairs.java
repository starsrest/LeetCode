class Solution {
  class Node {
    Node[] children = new Node[26];
    int wordId = -1; // -1 represents there is no word ends in here
    List<Integer> prefixPalindromes = new ArrayList<>();
  }
  
  // time O(L)
  // space O(1)
  private boolean isSubstringPalindrome(String s, int i) {
    int start = i, end = s.length() - 1;
    while (start < end) {
      if (s.charAt(start) != s.charAt(end)) return false;
      start++;
      end--;
    }
    return true;
  }
  
  // time O(n * L ^ 2) n is the length of words. L is the maximum length of word
  // space O(n ^ 2 * L) storing trie
  private Node buildTrie(String[] words) {
    Node root = new Node();
    for (int id = 0; id < words.length; id++) {
      String word = words[id];
      String reversedWord = new StringBuilder(word).reverse().toString();
      
      // put reversed word into trie
      Node curr = root;
      for (int i = 0; i < reversedWord.length(); i++) {
        
        // check if the substring is a palindrome
        if (isSubstringPalindrome(reversedWord, i)) curr.prefixPalindromes.add(id);
        
        char c = reversedWord.charAt(i);
        if (curr.children[c - 'a'] == null) curr.children[c - 'a'] = new Node();
        curr = curr.children[c - 'a'];
      }
      curr.wordId = id;
    }
    return root;
  }
  
  // time O(n * L ^ 2)
  // space O(n ^ 2) worst case
  private List<List<Integer>> getPairs(Node trie, String[] words) {
    List<List<Integer>> res = new ArrayList<>();
    for (int id = 0; id < words.length; id++) {
      String word = words[id];
      Node curr = trie;
      
      for (int i = 0; i < word.length(); i++) {
        if (curr == null) break; // no match
        
        // word's length is longer
        if (curr.wordId != -1 && isSubstringPalindrome(word, i)) {
          res.add(Arrays.asList(id, curr.wordId));
        }
        
        char c = word.charAt(i);
        curr = curr.children[c - 'a'];
      }
      
      if (curr == null) continue; // no match
      
      // dictionary path's length is equal to word length
      if (curr.wordId != id && curr.wordId != -1) {
        res.add(Arrays.asList(id, curr.wordId));
      }
      
      // dictionary path's length is longer
      for (int otherId : curr.prefixPalindromes) {
        res.add(Arrays.asList(id, otherId));
      }
    }
    return res;
  }
  
  public List<List<Integer>> palindromePairs(String[] words) {
    // edge case
    if (words == null || words.length == 0) return new ArrayList<>();
    
    // build trie
    Node root = buildTrie(words);
    
    // get palindrome pairs
    return getPairs(root, words);
  }
}