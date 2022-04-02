package problems;

public class Demo {
    public static void main(String[] args) {
        System.out.println(solution());
        System.out.println(Math.pow(3,11)-Math.pow(2,11)+3);
    }
    public static int solution(){
        int[] ints = new int[3];
        return process(11,ints);
    }

    public static int process(int rest,int[] flag){
        if(rest==0){
            boolean f = false;
            for(int i : flag){
                if(i<1){
                    f=true;
                }
            }
            return f ? 0 : 1;
        }

        int res = 0 ;
        int A = process(rest - 1, flag);
        flag[0]++;

        int B = process(rest - 1, flag);
        flag[1]++;

        int C = process(rest - 1, flag);
        flag[2]++;

        return A+B+C;


    }
}
