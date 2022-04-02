package data_structure.tree;

import data_structure.queue.LinkQueue;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    Node head;

    //通过一个数组构造一个赫夫曼树

    /**
     * 1. 对数组排序,找出最小两个值
     * 2. 最小两个值的和虚构一个新的结点
     * 3. 让新的值参与排序，重复2
     * 4. 直到最后两个结点合并
     * @param nums
     */
    public HuffmanTree(int[] nums){
        //元素个数不定，采用ArrayList作为容器
        ArrayList<Node> nodes = new ArrayList<>();
        for(int k : nums){
            nodes.add(new Node(k));
        }
        while(nodes.size()>=2) {
            Collections.sort(nodes);
            //取出第一第二个元素
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            Node parent = new Node(left.val + right.val);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        head = nodes.get(0);
    }

    //层序遍历
    public void levelTraverse() {
        LinkQueue linkQueue = new LinkQueue();
        if (head != null) {
            linkQueue.offer(head);
            while (!linkQueue.isEmpty()) {
                Node out = (Node) linkQueue.poll();
                System.out.println(out.val);
                if (out.left != null) {
                    linkQueue.offer(out.left);
                }
                if (out.right != null) {
                    linkQueue.offer(out.right);
                }
            }
        }
    }


}
class Node implements Comparable<Node>{
    public int val;
    public Node right;
    public Node left;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public int compareTo(Node o) {
        return this.val-o.val;
    }
}
