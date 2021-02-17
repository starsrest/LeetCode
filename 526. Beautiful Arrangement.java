class Solution {
  // backtracking
  // time O(k) k is the number of valid perms
  // space O(n)
  int count = 0;
  public int countArrangement(int n) {
    boolean[] visited = new boolean[n + 1];
    backtrack(visited, 1);
    return count;
  }
  
  private void backtrack(boolean[] visited, int index) {
    // base case
    if (index == visited.length) {
      count++;
      return;
    }
    
    // general case
    // All possible choices for current node
    for (int currNum = 1; currNum < visited.length; currNum++) {
      if (!visited[currNum] && (currNum % index == 0 || index % currNum == 0)) {
        visited[currNum] = true;
        backtrack(visited, index + 1);
        visited[currNum] = false;
      }
    }
  }
}