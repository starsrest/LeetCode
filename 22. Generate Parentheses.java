class Solution {
  // time O(k) k is the number of combinations 
  // space O(k) k for storing results, n is for call stack
  // k > n
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    StringBuilder comb = new StringBuilder();
    backtrack(res, comb, 0, 0, n);
    return res;
  }
  
  private void backtrack(List<String> res, StringBuilder comb, int left, int right, int num) {
    // wrong way, go back
    if (left > num || right > num || left < right) return;
    
    // base case
    if (right == num && left == num) {
      res.add(comb.toString());
      return;
    }
    
    // general case
    // go left
    comb.append("(");
    backtrack(res, comb, left + 1, right, num);
    comb.setLength(comb.length() - 1);
    
    // go right
    comb.append(")");
    backtrack(res, comb, left, right + 1, num);
    comb.setLength(comb.length() - 1);
  }
}