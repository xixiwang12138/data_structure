package problems;

import java.util.ArrayList;
import java.util.List;

public class StringProblems {
    public static void main(String[] args) {

//        System.out.println(convertNum("12111131"));
//        System.out.println(dpWays("12111131"));
    }

    /**
     * 获取一个字符串的子串
     */
    public static List<String> allSubString(String s) {
        ArrayList<String> ans = new ArrayList<>();
        int len = s.length();
        for (int begin = 0; begin < len; begin++) {
            for (int end = 0; end < len; end++) {
                if (begin == end) {
                    continue;
                }
                ans.add(s.substring(begin, end));
            }
        }
        return ans;
    }

    /**
     * 获得所有的一个字符串的所有子序列
     * 对于所有子序列的数量：对于每一个字符，可以选择加入或者不加入，形成一个二叉选择树，每一个叶子结点对应一种结果
     */
    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        ArrayList<String> ans = new ArrayList<>();
        String path = "";
        process1(str, 0, ans, path); //index初始化为0，path初始化为“”空字符串
        return ans;
    }

    /**
     * @param strArray 传入的字符串对应的数组
     * @param index    当前处理的字符串的索引
     * @param ans      结果的字符串集合
     * @param path     当前的路径
     */
    public static void process1(char[] strArray, int index, List<String> ans, String path) {
        //index来到这里时说明已经到结尾了,一个子序列path已经形成，将其加在结果中
        if (index == strArray.length) {
            ans.add(path);
            return;
        }
        //两个选择：加入路径或者不加入路径
        String no = path;
        process1(strArray, index + 1, ans, no);     //不加此时的index对应的字符，path直接传过来

        String yes = path + String.valueOf(strArray[index]);   //加上对应的字符，path加上后继续传
        process1(strArray, index + 1, ans, yes);
    }


    /**
     * 获取一个字符串的字符的的全排列，结果中可以相同
     *
     * @param s
     * @return
     */
    public static List<String> permutation(String s) {
        ArrayList<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }

        char[] chs = s.toCharArray();
        process2(chs, 0, res);
        return res;
    }

    /**
     * @param str
     * @param i   str[0..i-1]已经确定好顺序，str[i..]的字符都有机会来到i位置
     * @param res 结果的集合
     */
    public static void process2(char[] str, int i, List<String> res) {
        if (i == str.length) {
            res.add(new String(str));
            return;
        }
        //如果i没有终止，i以后的字符均可以来到i位置，然后继续进行i之后的排列
        for (int j = i; j < str.length; j++) {
            swap(str, i, j);  //交换ij的位置
            process2(str, i + 1, res); //对i+1之后的子序列作相似的处理
            swap(str, i, j);  //再交换回来！！回到之前的状态，才可以进行其他的分支
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 规定1和A对应，2和B对应...将一个数字字符串转化,如“111”可以转化为“AAA”或“KA”或“AK”
     * 给定任意一个数组字符串，求有多少种转化结果
     */
    public static int convertNum(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process3(s.toCharArray(), 0);
    }

    /**
     * process3函数返回的是i及之后字符组成的共有多少种结果
     *
     * @param str
     * @param i   str[0..i-1]已经转换完成
     * @return 多少种正确的结果
     * 解决思路：
     * 判断每一位数字的特征，从而做出正确的选择
     * 基线条件有两个：一个是i达到字符串结尾，返回1；还有一个是i为‘0’，没有任何办法得到正确的转化，返回0
     */
    public static int process3(char[] str, int i) {
        if (i == str.length) {  // base case : i已经来到结束的位置，该结算了
            return 1;
        }

        //i没有到终止的位置
        if (str[i] == '0') {
            return 0;       //i位置直接是0的话，无论怎么样都不会在1~26之间正确取值
        }

        if (str[i] == '1') {    //有两个选择：一个选择是直接取i位1，转换为A，剩余i+1继续递归
            //第二个选择是直接与i+1位置的元素结合，得到一个10~19的数字，剩余i+2位置递归
            //选择1
            int res = process3(str, i + 1);

            //选择二
            if (i + 1 < str.length) {
                res += process3(str, i + 2);
            }
            return res;    //将两种结果的转化情况合并
        }
        if (str[i] == '2') {
            //选择1：直接取i位2，转换为B，剩余i+1继续递归
            int res = process3(str, i + 1);
            //选择2：与第二位结合
            if (i + 1 < str.length && str[i] >= '0' && str[i] <= '6') {
                res += process3(str, i + 2);
            }
            return res;
        }
        //str[i] = 3,4,5,6,7,8,9——只有一种选择，即只转化一位
        return process3(str, i + 1);
    }

    public static int dpWays(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        //结合暴力递归过程中的i的变化范围
        int[] dp = new int[n + 1];
        /**
         * 以下是生成dp过程
         */
        dp[n] = 1;
        //已知n位置的值，从左往右填表
        for (int i = n - 1; i >= 0; i--) {
            //i没有到终止的位置
            if (str[i] == '0') {
                dp[i] = 0;       //i位置直接是0的话，无论怎么样都不会在1~26之间正确取值
            }
            else if (str[i] == '1') {    //有两个选择：一个选择是直接取i位1，转换为A，剩余i+1继续递归
                //第二个选择是直接与i+1位置的元素结合，得到一个10~19的数字，剩余i+2位置递归
                //选择1
                int res = dp[i + 1];
                //选择二
                if (i + 1 < str.length) {
                    res += dp[i + 2];
                }
                dp[i] = res;    //将两种结果的转化情况合并
            }
            else if (str[i] == '2') {
                //选择1：直接取i位2，转换为B，剩余i+1继续递归
                int res = dp[i + 1];
                //选择2：与第二位结合
                if (i + 1 < str.length && str[i] >= '0' && str[i] <= '6') {
                    res += dp[i + 2];
                }
                dp[i] = res;
            }
            //str[i] = 3,4,5,6,7,8,9——只有一种选择，即只转化一位
            else{
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }


}

class Solution {
    public static void main(String[] args) {
        String s1 = "baba";
        System.out.println(isPalindrome(s1));
    }
    public static boolean isPalindrome(String s) {
        String s1 = s.toLowerCase();
        ArrayList<Character> string = new  ArrayList<Character>();
        for(int i = 0 ; i < s1.length() ; i++){
            char c = s1.charAt(i);
            if(( c >= '0'  && c <= '9' ) || (c >= 'a' && c <= 'z') ){
                string.add(c);
            }
        }
        System.out.println(string.toString());
        int left = 0 ;
        int right = string.size()-1;
        while(left <=  right){
            if(string.get(left) != string.get(right)){
                return false;
            }
            ++left;
            --right;
        }
        return true;

    }
}
