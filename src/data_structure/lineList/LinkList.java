package data_structure.lineList;

import java.util.Scanner;

public  class LinkList {
    public ListNode head;                     //创建单链表的头指针(单链表的头结点)
    public LinkList(){
        head = new ListNode();                   //调用Node类的构造方法初始化头结点
    }

    public LinkList(int n,boolean Order) throws Exception{           //有参构造
        this();
        if (Order){                 //true则用尾插法
            create1(n);
        }else {                     //false用头插法
            create2(n);
        }
    }

    //尾插法顺序建立单链表，n为单链表的结点个数
    public void create1(int n ) throws Exception{

    }


    //头插法逆位序建立单链表，n为单链表的结点个数
    public void create2(int n ) throws Exception{

    }

    //将已经存在的带头结点单链表置空（实例化链表类的时候构造方法先行便创建了头结点对象）
    public void clear(){
        head.data = null;
        head.next = null;
    }

    //判断带头结点的单链表是否为空
    public boolean isEmpty(){
        return head.next == null;              //如果头结点的指针为空，就说明该单链表为空
    }

    //求带头结点的单链表的长度
    public int length(){
        ListNode n1 = head.next;                //n1此时为首结点
        int length = 0 ;
        while (n1!=null){                   //当结点类的引用为空的时候说明链表已经结束
            n1 = n1.next;
            length++;
        }
        return length;
    }


    //输出单链表中的所有结点
    public void display(){
        ListNode p = head.next;

        while(p!=null){
            System.out.println(p.data+"    ");
            p = p.next;
        }
        System.out.println();
    }


    //查找，查找位序号为i的数据元素值
    public Object get(int i) throws Exception{
        ListNode p = head.next;          //初始化，p为首结点对象，j为计数器
        int j = 0;

        while (p != null && j<i){   //两种情况下循环会停止：第一种是i的值小于链表长度，在j=i时，循环终止
                                                      //第二种是i的值大于链表长度，此时p会变成p=null
            p=p.next;
            j++;
        }

        if (p==null || j>i ){      //p==null这个条件是在查找结点过程中还没有到i次，链表就完了
                                   //j>i 这个条件是规避当i<0时会出现的bug(如果没有这个条件，i<0时会返回第一个结点的值)，也可以将此判断放在开头
            throw new Exception("第"+i+"个元素不存在");
        }
        return p.data;
    }

    //查找，在带头结点的单链表上查找与给定值x相等的结点，存在则返回位置，不存在即返回-1
    public int indexOf(Object x){
        ListNode p = head.next;         //p为首结点
        int j = 0;

        while (!p.data.equals(x) && p != null){    //两个条件：p==null时，是指到了链表结尾也没有匹配的值
                                                  //另外一个条件是p.data.equals(x),匹配了就结束
            p=p.next;
            j++;
        }
        if (p == null){
            return -1;
        }else{
            return j;
        }
    }


    //需要好好考虑怎么找到序号为i-1的结点
    //插入，带头结点的的单链表的插入
    public void insertWith(int i, Object x) throws Exception{
        ListNode p = head.next;
        int j = -1;
        while ( p!=null && j<i-1){             //将p操作为序号为i-1的结点，操作了i-2+1+1=i次
            p=p.next;
            j++;
        }
        if (j>i-1 || p==null){                 //两个条件：j>i-1说明并未执行while循环，该条件是限制插入位置i是大于0的
                                               //p==null是以及找完整个链表还没有i,也就是说i>链表长度
            throw new Exception("插入位置不合法");
        }

        ListNode s = new ListNode(x);                  //带一个参数的构造
        s.next = p.next;                       //修改链表，使新结点插入新链表之中
        p.next = s;
    }


    //插入，不带头结点的的单链表的插入
    public void insertWithout(int i ,Object x) throws Exception{
        ListNode p = head.next;
        int j = 0;
        while ( p!=null && j<i-1){             //将p操作为第i-1个结点(使用i=-1/0/1去测试)
            p=p.next;
            j++;
        }
        if (j>i || p==null){                 //两个条件：j>i-1是限制插入位置i是大于0的
            //p==null是以及找完整个链表还没有i,也就是说i>链表长度
            throw new Exception("插入位置不合法");
        }
        ListNode s = new ListNode(x);
        if (i==0){
            s.next = head;
            head = s;
        }else {
            s.next= p.next;
            p.next = s;
        }

    }


    //删除，已知位置
     public void remove(int i ) throws Exception{
        ListNode p = head;
        int j = 0 ;
        while (p!=null && j<i){
            p=p.next;
            j++;
        }

        if (p==null || j>i ){
            throw new Exception("删除位置不合法");
        }

        p.next = p.next.next;
     }
     //删除，与给定元素进行匹配即删除
     public void remove2(Object x ) throws Exception{
         ListNode p = head;
         while (p.next!=null && !p.data.equals(x)){
             p=p.next;
         }

         if (p.next==null){
             throw new Exception("待删结点不存在");
         }

         p.next = p.next.next;
     }


     //头插法建立,读入数据与实际存储数据顺序相反
    public void creat2(int n) throws Exception{
        Scanner sc = new Scanner(System.in);
        for (int j = 0; j<n ;j++ ){
//            insert(0,sc.next());
        }
    }

    //尾插法建立,读入数据与实际存储数据顺序相同
    public void creat1(int n) throws Exception{
        Scanner sc = new Scanner(System.in);
        for (int j = 0; j<n ;j++ ){
//            insert(length(),sc.next());
        }
    }

    //删除重复值
    public void removeRepeatElem(LinkList L) throws Exception{
        ListNode p = head.next;
        ListNode q;


        while (p!=null){
            int order = L.indexOf(p.data);

            q = p.next;
            while (q!=null){
                if (q.data.equals(p.data)){
                    L.remove(order +1);  //删除一个元素后order不用自增
                }else {
                    order++;
                }
                q=q.next;
            }
            p=p.next;
        }

    }







}
