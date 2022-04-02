package problems;


public class CoinChange {
    /**
     * 问题1：返回组合的方法数量
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * 假设每一种面额的硬币有无限个。
     * 题目数据保证结果符合 32 位带符号整数。
     */

    public static int change(int amount, int[] coins) {
        return process(coins, 0, amount);
    }

    /**
     * @param coins 零钱的组合数量
     * @param rest  还剩下没有凑齐的金额
     * @return 返回的是coins[index..]的零钱凑成rest的方法数量
     */
    public static int process(int[] coins, int index, int rest) {
        //由于在函数中保证了 num * coins[index] <= rest 即保证了rest >= 0 恒成立
//        if (rest <= 0) {
//            return 0;
//        }
        //index来到零钱的末尾
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }

        int res = 0;
        //num表示使用当前coins[index]的零钱的数量
        for (int num = 0; num * coins[index] <= rest; num++) {
            res = res + process(coins, index + 1, rest - num * coins[index]);
        }

        return res;
    }

    /**
     * 记忆化搜索
     *
     * @param amount
     * @param coins
     * @return
     */
    public static int change2(int amount, int[] coins) {
        //创建记忆化搜索的数组
        //横坐标表示index,纵坐标表示rest
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < amount + 1; j++) {
                dp[i][j] = -1;
            }
        }
        dp[n][0] = 1;
        for (int j = 1; j < amount+1; j++) {
            dp[n][j] = 0 ;
        }
        return process2(coins, 0, amount, dp);
    }

    public static int process2(int[] coins, int index, int rest, int[][] dp) {
        //由于在函数中保证了 num * coins[index] <= rest 即保证了rest >= 0 恒成立
//        if (rest <= 0) {
//            return 0;
//        }
        //index来到零钱的末尾

        if(dp[index][rest]!=-1){
            return dp[index][rest];
        }
        int res = 0;
        //num表示使用当前coins[index]的零钱的数量
        for (int num = 0; num * coins[index] <= rest; num++) {
            res = res + process2(coins, index + 1, rest - num * coins[index],dp);
        }
        dp[index][rest] = res;
        return res;
    }


    /** 问题2：凑成总金额所需的 最少的硬币个数
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1。
     * 你可以认为每种硬币的数量是无限的。
     */
    public static int coinChange(int[] coins,int amount){
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < amount + 1; j++) {
                dp[i][j] = -1;
            }
        }
        dp[n][0] = 1;
        for (int j = 1; j < amount+1; j++) {
            dp[n][j] = 0 ;
        }
        return process3(coins,0,amount,dp);
    }


    /**
     * @param coins
     * @param rest  剩余的金额
     * @param index  当前索引
     * @return 返回coins[index...]凑成rest所需的最小硬币数量
     */
    public static int process3(int[] coins,int index,int rest,int[][] dp){
        if(dp[index][rest]!=-1) {
            return dp[index][rest];
        }

        if (index == coins.length ) {
            if(rest==0){
                dp[coins.length][rest] = 0;
                return 0;   //可以凑成
            }else{
                dp[coins.length][rest] = -1;
                return -1;  //无法凑成
            }
        }
        int res= Integer.MAX_VALUE;
        boolean isPossible = false;
        //num表示使用当前coins[index]的零钱的数量
        for (int num = 0; num * coins[index] <= rest; num++) {
            //由种类为index + 1及rest - num * coins[index]之后的零钱凑成的数量
            int nextRes = process3(coins, index + 1, rest - num * coins[index],dp);
            if(nextRes!=-1) {
                isPossible = true;
                res = Math.min(res, nextRes + num);
            }
        }
        if(isPossible){
            dp[coins.length][rest] = res;
            return res ;
        }else {
            dp[coins.length][rest] = -1;
            return -1;
        }
    }

    
}
