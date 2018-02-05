package PartII.Graph.Frog;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FrogTravelGraph implements Serializable{
    private static final long serialVersionUID = -1L;
    private Map<Integer, Node> graphMap = new HashMap<>();
    private int V = 0;
    private int E = 0;
    private Map<String, Integer> weight = new HashMap<>();
    private Set<Integer>[] adj;

    public FrogTravelGraph() {
        adj = new HashSet[V];
        for (int i = 0 ; i < V ; i ++) {
            adj[i] = new HashSet<>();
        }
    }

    public void AddNode(Node n) {
        V += 1;
        graphMap.put(V, n);
        Set<Integer>[] adj_new = new HashSet[V];
        for (int i = 0 ; i < V ; i ++) {
            adj_new[i] = new HashSet<>();
        }
        System.arraycopy(adj, 0, adj_new, 0, adj.length);
        adj = adj_new;
    }

    public void addEdge (int V, int W, int weight) {
        if (this.weight.containsKey(V + "-" + W) || this.weight.containsKey(W + "-" + V)) return;
        adj[V].add(W);
        adj[W].add(V);
        this.weight.put(V + "-" + W, weight);
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
        for (Set<Integer> Vertice : adj) {
            sb.append(graphMap.get(i).getP_id()).append(":").append("\n");
            for (Integer j : Vertice) {
                sb.append("\t").append(graphMap.get(i).getP_id()).append("-").append(graphMap.get(j).getP_id());
                sb.append("\n");
            }
            i ++;
            sb.append("\n");
        }
        return sb.toString();
    }
    public static int degree (FrogTravelGraph g, int v) {
        int degree = 0;
        if (g.V <= v) return -1;
        for (int w : g.adj(v)) {
            degree ++;
        }
        return degree;
    }
    public static int maxDegree(FrogTravelGraph g) {
        int max = 0;
        for (int v = 0; v < g.V() ; v ++) {
            if (degree(g, v) > max) {
                max = degree(g, v);
            }
        }
        return max;
    }

    public static double averageDegree(FrogTravelGraph g) {
        return 2.0 * g.E() / g.V();
    }

    public static int numberOfSelfLoop(FrogTravelGraph g) {
        int count = 0;
        for (int v = 0; v < g.V(); v ++) {
            for (int w : g.adj(v)) {
                if (w == v) count ++;
            }
        }
        return count / 2; // each loop count twice
    }

    public static void saveGraph(FrogTravelGraph ftg, String filePath) throws IOException {
        File f = new File(filePath);
        if (f.isDirectory()) {
            throw new FileNotFoundException();
        }
        if (f.exists()) {
            f.delete();
        }
        if (!f.exists()) {
            f.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oo = new ObjectOutputStream(fos);
        oo.writeObject(ftg);
        oo.close();
    }

    public static FrogTravelGraph readGraph (String filePath) throws IOException, ClassNotFoundException {
        File f = new File(filePath);
        if (!f.exists() || !f.isFile())
            throw new FileNotFoundException();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        return (FrogTravelGraph) ois.readObject();
    }
}
