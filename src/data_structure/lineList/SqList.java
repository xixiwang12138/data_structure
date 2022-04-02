package data_structure.lineList;

public  abstract class SqList implements Llist{
    private Object[] listElem;
    private int curLen;

    //构造函数,构造一个存储空间容量为MaxSize的线性表
    public SqList(int MaxSize){
        curLen = 0 ;
        listElem = new Object[MaxSize];
    }

    //插入，在索引为i的元素前插入一个值为x的数据元素
    public void insert(int i ,Object x) throws Exception{
        if (curLen == listElem.length){   //判断数据表是否已满
            throw new Exception("顺序表已满");
        }
        if (i<0 || i>curLen){             //判断插入位置是否合法
            throw new Exception("插入位置不合法");
        }
        for (int j = i; j<curLen ; j++ ){
            listElem[j+1]=listElem[j];    //数据的移动
        }
        listElem[i]=x;
        curLen++;
    }


    //删除，将顺序表的索引为i的数据从顺序表中删除
    public void remove(int i) throws Exception{
        if (i<0 || i>curLen-1){
            throw new Exception("删除位置不合法");
        }
        for (int j=i ; j<curLen-1; j++){
            listElem[j]=listElem[j+1];
        }
        curLen--;
    }

    //查找，查找满足条件的数据元素的所在位置
    public int indexOf(Object x){
        int tar = 0 ;
        while (tar<curLen && !listElem[tar].equals(x)){    //不知道次数只知道何时停止，考虑用while循环
                                                           //对equals方法谨慎，此时调用的是Object类中的equals方法，判断引用是否相等
            tar++;
        }
        if (tar<curLen){
            return tar;
        }else {
            return -1;
        }
    }


    //就地逆置
    public void reverse(){
        for(int i = 0,j = curLen-1;i<j;i++,j--){
            Object temp = listElem[i];
            listElem[i]=listElem[j];
            listElem[j]=temp;
        }
    }

    public void move(int k){
//        for (int i = 0; i < listElem.length; i++) {
//            if (i<listElem.length-k){
//                listElem[i+k]=listElem[i];
//            }else{
//                listElem[k+i-listElem.length]=listElem[i];
//            }
//        }
    }

}