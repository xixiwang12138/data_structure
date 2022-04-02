package algorithm.MStack;

//单调栈

import problems.Array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 问题描述：
 * 给定一个数组长度为 n，请在结果的 n*2 二维数组中分别填入数组中每一个数左边最近比它小的数的索引和右边最近比它小的数的索引
 * 第一个数的结果在二维数组的第一行，第二个数的结果在二维数组的第二行，以此类推
 * 返回结果数组
 */
public class NearLess {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,0,3,5,3,5,1,0,5};
        int[][] res = getNearLessNoRepeat2(arr);
        for(int[] a : res){
            System.out.println(Arrays.toString(a));
        }
    }
    //简化版本：不考虑数组中存在重复值
    public static int[][] getNearLessNoRepeat(int[] arr){
        int[][] res = new int[arr.length][2];
        //ms中存储数组的下标
        Stack<Integer> ms = new Stack<>();
        ms.push(0);
        for (int i = 1; i < arr.length; i++) {
            //压栈过程中，要保证栈低到栈顶的从小到大的顺序
            while(arr[ms.peek()]>arr[i]){
                //如果不能正常压入栈，待入栈的元素即为栈顶元素的右边一个比其小的元素
                //栈顶元素压着的元素就是栈顶元素的左边一个比其小的元素
                int up = ms.pop();
                res[up][1] = i;
                if(ms.empty()){
                    res[up][0] = -1;
                    break;
                }
                int down = ms.peek();
                res[up][0] = down;
            }
            ms.push(i);
        }
        while (!ms.empty()){
            int up = ms.pop();
            res[up][1] = -1;
            if(ms.empty()){
                res[up][0] = -1;
                break;
            }
            int down = ms.peek();
            res[up][0] = down;
        }
        return res;
    }

    //存在重复值的时候
    public static int[][] getNearLessNoRepeat2(int[] arr){
        int[][] res = new int[arr.length][2];
        //ms中存储数组的下标的链表，每一个链表代表的数组的值相同
        Stack<LinkedList<Integer>> ms = new Stack<>();
        LinkedList<Integer> ele = new LinkedList<>();
        ele.add(0);
        ms.push(ele);
        for (int i = 1; i < arr.length; i++) {
            //压栈过程中，要保证栈低到栈顶的从小到大的顺序
            while(!ms.empty() && arr[ms.peek().get(0)]>=arr[i]){
                if(arr[ms.peek().get(0)]==arr[i]){
                    ms.peek().add(i);
                    break;
                }
                LinkedList<Integer> up = ms.pop();
                for(int index : up){
                    res[index][1] = i ;
                    res[index][0] = ms.empty() ? -1 : ms.peek().getLast();
                }
            }
            LinkedList<Integer> ele2 = new LinkedList<>();
            ele2.add(i);
            ms.push(ele2);
        }
        while (!ms.empty()){
            LinkedList<Integer> up = ms.pop();
            for(int index : up){
                res[index][1] = -1 ;
                res[index][0] = ms.empty() ? -1 : ms.peek().getLast();
            }
        }
        return res;
    }

}
