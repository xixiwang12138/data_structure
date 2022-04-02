package data_structure.graph;

public class Test {
    public static void main(String[] args) {
        Graph2 g = new Graph2(5);
        String[] vertexes = {"a","b","c","d","e"};
        for(String s : vertexes){
            g.insertVertex(s);
        }
        g.insertEdge(0,1,1);
        g.insertEdge(0,2,1);
        g.insertEdge(1,2,1);
        g.insertEdge(1,3,1);
        g.insertEdge(1,4,1);

        g.showEdges();
        boolean[] is = new boolean[5];
        g.dfs();
    }
}
