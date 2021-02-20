class Solution {
  public List<List<String>> partition(String s) {
    // time O(n * 2^n), space O(n)
    List<List<String>> lists = new ArrayList<>();
    if (s == null || s.length() == 0) return lists; // edge case
    partition(lists, new ArrayList<String>(), s);
    return lists;
  }
  
  private void partition(List<List<String>> lists, List<String> list, String s) {
    // base case
    // reached the end of the string means the previous substrings are valid
    if (s.length() == 0) { 
      lists.add(new ArrayList<>(list));
      return;
    }
    
    // general case
    for (int i = 0; i < s.length(); i++) {
      String sub = s.substring(0, i + 1);
      if (isPalindrome(sub)) {
        list.add(sub);
        partition(lists, list, s.substring(i + 1)); // check the other half
        list.remove(list.size() - 1);
      }
    }
  }
  
  private boolean isPalindrome(String s) {
    int start = 0, end = s.length() - 1;
    while (start < end) {
      if (s.charAt(start++) != s.charAt(end--)) return false;
    }
    return true;
  }
}