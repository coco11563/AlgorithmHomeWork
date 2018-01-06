package PartII.Graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Chapter one Graph
 * Edge is only the connection of two Vertices
 */
public class Graph {
    private final int V;
    private int E = 0;
    private Bag<Integer> [] adj;
    public Graph(In in) {
        this.V = in.readInt();
        adj = new Bag[V];
        for (int i = 0 ; i < V ; i ++) {
            adj[i] = new Bag<>();
        }
        while (in.hasNextLine()) {
            int i = in.readInt();
            int j = in.readInt();
            addEdge(i,j);
        }
    }

    public Graph (int V) {
        this.V = V;
        adj = new Bag[V];
        for (int i = 0 ; i < V ; i ++) {
            adj[i] = new Bag<>();
        }
    }

    public void addEdge (int V, int W) {
        adj[V].add(W);
        adj[W].add(V);
        E ++;
    }

    public Iterable<Integer> adj(int V) {
        return adj[V];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Bag<Integer> Vertice : adj) {
            sb.append(i).append(":").append("\n");
            for (Integer j : Vertice) {
                sb.append("\t").append(i).append("-").append(j);
                sb.append("\n");
            }
            i ++;
            sb.append("\n");
        }
        return sb.toString();
    }

    public static int degree (Graph g, int v) {
        int degree = 0;
        if (g.V <= v) return -1;
        for (int w : g.adj(v)) {
            degree ++;
        }
        return degree;
    }
    public static int maxDegree(Graph g) {
        int max = 0;
        for (int v = 0; v < g.V() ; v ++) {
            if (degree(g, v) > max) {
                max = degree(g, v);
            }
        }
        return max;
    }

    public static double averageDegree(Graph g) {
        return 2.0 * g.E() / g.V();
    }
     public static int numberOfSelfLoop(Graph g) {
        int count = 0;
        for (int v = 0; v < g.V(); v ++) {
            for (int w : g.adj(v)) {
                if (w == v) count ++;
            }
        }
        return count / 2; // each loop count twice
     }
}
