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
  int max = Integer.MIN_VALUE;
  
  // time O(n) n is number of nodes
  // space O(h) h is height of the tree
  public int maxPathSum(TreeNode root) {
    goPath(root);
    return max;
  }
  
  private int goPath(TreeNode root) {
    // every node in the tree will do the path sum and compare to the max
    if (root == null) return 0;
    
    int leftSum = Math.max(goPath(root.left), 0);
    int rightSum = Math.max(goPath(root.right), 0); // only take non-nagative
    
    int newPathSum = leftSum + rightSum + root.val;
    
    max = Math.max(max, newPathSum); // update
    
    return root.val + Math.max(leftSum, rightSum); // choose path from left or right
  }
}