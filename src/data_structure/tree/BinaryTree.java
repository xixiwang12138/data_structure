package data_structure.tree;

import data_structure.queue.LinkQueue;
import data_structure.stack.LinkStack;

import java.util.HashMap;
import java.util.HashSet;

public class BinaryTree {
    BinaryTreeNode head;

    public BinaryTree() {

    }

    public BinaryTree(int[] preOrder, int[] inOrder) {
        this(preOrder, inOrder, 0, 0, preOrder.length);
    }

    /**
     * 二叉树的建立，已知先根、中根遍历序列
     *
     * @param preOrder 先根遍历序列
     * @param inOrder  中根遍历序列
     * @param preIndex 开始位置
     * @param inIndex  开始位置
     * @param count    数的结点数
     */
    public BinaryTree(int[] preOrder, int[] inOrder, int preIndex, int inIndex, int count) {
        if (count > 0) {
            //构造根结点
            head = new BinaryTreeNode(preOrder[preIndex]);
            //index为根结点在中序遍历序列中的位置
            int index = 0;
            for (int i = 0; i < count; i++) {
                if (inOrder[inIndex + i] == head.value) {
                    index = i;
                }
            }
            //当前的根结点的左孩子指向一个新的子树的头结点，即建立联系了
            head.left = new BinaryTree(preOrder, inOrder, preIndex + 1, inIndex, index).head;
            head.right = new BinaryTree(preOrder, inOrder, preIndex + index + 1, inIndex + index + 1, count - index - 1).head;
            System.out.println("preIndex=" + inIndex);
        }
    }

    /**
     * 已知先根遍历序列和空结点的位置,注意未写对
     *
     * @param preOrder 先序遍历的序列,序列中的空结点用0表示
     */
    public BinaryTree(int[] preOrder, int index) {
        if (index < preOrder.length) {
            int ele = preOrder[index++];  //每执行一次（即每调用一次函数就++自增）
            if (ele != 0) {   //说明当前结点不是空结点
                head = new BinaryTreeNode(ele);  //头
                head.left = new BinaryTree(preOrder, index).head;  //左
                head.right = new BinaryTree(preOrder, index).head;  //右
            } else {
                head = null;   //基线条件是ele==0即结点为空，不再递归，将该子树的头结点置空，
                // 空结点的上一级递归函数中，左右(new BinaryTree(preOrder).head)都会变成null
            }
        }
    }


    public void f(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        //1
        f(head.left);
        //2
        f(head.right);
        //3
    }
    //递归思维：有两种对象——结点（输出值） 左右结点（继续左右递归遍历）
    //前序遍历

    public void pre(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    //中序遍历
    public void in(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    //后序遍历
    public void pos(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    //非递归方式遍历
    public void preTraverse() {
        BinaryTreeNode node = head;
        if (node != null) {   //如果树不为空，判断头结点是否为空
            LinkStack stack = new LinkStack();
            stack.push(node);   //不为空，向栈中先压入头结点

            while (!stack.isEmpty()) {
                node = (BinaryTreeNode) stack.pop(); //栈中取出栈顶结点
                System.out.println(node.value);  //先输出结点的值
                while (node != null) {
                    if (node.left != null) {
                        System.out.println(node.left.value);
                    }
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                    node = node.left;
                }
            }

        }
    }

    /**
     * 思路：利用栈这个数据结构
     * 先将树的根结点压入栈
     * 利用while循环，结束条件是栈为空
     * 1. 先序遍历：先从栈中将栈顶结点取出进行处理
     * 2. 再判断取出的结点的右子树是否为空，加入栈
     * 3. 再判断取出的结点的左子树是否为空，加入栈
     * 如此循环1、2、3
     */
    public void preTraverse2() {
        if (head != null) {
            LinkStack linkStack = new LinkStack();
            linkStack.push(this.head);
            while (!linkStack.isEmpty()) {
                //取出随即打印
                BinaryTreeNode pop = (BinaryTreeNode) linkStack.pop();
                System.out.println(head.value);  //输出结点的值

                if (pop.right != null) {
                    linkStack.push(pop.right);
                }
                if (pop.left != null) {
                    linkStack.push(pop.left);
                }
            }
        }
    }

    /**
     * 非递归方式的中序遍历的实现
     * 中序：先处理左子树，在处理结点的值，最后处理右子树h
     */
    public void inTraverse2() {
        if (head != null) {
            LinkStack linkStack = new LinkStack();
            while (!linkStack.isEmpty() || head != null) {
                if (head != null) {
                    linkStack.push(head);
                    head = head.left;
                } else {
                    head = (BinaryTreeNode) linkStack.pop();
                    System.out.println(head.value);
                    head = head.right;
                }
            }
        }
    }

    /**
     * 非递归方式的后序遍历的实现
     * 顺序是左、右、中
     * 由于中在前较为简单（类似于前序遍历），用中、右、左的顺序进行遍历存入helpStack中
     * 然后再逆序即可
     */
    public void posTraverse() {
        if (head != null) {
            LinkStack linkStack = new LinkStack();
            LinkStack helpStack = new LinkStack();
            linkStack.push(head);
            while (!linkStack.isEmpty()) {
                //取出随即打印
                BinaryTreeNode pop = (BinaryTreeNode) linkStack.pop();
                helpStack.push(pop.value);

                if (pop.left != null) {
                    linkStack.push(pop.left);
                }
                if (pop.right != null) {
                    linkStack.push(pop.right);
                }
            }
            helpStack.display();
        }
    }

    /**
     * 按层遍历
     */
    public void levelTraverse() {
        LinkQueue linkQueue = new LinkQueue();
        if (head != null) {
            linkQueue.offer(head);
            while (!linkQueue.isEmpty()) {
                BinaryTreeNode out = (BinaryTreeNode) linkQueue.poll();
                System.out.println(out.value);
                if (out.left != null) {
                    linkQueue.offer(out.left);
                }
                if (out.right != null) {
                    linkQueue.offer(out.right);
                }
            }
        }
    }

    //结点查找算法，错误的
    public static BinaryTreeNode searchNodeWrong(BinaryTreeNode head, int x) {
        if (head != null) {
            if (head.value == x) {
                return head;
            } else {
                if (head.left != null) {
                    return searchNodeWrong(head.left, x);
                }
                if (head.right != null) {
                    return searchNodeWrong(head.right, x);
                }
            }

        }
        return null;
    }

    /**
     * 搜索算法——针对一个元素只出现一次，元素具有唯一性
     *
     * @param head 要搜索的树的根结点
     * @param x    匹配的元素
     * @return 结点值
     */
    public static BinaryTreeNode searchNode(BinaryTreeNode head, int x) {
        if (head != null) {
            if (head.value == x) {
                return head;
            } else {
                BinaryTreeNode result = searchNode(head.left, x);
                return result != null ? result : searchNode(head.right, x);
            }
        }
        return null;
    }

    /**
     * 统计结点的个数
     */
    public static int countNode(BinaryTreeNode head) {
        int countLeft = 0;
        int countRight = 0;

        if (head != null) {
            if (head.left != null) {
                countLeft = countNode(head.left);
            }
            if (head.right != null) {
                countRight = countNode(head.right);
            }
        }
        return countLeft + countRight + 1;

//        //为节省空间
//        int count = 0;
//        if (head != null) {
//            ++count; //根结点+1
//            if (head.left != null) {
//                count += countNode(head.left);
//            }
//            if (head.right != null) {
//                count += countNode(head.right);
//            }
//        }
//        return count;

    }

    /**
     * 统计叶子结点数
     */
    public static int countLeaf(BinaryTreeNode head) {
        int count = 0;
        if (head != null) {
            if (head.left == null && head.right == null) {
                ++count;
            }
            if (head.left != null) {
                count += countLeaf(head.left);
            }
            if (head.right != null) {
                count += countLeaf(head.right);
            }
        }
        return count;
    }

    public static int getDepth(BinaryTreeNode head) {
        int depthLeft = 0;
        int depthRight = 0;
        if (head != null) {
            if (head.left != null) {
                depthLeft = getDepth(head.left);
            }
            if (head.right != null) {
                depthRight = getDepth(head.right);
            }
            return Math.max(depthLeft, depthRight) + 1;
        } else {
            return 0;
        }
    }

    public static int getDepth1(BinaryTreeNode head) {
        if (head == null) {
            return 0;
        } else if (head.right == null && head.left == null) {
            return 1;
        } else {
            return 1 + Math.max(getDepth1(head.right), getDepth1(head.left));
        }
    }

    /**
     * 基于递归的思路：
     * 1. 如果为空树认为是BST
     * 2. 向左递归，得到左树是否为BST，如果左树都不为BST，可以直接返回false
     * 3. 判断左子树的根结点结点值是否小于当前的结点的结点值，如果不为，返回false
     * 4. 如果经过前面的程序还没有返回false，可以直接返回右子树判断的结果
     */
    public static int preValue = Integer.MIN_VALUE;

    public static boolean isBST(BinaryTreeNode head) {
        if (head == null) {
            return true;
        }
        boolean isLeftBST = isBST(head.left);
        if (isLeftBST == false) {
            return false;
        }
        //preValue是读到左树的最后一个值
        if (head.value <= preValue) {
            return false;
        }
        return isBST(head.right);
    }

    /**
     * 基于非递归的中序遍历的判断BST实现
     *
     * @param head
     * @return
     */
    public static boolean isBST2(BinaryTreeNode head) {
        if (head != null) {
            int preValue = Integer.MIN_VALUE;
            LinkStack linkStack = new LinkStack();
            while (!linkStack.isEmpty() || head != null) {
                if (head != null) {
                    linkStack.push(head);
                    head = head.left;
                } else {
                    head = (BinaryTreeNode) linkStack.pop();

                    if (head.value <= preValue) {
                        return false;
                    } else {
                        preValue = head.value;
                    }
                    head = head.right;
                }
            }

        }
        return true;
    }

    /**
     * 判断是否为完全二叉树
     * 按层遍历，使用队列进行辅助
     */
    public static boolean isCBT(BinaryTreeNode head) {
        if (head == null) {
            return true;
        }

        LinkQueue queue = new LinkQueue();
        //是否遇见过左右两个孩子不双全的结点
        boolean childLoss = false;
        queue.offer(head);
        BinaryTreeNode l = head.left;
        BinaryTreeNode r = head.right;
        while (!queue.isEmpty()) {
            head = (BinaryTreeNode) queue.poll();
            l = head.left;
            r = head.right;
            //从队列中取出即进行判断处理
            if (
                    (l == null && r != null)  //条件2，只有右子树而没有左子树
                            ||
                            (childLoss && (l != null || r != null)) //遇见过第一个左右子树不全的结点并且后面的结点不为叶子节点
            ) {
                return false;
            }
            //队列，先进先出，按层遍历的套路
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }
            if (l == null || r == null) {   //判断每一个结点的左右孩子是否缺失
                childLoss = true;
            }
        }
        return true;  //遍历完成没有出现违反规则的情况，即是完全二叉树
    }

    /**
     * 判断一棵树是否为平衡二叉树
     *
     * @param head
     * @return
     */
    //主函数
    public static boolean isBalanced(BinaryTreeNode head) {
        return process(head).isBalanced;
    }

    //需要提供的信息的类
    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    //process函数，用于递归的辅助函数
    public static ReturnType process(BinaryTreeNode head) {
        //基线条件
        if (head == null) {
            return new ReturnType(true, 0);
//            return null;
        }

        //获取左右子树的信息
        ReturnType leftInfo = process(head.left);
        ReturnType rightInfo = process(head.right);

        //利用左右子树的信息,合起来，返回一个该结点(子树)的信息
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced &&
                Math.abs(leftInfo.height - rightInfo.height) < 2;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1; //当前树的高度，包括头结点
        return new ReturnType(isBalanced, height);
    }

    /**
     * 判断是否为二叉搜索树的递归套路
     */
    public static boolean isBSTRec(BinaryTreeNode head) {
        return processForBST(head).isBST;
    }

    public static class ReturnTypeForBST {
        public boolean isBST;
        public int max;
        public int min;

        public ReturnTypeForBST(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static ReturnTypeForBST processForBST(BinaryTreeNode head) {
        if (head == null) {
            return null;   //思考为什么是返回一个null
            //因为最大值最小值不好描述
            //所以在使用左右子树信息的数据之前必须判断是否为空
        }

        ReturnTypeForBST leftInfo = processForBST(head.left);
        ReturnTypeForBST rightInfo = processForBST(head.right);

        //min与max是想要得到整棵树的最大值和最小值
        int min = head.value;
        int max = head.value;
        if (leftInfo != null) {  //与基线条件对应
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {  //与基线条件对应
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }

        boolean isBST = true;
        if (leftInfo != null && (!leftInfo.isBST || leftInfo.max >= head.value)) {
            isBST = false;
        }
        if (rightInfo != null && (!rightInfo.isBST || rightInfo.min <= head.value)) {
            isBST = false;
        }
        return new ReturnTypeForBST(isBST, max, min);
    }

    /**
     * 查找两个结点的公共祖先结点
     *
     * @param head 树的根结点
     *             o1、o2是需要匹配的两个结点
     * @return 返回的是公共祖先结点
     */
    public static BinaryTreeNode lca(BinaryTreeNode head, BinaryTreeNode o1, BinaryTreeNode o2) {
        HashMap<BinaryTreeNode, BinaryTreeNode> fatherMap = new HashMap<>();
        //调用processForLca函数后，fatherMap其实只加入了第二层及之后的所有结点，差了根结点
        fatherMap.put(head, head); //根结点的父节点认为是其本身
        processForLca(fatherMap, head);
        HashSet<BinaryTreeNode> fatherNodeSet = new HashSet<>();
        BinaryTreeNode curNode = o1;  //curNode已经为o1的父结点
        while (fatherMap.get(curNode) != head) {
            fatherNodeSet.add(curNode);
            curNode = fatherMap.get(curNode);
        }
        fatherNodeSet.add(head);
        //到目前为止，fatherNodeSet已经全部包含o1到根结点路径上的结点

        curNode = o2;    //从o2开始沿着祖先结点遍历，如果发现结点在o1的祖先序列中则返回
        while(fatherMap.get(curNode)!=head){
            if(fatherNodeSet.contains(curNode)){
                return curNode;
            }
            curNode = fatherMap.get(curNode);
        }
        return head; //还没有返回就是整棵树的头结点
    }

    //将左右的结点的父结点信息加入fatherMap
    public static void processForLca(HashMap<BinaryTreeNode, BinaryTreeNode> fatherMap, BinaryTreeNode head) {
        if (head != null) {   //base case ：当结点为空时直接返回
            return;
        }

        //将左右子树的根结点加入哈希表
        fatherMap.put(head.left, head);
        fatherMap.put(head.right, head);

        //对左右子树进行递归处理
        processForLca(fatherMap, head.left);
        processForLca(fatherMap, head.right);
    }



}

class BinaryTreeNode {
    int value;

    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
    }
}

//8：45