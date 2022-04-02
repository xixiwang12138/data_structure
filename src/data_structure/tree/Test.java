package data_structure.tree;

public class Test {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.head = new BinaryTreeNode(1);
        tree.head.left = new BinaryTreeNode(2);
        tree.head.right = new BinaryTreeNode(3);
        tree.head.left.left = new BinaryTreeNode(4);
        tree.head.left.right = new BinaryTreeNode(5);
        tree.head.right.left = new BinaryTreeNode(6);
        tree.head.right.right= new BinaryTreeNode(7);
        tree.head.right.right.left= new BinaryTreeNode(8);
        tree.head.right.right.right= new BinaryTreeNode(8);
        tree.head.right.right.right.right= new BinaryTreeNode(8);
        tree.head.right.right.right.right.right= new BinaryTreeNode(8);
        tree.head.right.right.right.right.right.left= new BinaryTreeNode(8);
//
//
//
////        System.out.println(BinaryTree.searchNode(tree.head,3).right.value);
////        System.out.println(BinaryTree.countLeaf(tree.head));
//        System.out.println(BinaryTree.getDepth(tree.head));
        int[] huff = {1};
//        int[] inOrder = {4,2,5,1,6,3,7};
//        BinaryTree binaryTree = new BinaryTree(preOrder,inOrder);
//        binaryTree.levelTraverse();
//        System.out.println(BinaryTree.isBalanced(tree.head));

        HuffmanTree huffmanTree = new HuffmanTree(huff);
        System.out.println(huffmanTree.head.val);
        huffmanTree.levelTraverse();


    }

}


