package data_structure.graph;

import data_structure.queue.LinkQueue;
import data_structure.stack.LinkStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 基于API设计的图的实现（都是基于有向图）
 */
public class Graph3 {
    public HashMap<Integer, Node> nodes;   //Integer是某一结点的权值（value）
    public HashSet<Edge> edges;

    public Graph3() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    /**
     * 宽度优先遍历、广度优先遍历
     *
     * @param node 以API设计方式构建的图对象的任意一个结点
     */
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        //非递归方式，使用队列
        LinkQueue queue = new LinkQueue();
        //用于判断是否已经遍历过该结点， 遍历到结点就要加入set集合
        HashSet<Node> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = (Node) queue.poll();
            //取出结点就处理
            System.out.println(cur.value);
            //对当前的结点的所有邻接点进行处理
            //如果没有被遍历过，就加入队列和set标志集合
            for (Node nextNode : cur.nexts) {
                if (!set.contains(nextNode)) {
                    queue.offer(nextNode);
                    set.add(nextNode);
                }
            }
        }
    }

    public  static void dfs(Node node){
        if(node==null){
            return;
        }
        //深度优先遍历使用栈(模拟递归)
        LinkStack stack = new LinkStack();
        //同样需要使用set集合来判断是否已经遍历过
        HashSet<Node> set = new HashSet<>();
        //压栈和加入set一般同时进行
        stack.push(node);
        set.add(node);
        //入栈即打印（处理）
        System.out.println(node.value);
        while(!stack.isEmpty()){
            Node cur = (Node) stack.pop();
            for(Node nextNode : cur.nexts){
                if(!set.contains(nextNode)){
                    //又把弹出的cur压回栈
                    //这个栈表示的是 从栈底到栈顶是深度优先遍历的路径
                    stack.push(cur);
                    stack.push(nextNode);
                    set.add(nextNode);
                    System.out.println(nextNode.value);
                    break;
                }
            }
        }
    }
}

class Node {
    public int value;  //结点的编号，可以是其他要存储的类型
    public int in;   //入度
    public int out;    //出度
    public ArrayList<Node> nexts;  //当前结点的邻接顶点，只包括指出的部分(直接邻居)
    public ArrayList<Edge> edges;  //从当前结点出发的边的集合

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<Edge>();
    }
}

class Edge {
    public int weight; //边的权值
    public Node from;    //起始点
    public Node to;  //终点

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}

class GraphGenerator {
    /**
     * 将 [weight,from结点上面的值,to结点上面的值] 这样的图的存储结构转换为基于API设计的结构
     * matrix 代表存储了每一个边
     * N*3 的矩阵，其中N是边的个数
     */

    public static Graph3 createGraph(int[][] matrix) {
        Graph3 graph = new Graph3();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            if (graph.nodes.containsKey(from)) {   //如果顶点哈希表中不含有起始点，加入
                graph.nodes.put(from, new Node(from));  //使用new创建新的结点对象加入
            }
            if (graph.nodes.containsKey(to)) {   //如果顶点哈希表中不含有起始点，加入
                graph.nodes.put(to, new Node(to));
            }

            //获取起始点终点的结点对象，创建对应该边的对象
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);

            //在fromNode 初始结点的数据域发生对应的改变
            fromNode.nexts.add(toNode);
            fromNode.out++;
            fromNode.in++;
            fromNode.edges.add(newEdge);
            //在图的数据域中发生改变
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
