package algorithm.SortAlgorithm;

import java.util.Arrays;
import java.util.Date;

public class InsertSorting {
    public static void main(String[] args) {
        int[] nums = new int[80000];
        for (int i = 0; i < 80000; i++) {
            nums[i] =(int)(Math.random()*800000);
        }

        long t1 = System.currentTimeMillis();
        insertSorting(nums);
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1 + "ms");
    }
    public static void insertSorting(int[] nums){

//        假设从小到大
        for(int i = 1 ; i < nums.length ; i++){
            int insertVal = nums[i];
            int insertIndex = i - 1;

            while(insertIndex>=0 && nums[insertIndex] > insertVal){
                //nums[insertIndex] > insertVal和insertIndex>=0两个语句的位置要注意，否则可能会引发数组越界
                nums[insertIndex+1]=nums[insertIndex];
                insertIndex--;
            }
            nums[insertIndex+1]=insertVal;
        }


    }
}
