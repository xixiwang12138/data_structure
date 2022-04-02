package data_structure.queue;

import data_structure.lineList.ListNode;

public class LinkQueue {
    private ListNode front;
    private ListNode rear;

    //初始化，构造方法
    public LinkQueue(){
        front = rear = null;
    }

    //队列判空
    public boolean isEmpty(){
        return front == null;
    }

    //求队列长度
    public int length(){
        ListNode p = front;
        int length = 0;
        while(p.next != null){
            p= p.next;
            length++;
        }
        return length;
    }

    //查看队首元素
    public Object peek(){
        if(isEmpty()){   //判断是否为空
            return front.data;
        }else {
            return null;
        }
    }

    //出队
    public Object poll(){
        if(front!=null){
            ListNode p = front;  //p指向队首结点
            front = p.next;  //front指向下一节点
            //当只有一个有效元素时：front会指向null，但是rear不会清空，仍然指向原来的元素，需要将最后的结点删除
            if(p==rear){
                rear = null;
            }

            return p.data;  //返回队首的数据
        }else {
            return null;
        }
    }

    //入队
    public void offer(Object elem){
        ListNode p = new ListNode(elem); //创建包含elem的元素的结点，此时与原来的链表没有关系
        //如果队列为空
        if (isEmpty()){
            front = rear = p;
        }else {
            rear.next = p ; //使新创建的结点指向原来的链表的后面(建立逻辑联系)
            rear = p ;      //改变队尾的位置为新创建长度结点
        }
    }
}
