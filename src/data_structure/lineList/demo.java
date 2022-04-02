package data_structure.lineList;


import java.util.LinkedList;

/**
 * 题目中：一般为
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


public class demo {
    public static void main(String[] args) {
//        DoubleNode node1 = new DoubleNode(1);
//        DoubleNode node2 = new DoubleNode(2);
//        DoubleNode node3 = new DoubleNode(3);
//        DoubleNode node4 = new DoubleNode(4);
//        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(node1);
//        doubleLinkedList.add(node2);
//        doubleLinkedList.add(node3);
//        doubleLinkedList.add(node4);
////        DoubleLinkedList.reverse(doubleLinkedList.head);
//        doubleLinkedList.display();

        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));



    }

    public static boolean isValidSerialization(String preorder) {
        if(preorder == null || preorder.length()<1){
            return false;
        }
        String[] string = preorder.split(",");
        if(string[0].equals("#") && string.length==1){
            return true;
        }
        LinkedList<String> stack = new LinkedList<>();
        stack.addFirst(string[0]);
        //"9,3,4,#,#,1,#,#,2,#,6,#,#"
        for(int i = 1 ; i<string.length ; i++){
            stack.addFirst(string[i]);
            int size = stack.size();
            if(stack.get(size-1).equals("#") && stack.get(size-2).equals("#")){
                stack.removeFirst();
                stack.removeFirst();
                stack.removeFirst();
                stack.addFirst("#");
            }
        }
        String f = stack.removeFirst();
        return f.equals("#") && stack.isEmpty();
    }


    /**
     * 链表反转
     *
     * @param head
     * @return 反转后的头节点
     */
    //腾讯面试题：链表反转
    public static ListNode reverse(ListNode head) {
        if (head.next == null || head.next.next == null) {
            return null;
        }


        ListNode cur = head.next;   //将第一个结点的引用给cur
        ListNode next;      //用于指向当前节点cur的下一个节点
        ListNode reverseHead = new ListNode();  //反转后的头节点

        while (cur != null) {
            next = cur.next;  //暂时保存当前节点的下一个节点
            cur.next = reverseHead.next; //这时候cur与原来的链表断除关系，接入反转后链表的头链表
            reverseHead.next = cur; //将cur的指向reverse(新的)的下一节点(复制)
            cur = next;  //next的作用：将原来的链表保留下来，通过next可以访问
        }

        return reverseHead;
    }


    /**
     * 输出各节点的数据值
     *
     * @param head
     */
    public static void show(ListNode head) {
        ListNode listNode = head.next;
        while (listNode != null) {
            System.out.println(listNode.data);
            listNode = listNode.next;
        }
    }


    /**
     * leecode 19
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        if (head.next != null && head.next.next == null) {
            head.next = null; //置空
            return head;
        }

        /**
         * 求链表有效元素个数
         */

        int sz = 0;
        ListNode cur=head;
        //头结点必须保留！！
        while (cur.next != null) {
            sz++;
            cur = cur.next;
        }


        ListNode cur1=head;      //将 “数据的引用” 赋值给cur1,cur1可以继续对链表进行修改，要想访问全部链表必须保留head，不然会因为前面的元素无法访问到而丢失

        for (int i = 0; i < sz - n; i++) {
            cur1 = cur1.next;

        }
        System.out.println(cur1.next.data);
        System.out.println(cur1.next.next.data);

        cur1.next = cur1.next.next; //原来的对应位置元素由于没有引用变量指向它，成为垃圾被回收

        return head;

    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode cur= head;
        for (int i = 0; i < right; i++) {
            cur = cur.next;
        }
        //此时cur表示第right个结点


        ListNode cur1= head;
        for (int i = 0; i < left-1; i++) {
            cur1 = cur1.next;
        }
        //此时cur1表示第left-1个结点

        cur1.next = cur.next;


        ListNode cur2 = cur1;  //cur2用于存储当前
        ListNode cur3 = cur;        //cur3储存cur2及以后的元素
//        cur3.next = cur;       //连接
//

        for (int i = 0; i < (right - left+1 ); i++) {
            cur1.next=cur2;  //连接到左边


            cur2.next=cur3;
            cur2 = cur2.next;
        }


        return head;
    }
}
