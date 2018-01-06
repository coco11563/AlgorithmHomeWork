package PartII.Graph;
//Decouple the graph data type from graph processing

import edu.princeton.cs.algs4.Stack;

import java.util.TreeSet;

public class Paths {
    private Graph graph;
    private int vertice;
    public Paths(Graph graph, int s) {
        this.graph = graph;
        this.vertice = s;
    }

    public boolean hasPathTo(int v) {
        return hasPath(vertice, v, new TreeSet<>(), new Stack<>());
    }
 // || 为满足第一个条件就不再判断
    private boolean hasPath (int v1, int v2, TreeSet<Integer> i, Stack<Integer> path) {
        if (i.contains(v1)) return false;
        if (v1 == v2) {
            return true;
        }
        i.add(v1);
        for (Integer v : graph.adj(v1)) {
            if (i.contains(v)) continue;
            if (hasPath(v, v2, i, path)) {
                path.push(v);
                return true;
            }
        }
        return false;
    }
    public Iterable<Integer> pathTo (int i) {
        Stack<Integer> path = new Stack<>();
        hasPath(vertice, i, new TreeSet<>(), path);
        path.push(vertice);
        return path;
    }
    public static void main(String args[]) {
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        Paths p = new Paths(g, 0);
        for (int i : p.pathTo(5)) {
            System.out.println(i + 1);
        }

    }
}
