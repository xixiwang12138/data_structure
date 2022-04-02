package data_structure.tree;

public class Heap {  //堆种存储元素需要比较规则
    public Integer[] items;   //存储元素的数组
    private int N;     //储存元素的个数

    public Heap(int capacity) {
        this.items = new Integer[capacity];  //泛型不能直接实例化，强制转换
        this.N = 0;
    }

    //判断i处的索引是否小于j处的索引值
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    //交换i和j的元素
    public void exchange(int i, int j) {
        Integer temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    //沿着树的顺序在堆中插入一个元素
    public void insert(Integer t) {
        N++;  //初始化为0，插入一个元素元素个数增加1
        items[N] = t; //为什么索引为N?因为0位置是废弃的，不使用
        swim(N);
    }

    //上浮方法，使index位置的元素排入堆中的正确位置，使堆符合规则
    public void swim(int index) {
        while (index > 1) {
            if (!less(index, index / 2)) {
                exchange(index, index / 2);
            }
            index /= 2;
        }
    }

    //删除堆中最大元素
    public Integer delMax() {
        Integer max = items[1];
        //交换最后一个值和头结点
        exchange(1, N);
        //让最后一个结点置空
        items[N] = null;
        //元素减少1
        N--;
        //通过让现在的头结点下沉使堆重新符合规则
        sink(1);
        return max;
    }

    //下沉方法
    public void sink(int index) {
        while (2 * index + 1 <= N) {
            if (less(index, index * 2) || less(index, index * 2 + 1)) {
                int k = items[index * 2].compareTo(items[index * 2 + 1]) < 0
                        ? (index * 2 + 1) : (index * 2);
                exchange(k, index);
                index = k;
            } else {
                return;
            }
        }
    }

    public void display() {
        int count = 1;
        int index = 1;
        while (index <= N) {
            for (int i = 0; i < count; i++) {
                System.out.print(items[index++] + " ");
            }
            count = count * 2;
            System.out.println();
        }
    }


}
