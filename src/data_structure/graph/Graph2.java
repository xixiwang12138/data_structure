package data_structure.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph2 {
    private ArrayList<String> vertexList;  //存储顶点的集合
    private int[][] edges;  //二维数组，描述邻接矩阵
    private int edgesNum;   //
    private boolean[] isSelected;

    public Graph2(int vertexNum) {    //顶点的个数
        this.vertexList = new ArrayList<String>(vertexNum);
        this.edges = new int[vertexNum][vertexNum];
        this.isSelected = new boolean[vertexNum];
    }

    /**
     * 添加顶点
     */
    public void insertVertex(String vertex) {
        this.vertexList.add(vertex);
    }

    /**
     * 添加图的边
     */
    public void insertEdge(int x, int y, int w) {  //w为边的权值
        //无向图
        edges[x][y] = w;
        edges[y][x] = w;
        //边的数量
        ++edgesNum;
    }

    /**
     * 返回顶点的个数
     */
    public int getVertexSize() {
        return this.vertexList.size();
    }

    /**
     * 返回边的个数
     */
    public int getEdgesNum() {
        return this.edgesNum;
    }

    /**
     * 获取顶点的权值
     */
    public int getWeight(int x, int y) {
        return this.edges[x][y];
    }

    /**
     * 访问顶点的值
     */
    public String getValue(int index) {
        return this.vertexList.get(index);
    }

    /**
     * 输出邻接矩阵的图案
     */
    public void showEdges() {
        for (int[] arr : edges) {
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 给定一个顶点的索引，查找当前索引的第一个邻接点
     * 如果存在就返回索引位置
     * 如果不存在就返回-1
     */
    public int getFirstVertex(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 给定一个坐标，查找坐标下的第一个邻接顶点
     * 如果存在就返回顶点索引位置
     * 如果不存在就返回-1
     */
    public int getNextVertex(int x, int y) {
        for (int i = y + 1; i < vertexList.size(); i++) {
            if (edges[x][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历
     */
    public void dfs(boolean[] isSelected, int beginIndex) {
        System.out.println(getValue(beginIndex));
        isSelected[beginIndex] = true;//已经被访问
        //获取beginIndex后第一个邻接顶点的索引
        int nextIndex = getFirstVertex(beginIndex);
//        if (isSelected[nextIndex] || nextIndex == -1) {
//            return;            //不能返回，因为搜索还未结束
//        }
        while(nextIndex!=-1) {     //存在第一个邻接点，如果
            if(!isSelected[nextIndex]){
                dfs(isSelected,nextIndex);
            }
            nextIndex = getNextVertex(beginIndex,nextIndex);
        }
    }

    public void dfs(){
        for (int i = 0; i < getVertexSize(); i++) {
            if(isSelected[i]) {
                dfs(isSelected,i);
            }
        }
    }


}
