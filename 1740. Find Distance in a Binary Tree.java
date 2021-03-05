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
  
  // time O(n) space O(n)
  public int findDistance(TreeNode root, int p, int q) {
    
    // find lca
    TreeNode lca = lca(root, p, q);
    
    // add distance from lca to p and q, return distance
    int distP = dist(lca, p);
    int distQ = dist(lca, q);
    return distP + distQ;
  }
  
  // time O(n) space O(n)
  private TreeNode lca(TreeNode node, int p, int q) {
    if (node == null) return null;
    if (node.val == p || node.val == q) return node;
    
    TreeNode left = lca(node.left, p, q);
    TreeNode right = lca(node.right, p, q);
    
    if (left != null && right != null) {
      return node;
    } else if (left != null) {
      return left;
    } else if (right != null) {
      return right;
    }
    return null;
  }
  
  // time O(n) space O(n)
  private int dist(TreeNode node, int target) {
    if (node == null) return -1;
    if (node.val == target) return 0;
    
    int left = dist(node.left, target);
    if (left != -1) return left + 1;
    
    int right = dist(node.right, target);
    if (right != -1) return right + 1;
    return -1;
  }
}