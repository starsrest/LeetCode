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
  // time O(n)
  // space O(n)
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // traverse the tree to find p and q
    // while traversing, store the current -> parent pair to map
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    parentMap.put(root, null);
    
    while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
      TreeNode curr = stack.pop();
      
      if (curr.left != null) {
        stack.push(curr.left);
        parentMap.put(curr.left, curr);
      }
      
      if (curr.right != null) {
        stack.push(curr.right);
        parentMap.put(curr.right, curr);
      }
    }
    
    // put the nodes from path p to root to a set
    // go up from q to root, once the path node is contained in the set
    // this node is the LCA
    Set<TreeNode> set = new HashSet<>();
    while (p != null) {
      set.add(p);
      p = parentMap.get(p);
    }
    
    while (q != null) {
      if (set.contains(q)) return q;
      q = parentMap.get(q);
    }
    
    return null;
  }
}