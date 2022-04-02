package algorithm.SortAlgorithm;

import java.util.Date;

public class BubbleSorting {
    public static void main(String[] args) {
        int[] nums = new int[80000];
        for (int i = 0; i < 80000; i++) {
            nums[i] =(int)(Math.random()*80000);
        }

        Date t1 = new Date();
        System.out.println("排序前："+t1.toString());
        BubbleSorting.bubbleSorting1(nums);
        Date t2 = new Date();
        System.out.println("排序后："+t2.toString());
    }
    public static void bubbleSorting(int[] nums){
        for(int i = 0 ; i<nums.length ; i++){
            for(int j = nums.length-1 ; j>i ; j--){
                if(nums[j]<nums[j-1]){
                    int temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                }
            }
        }
    }

    public static void bubbleSorting1(int[] nums){
        for(int i = 0 ; i<nums.length ; i++){
            boolean flag = false;       //元素是否进行了交换
            for(int j = nums.length-1 ; j>i ; j--){
                if(nums[j]<nums[j-1]){
                    int temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                    flag = true;
                }
            }
            if(!flag){    //在一趟排序中，一次交换都没有发生过
                return;
            }
        }
    }


    public static void bubbleSorting2(int[] nums){
        boolean flag = false;       //元素是否进行了交换
        for(int i = 0 ; i<nums.length ; i++){
            for(int j = nums.length-1 ; j>i ; j--){
                if(nums[j]<nums[j-1]){
                    int temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                    flag = true;
                }
            }
            if(!flag){    //在一趟排序中，一次交换都没有发生过
                return;
            }else{
                flag = false;   //如果发生了交换，说明排序未终止，则将下一次趟（外层循环）flag重置为false
            }
        }
    }


}
