package problems;

/**
 * 在8×8格的国际象棋上摆放8个皇后，使其不能互相攻击，
 * 即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 */
public class EightQueens {
    public static void main(String[] args) {
        System.out.println(eightQueens(13));
    }
    public static int eightQueens(int n) {
        if(n<1){
            return 0;
        }
        int[] record = new int[n];
        return process(n,record,0);
    }

    public static int process(int n, int[] record, int lineNum) {
        if(lineNum==n){
            return 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {   //需要将一行搜索完全,在每一个满足的点位下继续向下一行递归
            if(isValid(record,lineNum,i)){
                record[lineNum] = i ;    //在record中添加记录
                res = res + process(n, record, lineNum+1);
            }
        }
        return res;  //如果没有任何一个i满足合法条件，就直接返回0，不向下一行搜索
    }

    //record[i]表示i行上的皇后的列位置

    /**
     * 判断(lineNum,j)位置是否与前lineNum行冲突
     * @param record  记录的是前 lineNum行的皇后位置
     * @param lineNum  判断的行数
     * @param j  列数
     * @return
     */
    public static boolean isValid(int[] record,int lineNum,int j){
        //保证每一行只有一个，所以行上一定满足条件
        for (int i = 0; i < lineNum; i++) {
            if(j==record[lineNum] || Math.abs(lineNum-i)==Math.abs(record[lineNum]-j)){
                return false;
            }
        }
        return true;
    }
}
