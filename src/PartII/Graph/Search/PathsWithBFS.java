package PartII.Graph.Search;

import PartII.Graph.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
//用queue代替系统栈来迭代
public class PathsWithBFS {
    private Graph graph;
    private int vertice;
    private boolean[] marked;
    private int[] edgeTo;
    private Queue<Integer> st = new Queue<>();
    public PathsWithBFS(Graph graph, int s) {
        this.graph = graph;
        this.vertice = s;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        st.enqueue(s);
        edgeTo[s] = s;
        bfs();
    }

    private void bfs () {
        while (!st.isEmpty()) {
            int now = st.dequeue();
            if (marked[now]) continue;
            marked[now] = true;
            for (int sl : graph.adj(now)) {
                if (marked[sl]) continue;
                edgeTo[sl] = now;
                st.enqueue(sl);
            }
        }
    }

    private void bfs_ (int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int now = queue.dequeue();
            for (int sl : graph.adj(now)) {
                if (marked[sl]) continue;
                edgeTo[sl] = now;
                marked[sl] = true;
                queue.enqueue(sl);
            }
        }
    }

    public boolean havePath (int i) {
        return marked[i];
    }

    public Iterable<Integer> pathTo (int i) {
        if (!havePath(i)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = i; x != vertice ; x = edgeTo[x]) {
            path.push(x);
        }
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
        PathsWithBFS p = new PathsWithBFS(g, 0);
        for (int i : p.pathTo(5)) {
            System.out.println(i + 1);
        }

    }
}
