package problems;

/**
 * 给你几个参数：
 * N：代表总共有多少个位置。
 * M：机器人的出发位置。
 * P：机器人的目的地。
 * K：机器人必须走K步。
 * 问题：请你求出机器人从S起始位置走K步到达终止位置E，一共有多少种走法？
 * 注意，每次机器人必须走一步，不能留在原地，每次只能走一格，可以向右或者向左。
 * 在 1 位置只能向右走
 * 在 N 位置只能向左走
 */
public class RobotMoving {
    public static void main(String[] args) {
        System.out.println(robotMoving2(7, 2, 3, 5));
    }
    /**
     * 暴力递归
     * @return
     */
    public static int robotMoving(int N, int M, int P, int K) {
        //参数合法性判断
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        return process(M, P, N, K, 0);
    }

    /**
     * @param cur    当前所在的位置
     * @param curSum 在当前位置已经走过的步数
     *               P：机器人的目的地。
     *               K：机器人必须走K步。
     * @return 可能的方法数量
     */
    public static int process(int cur, int P, int N, int K, int curSum) {
        if (curSum == K && cur == P) {
            return 1;
        }
        //一定要进行此处的判断，否则会引发递归过深
        if (curSum == K && cur != P) {
            return 0;
        }
        int res = 0;
        if (cur == 1) {
            res += process(2, P, N, K, curSum + 1);
        } else if (cur == N) {
            res += process(N - 1, P, N, K, curSum + 1);
        } else {
            res += process(cur + 1, P, N, K, curSum + 1);
            res += process(cur - 1, P, N, K, curSum + 1);
        }
        return res;
    }

    /**
     * 缓存减少重复计算
     */
    public static int robotMoving2(int N, int M, int P, int K) {
        //参数合法性判断
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        //初始化缓存表,所有组合都是-1
        for (int row = 0; row < N + 1; row++) {
            for (int col = 0; col < K + 1; col++) {
                dp[row][col] = -1;
            }
        }
        return process2(M, P, N, K, 0, dp);
    }

    /**
     * @param cur    当前所在的位置
     * @param curSum 在当前位置已经走过的步数
     * P：机器人的目的地。
     * K：机器人必须走K步。
     * @return 可能的方法数量
     */
    public static int process2(int cur, int P, int N, int K, int curSum, int[][] dp) {
        if (dp[cur][curSum] != -1) {
            return dp[cur][curSum];
        }
        if (curSum == K && cur == P) {
            dp[cur][curSum] = 1;
            return dp[cur][curSum];
        }
        //一定要进行此处的判断，否则会引发递归过深
        if (curSum == K && cur != P) {
            dp[cur][curSum] = 0;
            return dp[cur][curSum];
        }
        int res = 0;
        if (cur == 1) {
            res += process2(2, P, N, K, curSum + 1, dp);
        } else if (cur == N) {
            res += process2(N - 1, P, N, K, curSum + 1, dp);
        } else {
            res += process2(cur + 1, P, N, K, curSum + 1, dp);
            res += process2(cur - 1, P, N, K, curSum + 1, dp);
        }
        dp[cur][curSum] = res;
        return res;
    }
}
