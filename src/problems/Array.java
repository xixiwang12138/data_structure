package problems;

import java.util.Arrays;
import java.util.HashSet;

public class Array {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 3, 43, 4, 4, 54, 43, 54, 9, 56};
        System.out.println("before");
        System.out.println(Arrays.toString(arr));
        flag(arr, 43);
        System.out.println();
        System.out.println("after");
        System.out.println(Arrays.toString(arr));
    }

    //荷兰国旗问题
    //简化版
    public static void simplify(int[] nums, int reg) {
        int lessAreaIndex = -1;   // <=区域保证里面的数小于等于reg,索引为0的位置的数不一定满足,所以初始化为-1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= reg) {
                //交换<=区域的下一个数和nums[i]
                int temp = nums[i];
                nums[i] = nums[lessAreaIndex + 1];
                nums[lessAreaIndex + 1] = temp;

                ++lessAreaIndex;
            }
        }
    }

    public static void flag(int[] nums, int reg) {
        int lessAreaIndex = -1;
        int largeAreaIndex = nums.length;
        int i = 0 ;
        while ( i < largeAreaIndex) {
            if (nums[i] < reg) {
                //交换<=区域的下一个数和nums[i]
                int temp = nums[i];
                nums[i] = nums[lessAreaIndex + 1];
                nums[lessAreaIndex + 1] = temp;
                ++lessAreaIndex;
                i++;
            } else if (nums[i] > reg) {
                int temp = nums[i];
                nums[i] = nums[largeAreaIndex - 1];
                nums[largeAreaIndex - 1] = temp;
                --largeAreaIndex;
            } else {
                i++;
            }
        }
    }
}

/**
 * leetcode26
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
class SolutionFor26 {
    public int removeDuplicates(int[] nums) {
        /** 思路
         1. 快指针一直向后走
         2. 当慢指针在快指针前后元素不一致时才向右走，前后元素一致时不移动慢指针
         3. 一直将快指针的值复制给慢指针
         4. 注意先移动指针后进行赋值操作
         */
        int slow = 0;
        int quick = 0;
        while (quick < nums.length - 1) {
            if (nums[quick] == nums[quick + 1]) {
                quick++;
            } else {
                slow++;
                quick++;
            }
            if (quick - slow >= 1) {      //优化
                nums[slow] = nums[quick];  //减少原地复制（快慢指针未分开时）
            }
        }
        return slow + 1;
    }
}

/**
 * leetcode217
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true
 * 如果数组中每个元素互不相同，返回 false 。
 */
class SolutionFor217 {
    //暴力求解
    public boolean containsDuplicate01(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    //利用HashSet去重
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        if (set.size() == nums.length) {
            return false;
        } else {
            return true;
        }
        /**
         * 利用哈希表求解的第二种方法，效率更高
         */
//        for (int x : nums) {
//            if (!set.add(x)) {
//                return true;
//            }
//        }
//        return false;

    }

    //排序后进行判断
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        return false;
    }

    /**
     * leetcode136
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：算法应该具有线性时间复杂度。
     * 对于任何数x，都有 x^x=0，x^0=x
     * 特征：偶数次与奇数次
     */
    class SolutionFor136 {
        public int singleNumber(int[] nums) {
            //利用位运算的性质：对于任何数x，都有 x^x=0，x^0=x，异或运算
            int result = nums[0];
            for (int i = 1; i < nums.length; i++) {
                result = result ^ nums[i];
            }
            return result;
        }
    }


    /**
     * leetcode53
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     * <p>
     * 动态规划问题
     */
    class SolutionFor53 {
        public int maxSubArray(int[] nums) {
            //动态规划，首先需要一个记录状态的dp数组
            int[] dp = new int[nums.length];
            //dp[i]记录以i结尾的最大子序列数组

            //状态转移方程
            //dp[i] = Math.max(nums[i],dp[i-1]+nums[i])
            //状态转移方程的解释：两种选择使目前的子数组最大
            //要么dp[i-1]为负数，就nums[i]最大
            //要么dp[i-1]为正数，然后加上nums[i]即为以i结尾的最大子数组和
            dp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            }

            //找出dp数组中最大值
            int res = Integer.MIN_VALUE;
            for (int j = 0; j < nums.length; j++) {
                res = Math.max(res, dp[j]);
            }
            return res;
        }
    }
}
