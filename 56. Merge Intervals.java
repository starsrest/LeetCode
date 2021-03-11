class Solution {
  // time O(nlogn)
  // space O(n) worst space for res, O(logn) for sorting
  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]); // sort by start time
    LinkedList<int[]> res = new LinkedList<>();
    for (int[] interval : intervals) {
      if (res.isEmpty() || res.getLast()[1] < interval[0]) {
        res.add(interval);
      } else {
        res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
      }
    }
    return res.toArray(new int[res.size()][]);
  }
}