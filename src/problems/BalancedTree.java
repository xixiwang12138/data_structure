package problems;

public class BalancedTree {

}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}
class Solution2 {
    public boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }

    public Info process(TreeNode root){
        if( root == null ){
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);
        if(left==null && right==null){
            return new Info(1,true);
        }
        else if(left==null && right!=null){
            boolean res = right.height <= 1;
            return new Info(right.height+1,res);
        }
        else if(left!=null && right==null){
            boolean res = left.height <= 1;
            return new Info(left.height+1,res);
        }
        else{
            if(!left.isBalanced || !right.isBalanced){
                return new Info(0,false);
            }
            boolean res = Math.abs(left.height-right.height)<=1;
            return new Info(Math.max(left.height, right.height),true);
        }

    }
}

class Info {
    public int height;
    public boolean isBalanced ;

    public Info (int height , boolean isBalanced) {
        this.height = height;
        this.isBalanced = isBalanced;
    }
}
