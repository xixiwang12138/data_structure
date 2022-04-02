package data_structure.graph;


import data_structure.queue.LinkQueue;

/**
 * 基于邻接表的图的实现
 */
public class Graph {
    private int v;  //顶点的数量
    private int E;        //边的数量
    private LinkQueue[] adj;  //邻接表,数组中的元素为队列

    public Graph(int v) {
        this.v = v;
        this.E = 0;
        this.adj = new LinkQueue[v];
        for(LinkQueue queue : adj){
            queue = new LinkQueue();
        }
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return E;
    }

    public void getEdge(int v,int w){
        //在无向图中，w、v在两个队列中同时出现
        adj[v].offer(w);
        adj[w].offer(v);
        E++;
    }
    //获取和顶点v相邻的所有顶点队列
    public LinkQueue adj(int v){
        return adj[v];
    }

    //深度优先搜索
    public static void dfs(Graph graph, int v){

    }
}
