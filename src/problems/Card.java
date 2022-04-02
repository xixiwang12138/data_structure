package problems;

/**
 * 给定一个正数整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 */
public class Card {
    public static void main(String[] args) {
        System.out.println(win(new int[]{3,100,1,1}));
    }
    public static int win(int[] arr) {
        if(arr==null || arr.length==0 ){
            return -1;
        }
        //初始状态下是新手的分多还是后手的分多
        return Math.max(firstHand(arr,0, arr.length-1),
                secondHand(arr,0, arr.length-1));
    }

    //这两个函数需要指定一个选手来看

    //对于arr的L..R的状态下，先手可以做出选择，可以获得的最大分数
    public static int firstHand(int[] arr, int L, int R) {
        if (L == R) {  //只有一张牌的时候
            return arr[L];
        }
        //两种选择，取最左或最右
        return Math.max(arr[L] + secondHand(arr, L + 1, R),     //在L+1,R的状态下，先手变成了后手
                arr[R] + secondHand(arr, L, R - 1));
    }
    //在arr的L..R状态下的后手，可以获得的最大分数
    public static int secondHand(int[] arr, int L, int R) {
        if (L == R) {  //只有一张牌的时候，作为后手只能看着被抽走
            return 0;
        }
        //先手要么抽L位置，要么抽R位置，但是先手会选择对自己有利，被动的后手只会得到被动的结果
        return Math.min(firstHand(arr,L+1,R),    //对于L..R状态下的后手来说，在对手抽掉一张牌后，变成先手
                //非常重要的一点：聪明绝顶，选手都会算计，会将最差的结果留给对手
                //L..R的状态下后手处于被动状态，只能选择最小值
                firstHand(arr,L,R-1));
    }


}
