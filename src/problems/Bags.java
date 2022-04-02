package problems;

/**
 * 有一个容量为bag的背包，还有n个物体。
 * 现在忽略物体实际几何形状，我们认为只要背包的剩余容量大于等于物体体积，
 * 那就可以装进背包里。每个物体都有两个属性，即体积w和价值v。
 * n个物品的体积与价值分别用长度为n的两个数组进行表示
 */
public class Bags {
    public static void main(String[] args) {
        int[] w = new int[]{1, 2, 3, 4, 6};
        int[] v = new int[]{1, 1, 8, 8, 6};

        System.out.println(maxValue(w, v, 10));
        System.out.println(dpWays(w, v, 10));
    }

    /**
     * 暴力递归解法1
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, bag, 0, 0);
    }

    /**
     * @param /bag/w/v 传入的参数
     * @param index
     * @param alreadyW index之前的已经选的总重量之和
     * @return v[index..]之后的选择的总价值的最大值
     */
    public static int process(int[] w, int[] v, int bag, int index, int alreadyW) {
        if (alreadyW > bag) {   //已经超重了，返回-1表示该递归方案不可行
            return -1;
        }
        if (index == w.length) {  //index来到最后的位置，后面的总价值最大值为0
            return 0;
        }

        //选择1：不选择i位置的货物
        int result1 = process(w, v, bag, index + 1, alreadyW);

        //选择2：选择i位置的货物
        //result2next表示index位置之后的总价值最大值
        int result2next = process(w, v, bag, index + 1, alreadyW + w[index]);
        int result2 = -1;  //result2初始化为-1，如果result2next==-1方案不可行，result2设置为-1
        if (result2next != -1) {    // 不等于-1即选择i位置的方案可行
            result2 = result2next + v[index];
        }
        return Math.max(result1, result2);
    }

    /**
     * 暴力递归解法2
     */
    public static int maxValue2(int[] w, int[] v, int bag) {
        return process2(w, v, 0, bag);
    }

    /**
     * 返回index后货物的价值的最大值
     *
     * @param w
     * @param v
     * @param index 当前讨论的索引
     * @param rest  背包中还剩下的可以装货物的空间
     * @return 返回index以及之后货物的价值的最大值
     */
    public static int process2(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == w.length) {
            return 0;
        }
        if (w[index] <= rest) {
            //两个选择，要index或者不要index
            //选择1：不要index
            int result1 = process2(w, v, index + 1, rest);
            //选择2：要Index
            int result2 = process2(w, v, index + 1, rest - w[index]) + v[index];
            return Math.max(result1, result2);
        } else {  //只有一种选择，不要index
            return process2(w, v, index + 1, rest);
        }
    }

    public static int dpWays(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];   //横坐标表示index.列坐标表示rest
        //边界：index==w.length -> return 0;
        //数组自动默认为0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                //两个选择，要index或者不要index
                //选择1：不要index
                int result1 = dp[index + 1][rest];
                int result2 = -1;
                //注意判断合法性，
                if (w[index] <= rest) {
                    //选择2：要Indexrest - w[index]是否会下标越界
                    result2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(result1, result2);
            }
        }
        return dp[0][bag];
    }


}
