package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，
 * 用于能够生成所有可能的并且 有效的 括号组合。
 */
public class GenerateParenthesis {
    /**
     * 方法一：暴力递归
     */
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<String>();
        char[] charArray = new char[2*n];
        process(res,charArray,0);
        return res;
    }
    //process函数处理index位置的组合情况并加入curRes
    public void process(ArrayList<String> res,char[] curRes,int index){
        if(index==curRes.length){   //满足此条件时，已经生成好了
            if(isValid(curRes)){    //如果结果合法就加入集合
                res.add(new String(curRes));
            }
        }else{  //没有达到长度
            //分两个情况会将函数继续执行下去进行递归，直到index==curRes.length
            curRes[index] = '(';
            process(res,curRes,index+1);
            curRes[index] = ')';
            process(res,curRes,index+1);
        }
    }
    public boolean isValid(char[] res){
        int left = 0;
        int right = 0;
        for(int j = 0; j<res.length;j++){
            if(res[j]=='('){
                left++;
            }else{
                right++;
            }
            if(right>left){
                return false;
            }
        }
        return left==right;
    }
}
