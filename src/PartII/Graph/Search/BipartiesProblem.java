package PartII.Graph.Search;

import PartII.Graph.Graph;

public class BipartiesProblem {
    private Graph graph;
    private boolean[] color;
    private boolean[] marked;
    private boolean biparties;
    private int N;

    public BipartiesProblem(Graph g) {
        this.graph = g;
        this.color = new boolean[g.V()];
        this.marked = new boolean[g.V()];
        this.N = g.V();
        check();
    }

    private void check() {
        for (int i = 0 ; i < N ; i ++) {
            if (!marked[i])
                dfs(i, true);
        }
    }

    private void dfs(int i, boolean color) {
//        this.color[i] = color;
//        for (int s : graph.adj(i)) {
//            if (!marked[s])
//        }
    }

    public boolean isBiparties() {
        return biparties;
    }
}
