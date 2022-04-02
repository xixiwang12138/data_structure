package algorithm.dp;

import java.util.Arrays;

public class Jump {
    public static void main(String[] args) {
        int[] arr = new int[]{13055,19141,12848,11888,1185,9905,3984,14772,
                18935,6844,9851,13695,17955,16359,14969,6200,13436,16940,13893,1639,
                3233,11861,13475,11963,8193,3413,10952,8585,13807,6320,4507,1743,454,
                7983,9677,8348,2496,4585,11022,9103,1096,6770,6745,4350,2334,13024,
                2738,9444,1028,5337,2446,272,5799,7397,15000,4848,16772,12860,11773,196,
                3187,14016,1314,1684,19858,2598,16111,10577,1643,12252,5835,2819,17898,18210,
                799,5010,19937,1285,9693,18155,484,9184,7212,3474,14630,18418,
                10108,1735,3940,2877,18007,14731,10133,
                10470,15396,7464,6936,261};
//        System.out.println(canJump(arr));
        System.out.println(dp(arr));
    }
    public static int canJump(int[] nums) {
        return process(nums, 0) ;
    }

    //从当前位置curPos走到最后一个位置的方法数
    public static int process(int[] nums, int curPos) {
        if (curPos == nums.length - 1) {
            return 1;
        }
        int res = 0;
        for (int i = 1; i <= nums[curPos]; i++) {
            if(curPos + i > nums.length - 1){
                break;
            }
            res += process(nums, curPos + i);
        }
        return res;
    }

    public static int dp(int[] nums) {
        int N = nums.length;
        //dp[curPos]表示从当前位置curPos走到最后一个位置的方法数
        int[] dp = new int[N + 1];
        dp[N] = 0;
        dp[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            int res = 0;
            for (int j = 1; j <= nums[i]; j++) {
                if(i+j <= N-1) {
                    res += dp[i + j];    //可能会发生溢出
                    if(res!=0){
                        dp[i] = 1; //直接标记即可
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[0] ;
    }
}
