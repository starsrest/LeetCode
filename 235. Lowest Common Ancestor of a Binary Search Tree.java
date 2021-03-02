/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // time O(n) n is number of nodes. Worst case, the tree is skewed
    // space O(n) worst case for call stack
    
    // case 1: root value is less than p's and q's, 
    // which means they are at left side of the root
    if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
    
    // case 2: root value is greater than p's and q's
    // which means they are at right side of the root
    if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
    
    // case 3: none of the above. p and q are at different side of root
    // or either one is the root
    // which means the current node is the LCA
    return root;
  }
}