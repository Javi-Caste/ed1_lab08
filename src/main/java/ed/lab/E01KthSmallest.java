package ed.lab;

public class E01KthSmallest {
   
    int count = 0;
    TreeNode <Integer>result = null;

    public int kthSmallest(TreeNode<Integer> root, int k) {
        helper(root, k);
        return result.value;
    }

    private void helper(TreeNode<Integer> node, int k) {
        if (node == null) return;

        helper(node.left, k);       
        count++;                    
        if (count == k) {
            result = node;
            return;
        }
        helper(node.right, k);      
    } 
}