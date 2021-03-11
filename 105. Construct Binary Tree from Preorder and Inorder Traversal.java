/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  int[] preorder;
  int[] inorder;
  int n;
  Map<Integer, Integer> map; // inoder value -> index
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    // time O(n)
    // space O(n)
    this.preorder = preorder;
    this.inorder = inorder;
    this.n = preorder.length;
    map = new HashMap<>();
    for (int i = 0; i < n; i++) map.put(inorder[i], i);
    return build(0, 0, n - 1); 
  }
  
  private TreeNode build(int currPre, int start, int end) {
    // base case
    if (currPre >= n || start > end) return null;
    
    TreeNode root = new TreeNode(preorder[currPre]);
    int currIn = map.get(preorder[currPre]);
    int rightLen = currIn - start;
    root.left = build(currPre + 1, start, currIn - 1);
    root.right = build(currPre + rightLen + 1, currIn + 1, end);
    return root;
  }
}