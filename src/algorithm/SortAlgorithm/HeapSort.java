package algorithm.SortAlgorithm;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] nums = new int[80000];
        for (int i = 0; i < 80000; i++) {
            nums[i] =(int)(Math.random()*800000);
        }

        long t1 = System.currentTimeMillis();
        heapSorting(nums);
        System.out.println(Arrays.toString(nums));
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1 + "ms");
    }
    public static void heapSorting(int[] nums) {
        establishHeap(nums);
        for (int i = 0; i < nums.length-1; i++) {
            swap(nums,0, nums.length-1-i);
            sink(nums,0, nums.length-2-i);
        }
    }
    /**
     * 将原始数组构建成规则的堆
     *
     * @param nums 原始数组
     */
    public static void establishHeap(int[] nums) {
        //构建方法，从最后一个叶子节点（非叶子节点无法下沉）开始到0，进行元素的下沉
        for(int i = nums.length/2-1;i>=0;--i){
            sink(nums,i,nums.length-1);
        }
    }
    /**
     * 将索引为k的元素从规则的堆中下沉
     * @param nums
     * @param k     待下沉的元素
     * @param range 尚未排好序的范围
     */
    public static void sink(int[] nums, int k, int range) {
        while (2 * k + 1 <= range) {
            if (2 * k + 2 > range) {
                if (nums[k] < nums[2 * k + 1]) {
                    swap(nums, k, 2 * k + 1);
                }
                return;
            } else if(2 * k + 2 <= range){   //即最后一个非叶结点的右节点在范围内
                int maxIndex = nums[2 * k + 1] > nums[2 * k + 2] ? 2 * k + 1 : 2 * k + 2;
                if (nums[k] >= nums[maxIndex]) {
                    return;
                } else {
                    swap(nums, k, maxIndex);
                }
                k = maxIndex;
            }
        }
    }
    //交换
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
