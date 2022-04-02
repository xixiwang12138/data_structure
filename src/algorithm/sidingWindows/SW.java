package algorithm.sidingWindows;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 窗口内最大值
 */
public class SW {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(arr, 3)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int resIndex = 0;

        //注意：doubleQ储存的是对应元素的数组中索引
        LinkedList<Integer> doubleQ = new LinkedList<>();
        for (int R = 0; R < nums.length; R++) {
            while (!doubleQ.isEmpty() && nums[doubleQ.peekLast()] <= nums[R]) {
                doubleQ.pollLast();
            }
            doubleQ.addLast(R);
            if (doubleQ.peekFirst() == R - k) {
                doubleQ.pollFirst();
            }
            if (R >= k - 1) {
                res[resIndex++] = nums[doubleQ.peekFirst()];
            }
        }
        return res;
    }
}
