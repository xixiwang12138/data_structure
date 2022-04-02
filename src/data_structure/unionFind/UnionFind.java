package data_structure.unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 基于哈希表的方式实现
 */
public class UnionFind {
    public static class Node<V> {   //V是泛型
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes; //存放具体的数值与其包装结点对应关系的表
        public HashMap<Node<V>, Node<V>> parents; //存放结点之间的父子关系
        public HashMap<Node<V>, Integer> sizeMap; //Node<V>必须是代表结点，Integer存放该集合的数量

        public UnionSet(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();

            for (V cur : values) {
                Node<V> node = new Node<>(cur);  //包装
                nodes.put(cur,node);
                parents.put(node,node);  //自己的父亲指向自己
                sizeMap.put(node,1); //每一个都是独立的集合，元素个数都是1
            }
        }
        public Node<V> findFather(Node<V> cur){
            Stack<Node<V>> path = new Stack<>();
            while(cur != parents.get(cur)){
                path.push(cur);
                cur = parents.get(cur);
            }
            while(!path.empty()){
                Node<V> node = path.pop();
                parents.put(node,cur);
            }
            return cur;
        }

        public  boolean isSameSet(V a , V b){
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a , V b){
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if(aHead != bHead){   //不在一个集合当中
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                //找到两个头结点的集合数量较大较小的那一个
                Node<V> big = aSize >= bSize ? aHead : bHead ;
                Node<V> small = big == aHead ? bHead : aHead ;
                parents.put(small,big);
                sizeMap.put(big,aSize+bSize);
                sizeMap.remove(small);
            }
        }



    }
}
