package algorithm.SortAlgorithm;

import java.util.Arrays;
import java.util.Date;

public class SelectSorting {
    public static void main(String[] args) {
        int[] nums = new int[80000];
        for (int i = 0; i < 80000; i++) {
            nums[i] =(int)(Math.random()*80000);
        }

        Date t1 = new Date();
        System.out.println("排序前："+t1.toString());
        selectSorting(nums);
        Date t2 = new Date();
        System.out.println("排序后："+t2.toString());
    }
    public static void selectSorting(int[] nums){

        for(int i = 0 ; i<nums.length-1 ; i++){
            int min = nums[i];
            int index = i ;
            for(int j = i ; j < nums.length;j++){
                if(nums[j]<min){
                    index = j ;
                    min = nums[j];
                }
            }
            nums[index] = nums[i];
            nums[i] = min;

        }
    }
}
