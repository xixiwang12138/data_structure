package data_structure.lineList;

import java.util.Scanner;

public class DoubleLinkedList {
    public DoubleNode head;                     //创建单链表的头指针(单链表的头结点)
    public DoubleLinkedList(){
        head = new DoubleNode();                   //调用Node类的构造方法初始化头结点
    }


    //输出单链表中的所有结点
    public void display(){
        DoubleNode p = head.next;

        while(p!=null){
            System.out.println(p.data+"    ");
            p = p.next;
        }
        System.out.println();
    }
    /**
     * 向链表最后添加一个元素（结点）
     * @param doubleNode
     */
    public void add(DoubleNode doubleNode){
        //head结点始终不能动，需要辅助结点temp
        DoubleNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        //形成双向链表到最后
        temp.next = doubleNode;
        doubleNode.pre = temp;
    }

    /**
     * 修改元素
     * @param newDoubleNode
     */
    public void update(DoubleNode newDoubleNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
    }

    public static DoubleNode reverse(DoubleNode head){
        DoubleNode cur = head.next;
        DoubleNode next = null;
        DoubleNode reverseHead = new DoubleNode();
        while(cur != null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
//            cur.pre = reverseHead;
            cur = next;
        }
        return reverseHead;
    }

}

class DoubleNode{
    public DoubleNode next;
    public DoubleNode pre;

    public Object data;

    public DoubleNode(Object data) {
        this.data = data;
    }

    public DoubleNode() {
    }
}
