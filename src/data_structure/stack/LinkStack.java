package data_structure.stack;

public class LinkStack {
    private Node top;

    //置空
    public void clear() {
        top = null;  //栈顶为空，其余元素引用丢失
    }

    public boolean isEmpty() {
        return (top == null);
    }

    //求链栈的长度
    public int length() {
        Node node = top;
        int count = 0;
        while (node != null) {
            ++count;
            node = node.next;
        }
        return count;
    }

    //读取栈顶元素——注意不是pop取出
    public Object peek() {
        if (!isEmpty()) {
            return top.data;
        } else {
            return null;   //注意为空的时候也需要返回
        }
    }

    //入栈
    public void push(Object x) {
        Node node = top;  //记录原来的栈顶结点
        Node newNode = new Node(x);
        newNode.next = node;  //建立指针
        top = newNode;  //栈顶结点更新为新的压入结点
    }

    //出栈
    public Object pop() {
        if (isEmpty()) {
            return null;
        } else {
            Node node = top;  //记录原来的栈顶结点
            Node newTop = top.next;
            top = newTop;
            return node.data;
        }
    }

    //遍历栈的所有元素、从栈顶到底
    public void display() {
        Node p = top; //辅助指针，用于更新
        while (p != null) {
            System.out.print(p.data.toString() + " ");
            p = p.next;
        }
    }

}

