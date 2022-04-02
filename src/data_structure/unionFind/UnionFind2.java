package data_structure.unionFind;

/**
 * 基于数组的实现
 */
public class UnionFind2 {
    public static void main(String[] args) {
        int[][] res = new int[][]{{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        System.out.println(findCircleNum(res));
    }

    //200 岛屿数量
    public static int numIslands(char[][] grid) {
        int res = 0;
        for (int k = 0; k < grid.length; k++) {
            for (int m = 0; m < grid[k].length; m++) {
                if (grid[k][m] == '1') {
                    infect(grid, k, m);
                    ++res;
                }
            }
        }
        return res;
    }

    //第i行，第j列向外感染
    public static void infect(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        infect(grid, i + 1, j);
        infect(grid, i, j + 1);
        infect(grid, i - 1, j);
        infect(grid, i, j - 1);
    }

//    //基于并查集的实现
//    public static int numIslands2(char[][] grid) {
//        UnionFind unionFind = new UnionFind(grid.length * grid.length);
//
//
//    }


    //547 省份数量/朋友圈
    public static int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        UnionFind unionFind = new UnionFind(N);
        //isConnected数组中对角线上都等于1，且是关于对角线对称的矩阵，只用遍历上半矩阵
        for (int row = 0; row <= N - 1; row++) {
            for (int column = row + 1; column <= N - 1; column++) {
                if (isConnected[row][column] == 1) {
                    unionFind.union(row, column);
                }
            }
        }
        return unionFind.size();


    }

    public static class UnionFind {
        public int[] parents; // parents[i] = k 表示 :  i的父亲结点是k
        private int[] sizes; // size[i] = k 在i为代表结点的时候才有意义，表示代表结点对应的集合整体的结点数量
        private int[] help;
        private int sets; // 此并查集中所有的代表结点的数量

        public UnionFind(int N) {    //N个结点组成一个并查集
            parents = new int[N];
            sizes = new int[N];
            help = new int[N];
            sets = N; //N个结点相互独立

            for (int i = 0; i < N; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        /**
         * 寻找i结点的头结点
         *
         * @return
         */
        private int find(int i) {
            int helpIndex = 0;
            while (i != parents[i]) {  //等于的时候，即到达i节点的头结点
                help[helpIndex++] = i; //help用于记录路径上(i-->i的代表结点)的所有结点
                i = parents[i];
            }
            //此处，i为代表结点
            //这个循环使路径上(i-->i的代表结点)的所有结点的父节点直接成为i的父亲结点——>扁平化
            helpIndex--; //helpIndex超过了1
            while (helpIndex >= 0) {
                parents[help[helpIndex]] = i;
                helpIndex--;
            }
            return i;
        }


        /**
         * 将 i 和 j 的两个集合合并
         */
        public void union(int i, int j) {
            int head1 = find(i);
            int head2 = find(j);
            //判断两个元素是不是属于一个集合中
            if (head1 != head2) {
                //将元素较少的集合合并至较多的集合中
                if (sizes[head1] >= sizes[head2]) {
                    sizes[head1] += sizes[head2];
                    parents[head2] = head1;
                } else {
                    sizes[head2] += sizes[head1];
                    parents[head1] = head2;
                }
                sets--; //结构中集合的数量要减少一个
            }
        }

        public int size() {
            return sets;
        }
    }
}
