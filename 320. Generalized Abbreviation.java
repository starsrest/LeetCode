class Solution {
  public List<String> generateAbbreviations(String word) {
    // time O(n * (2 ^ n)) n is for converting stringbuider to string
    // space O(n) n is the length of word
    List<String> res = new ArrayList<>();
    
    // edge case
    if (word == null || word.length() == 0) return res;
    
    backtrack(res, new StringBuilder(), word, 0, 0);
    return res;
  }
  
  private void backtrack(List<String> res, StringBuilder sb, String word, int pos, int count) {
    int len = sb.length();
    
    // base case
    if (pos == word.length()) {
      if (count != 0) sb.append(count);
      res.add(sb.toString());
      sb.setLength(len);
      return;
    }
    
    // general case
    // char in pos is added into abbreviation
    backtrack(res, sb, word, pos + 1, count + 1);
    
    // char in pos is not added into abbreviation
    if (count != 0) sb.append(count); // add count to sb
    backtrack(res, sb.append(word.charAt(pos)), word, pos + 1, 0); // reset count to 0
    
    sb.setLength(len); // reset sb to previous status
  }
}