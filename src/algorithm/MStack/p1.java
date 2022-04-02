package algorithm.MStack;

import java.util.Stack;

/**
 * 给定一个数组，对于任何连续子数组，返回所有子数组的（子数组的和 * 子数组中最小值）的最大值
 */
public class p1 {
    public static int sol(int[] arr){
        //涉及连续子数组的和的问题——生成前缀和数组 prefixSum
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i-1] + arr[i];
        }
        int res = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < arr.length; i++) {
            while(!stack.isEmpty() && arr[i]<=arr[stack.peek()]){
                int up = stack.pop();
                res = Math.max(res,(stack.isEmpty() ?
                        prefixSum[i-1] : (prefixSum[i-1] - prefixSum[stack.peek()])) * arr[up]);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int up = stack.pop();
            res = Math.max(res,(stack.isEmpty() ?
                    prefixSum[arr.length-1] : (prefixSum[arr.length-1] - prefixSum[stack.peek()])) * arr[up]);
        }
        return res;
    }
}
