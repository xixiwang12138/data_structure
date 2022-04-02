package algorithm.sidingWindows;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num；某个arr中的子数组sub，如果想达标，必须满足
 * sub中最大值一sub中最小值＜=num,
 * 返回arr中达标子数组的数量。
 */
public class ValidSub {
    public static void main(String[] args) {
        int n = (int) (Math.random() * 30000);
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 10000000);
        }
        int tar = 200;
//        System.out.println(Arrays.toString(nums));
        System.out.println(subCount(nums,tar));
        System.out.println(subCount2(nums,tar));
        System.out.println(subCount3(nums,tar));

    }

    //暴力求解对数器
    public static int subCount(int[] nums, int tar) {
        long t1 = System.currentTimeMillis();
        int N = nums.length;
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (max(nums, i, j) - min(nums, i, j) <= tar) {
                    ++res;
                }
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("暴力解法："+(t2-t1)+"ms");
        return res;
    }

    public static int max(int[] nums, int L, int R) {
        int max = nums[L];
        for (int i = L + 1; i <= R; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    public static int min(int[] nums, int L, int R) {
        int min = nums[L];
        for (int i = L + 1; i <= R; i++) {
            min = Math.min(min, nums[i]);
        }
        return min;
    }


    //暴力解法的优化
    public static int subCount2(int[] nums, int tar) {
        long t1 = System.currentTimeMillis();
        int N = nums.length;
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (max(nums, i, j) - min(nums, i, j) <= tar) {
                    ++res;
                } else {
                    break;  //利用特性2，一旦出现不合法，就不用扩大右边的范围
                }
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("暴力解法优化："+(t2-t1)+"ms");
        return res;
    }

    //滑动窗口
    public static int subCount3(int[] nums, int tar) {
        long t1 = System.currentTimeMillis();
        int N = nums.length;
        int L =0 ;
        int R = 0;
        int res = 0;

        LinkedList<Integer> maxQueue = new LinkedList<>();
        LinkedList<Integer> minQueue = new LinkedList<>();
        while (L < N) {
            while (R < N) {
                //构建滑动窗口最大值结构
                while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] <= nums[R]) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(R);
                //构建滑动窗口最小值结构
                while (!minQueue.isEmpty() && nums[minQueue.peekLast()] >= nums[R]) {
                    minQueue.pollLast();
                }
                minQueue.addLast(R);
                if (nums[maxQueue.getFirst()] - nums[minQueue.getFirst()] > tar) {
                    break;
                }
                R++;
            }
            res = res + (R - L);  //在L固定时，现在R到达第一个违规的位置
            //此时，需要判断maxQueue、minQueue左边的边界是否过期，因为接下来会对L进行改变
            //过期条件
            if(maxQueue.peekFirst() == L ){
                maxQueue.pollFirst();
            }
            if(minQueue.peekFirst() == L ){
                minQueue.pollFirst();
            }
            ++L;
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1+"ms");
        return res;
    }
}
