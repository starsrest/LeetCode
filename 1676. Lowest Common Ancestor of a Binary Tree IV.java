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
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
    Set<TreeNode> set = new HashSet<>();
    for (TreeNode node : nodes) {
      set.add(node);
    }
    return dfs(set, root);
  }
  
  private TreeNode dfs(Set<TreeNode> set, TreeNode node) {
    if (node == null) return null;
    if (set.contains(node)) return node;
    TreeNode left = dfs(set, node.left);
    TreeNode right = dfs(set, node.right);
    
    if (left != null && right != null) {
      return node;
    } else if (left == null) {
      return right;
    } else if (right == null) {
      return left;
    }
    return null;
  }
}