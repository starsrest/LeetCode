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
    TreeNode nodeP = null, nodeQ = null;
    
    // travese to find p and q
    // store parent pair to map
    Map<TreeNode, TreeNode> map = new HashMap<>(); // curr -> parent
    Stack<TreeNode> stack = new Stack<>(); // dfs for storing node and its parent pair
    
    stack.push(root);
    map.put(root, null);
    while (!stack.isEmpty() && (nodeP == null || nodeQ == null)) {
      TreeNode node = stack.pop();
      if (node.val == p.val) nodeP = node;
      if (node.val == q.val) nodeQ = node;
      
      if (node.left != null) {
        map.put(node.left, node);
        stack.push(node.left);
      }
      if (node.right != null) {
        map.put(node.right, node);
        stack.push(node.right);
      }
    }
    
    // check if p and q are in the tree
    if (nodeP == null || nodeQ == null) return null;
    
    // if they are all in the tree,
    // go up from p and store nodes on the path to a set
    // then go up from q, while going up, 
    // if the nodes on the path is in the set
    // then this node is the LCA
    Set<TreeNode> set = new HashSet<>();
    TreeNode n1 = nodeP;
    while (n1 != null) {
      set.add(n1);
      n1 = map.get(n1);
    }
    
    TreeNode n2 = nodeQ;
    while (n2 != null) {
      if (set.contains(n2)) {
        return n2;
      }
      n2 = map.get(n2);
    }
    return null;
  }
}