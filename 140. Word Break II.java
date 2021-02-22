class Solution {
  // time O(2^n) n is the length of s
  // space O(2^n)
  public List<String> wordBreak(String s, List<String> wordDict) {
    Map<String, List<String>> map = new HashMap<>(); // string -> list of sentences
    return backtrack(s, wordDict, map);
  }
  
  private List<String> backtrack(String s, List<String> wordDict, Map<String, List<String>> map) {
    if (map.containsKey(s)) return map.get(s);
    
    List<String> res = new ArrayList<>();
    if (s.length() == 0) { // reach the end of the string
      res.add("");
      return res;
    }
    
    for (String word : wordDict) {
      if (s.startsWith(word)) {
        List<String> subList = backtrack(s.substring(word.length()), wordDict, map);
        for (String sub : subList) {
          res.add(word + (sub.isEmpty() ? "" : " ") + sub);
        }
      }
    }
    map.put(s, res);
    return res;
  }
}