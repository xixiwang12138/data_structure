package algorithm.SortAlgorithm;

import java.util.Arrays;
import java.util.Date;

public class QuickSorting {
    public static void main(String[] args) {
        int[] nums = new int[800000];
        for (int i = 0; i < 800000; i++) {
            nums[i] =(int)(Math.random()*800000);
        }

        long t1 = System.currentTimeMillis();
        quickSorting(nums,0, nums.length-1);   //32ms
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1 + "ms");
//        System.out.println(Arrays.toString(nums));
    }
    public static void quickSorting(int[] nums, int start , int end){
        if(start<end) {
            //将起始数位置看作标准
            int stard = nums[start];
            int low = start;
            int high = end;

            //循环找比标准数大或小的数
            while (low < high) {
                while (low < high && stard <= nums[high]) {     //右指针的数比标准数大，不用移动位置
                    high--;                               //只需要将右边这个指针向左移动
                }
                //使用右指针的数替换左指针的数
                nums[low] = nums[high];
                while (low < high && nums[low] <= stard) {        //左指针的数比标准数小，不用移动位置
                    low++;
                }
                nums[high] = nums[low];
            }
            //把标准数赋给重合的位置
            nums[low] = stard;
            //处理比标准小的数字
            //处理比标准大的数字
            quickSorting(nums, start, low - 1);
            quickSorting(nums, high + 1, end);
        }
    }
}
