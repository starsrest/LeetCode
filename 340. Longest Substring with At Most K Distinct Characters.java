class Solution {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    // time O(n)
    // space O(k)
    int longest = 0, left = 0, right = 0;
    Map<Character, Integer> countMap = new HashMap<>();
    while (right < s.length()) {
      char rightChar = s.charAt(right);
      countMap.put(rightChar, countMap.getOrDefault(rightChar, 0) + 1);
      
      while (countMap.size() > k && left < s.length()) {
        char leftChar = s.charAt(left);
        int count = countMap.get(leftChar) - 1;
        if (count == 0) {
          countMap.remove(leftChar);
        } else {
          countMap.put(leftChar, count);
        }
        left++;
      }
      
      longest = Math.max(longest, right - left + 1);
      right++;
    }
    return longest;
  }
}