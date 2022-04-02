package problems;

public class Recursion {
    public static void main(String[] args) {
        System.out.println(letter(4));
    }
    /**
     * 问题描述：一个村里有n个人，先进行互相写信的活动，每个人必须参与
     * 每个人只能写一封信给其他任何人，不能写给自己，也只能收到来自其他人的一封信
     * 求共有多少种送收情况
     */
    public static int letter(int n){
        //思路：选定一个人(设为A)为视角，他可以给其他(n-1)个人送信
        //然后有以下两种情况：1. 收到信的那个人(B)再给寄信人(A)送信，共有letter(n-2)种送法
        //2. 收到信的那个人(B)再给非寄信人送信，然后将两个人组合为一个人(有进有出)，共有letter(n-1)种
        if(n==1){
            return 0;
        }
        if(n==2){
            return 1;
        }
        if(n==3){
            return 2;
        }
        return (n-1)*(letter(n-1)+letter(n-2));
    }
}
