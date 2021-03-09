class Solution {
  public int minMeetingRooms(int[][] intervals) {
    // time O(nlogn)
    // space O(n)
    Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]); // sort by start time
    
    // smallest end time at top
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]);
    
    for (int[] interval : intervals) {
      if (minHeap.isEmpty() || interval[0] < minHeap.peek()[1]) {
        minHeap.add(interval);
      } else {
        int[] top = minHeap.poll();
        top[1] = interval[1];
        minHeap.add(top);
      }
    }
    return minHeap.size();
  }
}