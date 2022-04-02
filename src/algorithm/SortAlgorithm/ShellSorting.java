package algorithm.SortAlgorithm;

import java.util.Arrays;
import java.util.Date;

public class ShellSorting {
    public static void main(String[] args) {
        int[] nums = new int[]{8,9,1,7,2,3,5,4,6,0};
//        for (int i = 0; i < 80000; i++) {
//            nums[i] = (int) (Math.random() * 800000);
//        }

        long t1 = System.currentTimeMillis();
        shellSorting1(nums);  //332ms左右
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1 + "ms");


//        int[] arr = new int[]{1, 3, 43, 5, 4, 5, 65, 8, 79, 7, 89877, 76566676, 74, 6, 44, 57, 65, 765, 56735, 797};
//        shellSorting(arr);
//        System.out.println(Arrays.toString(arr));
    }

    //基于预处理的优化 不是希尔排序
    public static void insertSorting(int[] nums) {
        int gap = nums.length / 2;
        while (gap != 1) {
            for (int i = 0; i < gap; i++) {
                if (nums[i] > nums[i + gap]) {
                    int temp = nums[i];
                    nums[i] = nums[i + gap];
                    nums[i + gap] = temp;
                }
            }
            gap = gap / 2;
        }

        //后面即为插入排序
        for (int i = 1; i < nums.length; i++) {
            int insertVal = nums[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && nums[insertIndex] > insertVal) {
                //nums[insertIndex] > insertVal和insertIndex>=0两个语句的位置要注意，可能会引发
                nums[insertIndex + 1] = nums[insertIndex];
                insertIndex--;
            }
            nums[insertIndex + 1] = insertVal;
        }

    }

    //交换式
    //int[] arr = {8,9,1,7,2,3,5,4,6,0};
    public static void shellSorting1(int[] nums) {
        int temp = 0;
        for (int gap = nums.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < nums.length; i++) {
                //遍历每一组中的所有元素(共gap组)，步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (nums[j] > nums[j + gap]) {
                        temp = nums[j];
                        nums[j] = nums[j + gap];
                        nums[j + gap] = temp;
                    }
                }
            }
        }

    }
}
