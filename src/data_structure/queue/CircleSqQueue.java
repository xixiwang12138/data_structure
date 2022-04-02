package data_structure.queue;

public class CircleSqQueue {
    private int front;  //front表示队列头(第一个元素)
    private int rear;   //rear表示队列尾(最后一个元素)后面的一个位置，不是队列尾
    private int maxSize;  //允许存放的的数据元素最多为maxSize-1个，一个留空(便于判断)
    private Object[] arr;

    public CircleSqQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new Object[maxSize];
        front = 0;
        rear = 0;

    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //有效个数
    public int length() {
        return (rear + maxSize - front) % maxSize;  //此公式可通用队列长度公式
    }

    public void showQueue() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空");
        }
        for (int i = front; i < this.length(); i = (i + 1) % maxSize) {
            System.out.println(arr[i]);
        }
    }

    //入列
    public void offer(Object o) throws Exception {
        if (isFull()) {
            throw new Exception("队列已满");
        }
        arr[rear] = o;
        rear = (rear + 1) % maxSize;
    }

    //出列
    public Object poll() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空");
        }
        Object value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }


}
