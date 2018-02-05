package PartII.Graph.Search;

import PartII.Graph.Graph;

public class ConponentConnectivity {
    private Graph graph;
    private boolean[] marked;
    private int[] index;
    private int n;
    public ConponentConnectivity(Graph g) {
        this.graph = g;
        n = g.V();
        marked = new boolean[n];
        index = new int[n];
        cc();
    }

    private void cc() {
        int groupIndex = 0;
        for (int i = 0 ; i < n ; i ++) {
            if (!marked[i]) {
                dfs(i, groupIndex);
            }
            groupIndex ++;
        }
    }

    private void dfs(int i, int index) {
        marked[i] = true;
        this.index[i] = index;
        for (int s : graph.adj(i)) {
            if (!marked[s]) dfs(s, index);
        }
    }

    public boolean isConnect(int a, int b) {
        return index[a] == index[b];
    }
    public static void main(String args[]) {
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        ConponentConnectivity p = new ConponentConnectivity(g);
        System.out.println(p.isConnect(0, 5));


    }
}
