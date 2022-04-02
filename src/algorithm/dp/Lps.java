package algorithm.dp;

/**
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 */
public class Lps {

}

class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);

    }

    //返回str[L..R]上的最长回文子序列长度
    public static int process(char[] str, int L, int R) {
        // base case
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }

        //一共有四种情况
        int p1 = process(str, L + 1, R - 1);  //L,R 都不包括在回文子序列中
        int p2 = process(str, L, R - 1); //L包含在回文子序列中，R不包含在其中
        int p3 = process(str, L + 1, R); //R包含在回文子序列中，L不包含在其中
        //LR都包含在其中，但是如果LR对应的元素不等的话，就不可能存在这种情况
        int p4 = str[L] != str[R] ? 0 : (p1 + 2);

        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];

        //首先dp表中的L>R的部分不会出现不用填，可以忽略
        //画出表的图
        //1.填基线条件:
        dp[N-1][N-1] = 1;  //先填最右下角，后面的情况可以一次填两个位置(排除简单特殊情况，后面的可以模式化进行)
        for (int i = N - 2; i >= 0; i++) {
            dp[i][i] = 1;
            dp[i][i+1] = str[i] == str[i+1] ? 2 : 1;
        }

        //2.分析任意位置的依赖——根据递归过程中的情况——决定填表顺序
        for(int k= N-3 ; k >= 0  ; k--){
            for(int j = k + 2 ; j < N ; j++){  //k是行，j是列
                int p1 = dp[k+1][j-1];
                int p2 = dp[k][j-1];
                int p3 = dp[k+1][j];
                //LR都包含在其中，但是如果LR对应的元素不等的话，就不可能存在这种情况
                int p4 = str[j] != str[k] ? 0 : (p1 + 2);
                dp[k][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }

        return dp[0][N-1];
    }


}
