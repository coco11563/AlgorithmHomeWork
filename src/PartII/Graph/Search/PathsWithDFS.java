package PartII.Graph.Search;
//Decouple the graph data type from graph processing

import PartII.Graph.Graph;
import edu.princeton.cs.algs4.Stack;

import java.util.TreeSet;

public class PathsWithDFS {
    private Graph graph;
    private int vertice;
    public PathsWithDFS(Graph graph, int s) {
        this.graph = graph;
        this.vertice = s;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        dfs(graph, s);
    }

    public boolean hasPathTo(int v) {
        return hasPath(vertice, v, new TreeSet<>(), new Stack<>());
    }

 // || 为满足第一个条件就不再判断
    //使用TreeSet去实现判定点是否被浏览
    //使用Stack去记录路由
    private boolean hasPath (int v1, int v2, TreeSet<Integer> i, Stack<Integer> path) {
        if (i.contains(v1)) return false;
        if (v1 == v2) {
            return true;
        }
        i.add(v1);
        for (Integer v : graph.adj(v1)) {
            if (i.contains(v)) continue;
            if (hasPath(v, v2, i, path)) {
                path.push(v); //内部递归 寻找该点后续
                return true;
            }
        }
        return false;
    }

    //aLGORITHM 书上方法
    //在记录路由时可以很容易想到，如果需要进行多次搜索，下一次搜索可以建立在之前搜索的基础上，我们只需要记录上一次搜索的结果，如已知1->5， 5->6，我们可以得到1->6而不需要再次全体搜索
    //如何记录路由成为一个问题
    private boolean[] marked;
    private int[] edgeTo;
    public void dfs (Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
                edgeTo[w] = v;
            }
        }
    }
    public boolean havePath (int i) {
        return marked[i];
    }
    public Iterable<Integer> pathToN (int i) {
        if (!havePath(i)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = i; x != vertice ; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(vertice);
        return path;
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
        PathsWithDFS p = new PathsWithDFS(g, 0);
        for (int i : p.pathTo(5)) {
            System.out.println(i + 1);
        }

    }
}
