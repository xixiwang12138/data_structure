package data_structure.lineList;
public class ListNode {
    public Object data;
    public ListNode next;                //Next是一个结点类的对象，所以结点类的对象又可以调用结点类中的内容

    //无参构造
    public ListNode() {
    }

    //一个参数的构造，传入结点包含的数据
    public ListNode(Object data) {
        this.data = data;
    }


}
