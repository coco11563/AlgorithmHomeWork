package GeoMetricApplications;

import java.util.ArrayList;
import java.util.List;

public class KDTree2<A extends Comparable<A>> {
    private static final int TYPE_LR = 0;
    private static final int TYPE_UB = 1;
    private node root;
    public KDTree2 (node n1) {
        this.root = n1;
    }
    public KDTree2 () {
        this.root = null;
    }
    class node {
        A Xis;
        A Yis;
        node left;
        node right;
        node (A x, A y) {
            this.Xis = x;
            this.Yis = y;
        }
        node (node x) {
            this.Xis = x.Xis;
            this.Yis = x.Yis;
        }
        void setRight (node x) {
            this.right = x;
        }

        void setLeft (node x) {
            this.left = x;
        }
        boolean isLeft (node x) {
            assert x != null;
            return this.Xis.compareTo(x.Xis) < 0;
        }
        boolean isBelow (node x) {
            assert x != null;
            return this.Yis.compareTo(x.Yis) < 0;
        }
        boolean equal(node x) {
            assert x!= null;
            return x.Yis.compareTo(this.Yis) == 0 && x.Xis.compareTo(this.Xis) == 0;
        }
    }
    class grid {
        node leftUpper;
        node rightBelower;
        A left;
        A right;
        A upper;
        A belower;
        grid (A a, A b, A c, A d) {
            new grid(new node(a, b), new node(c, d));
        }
        grid (node a, node b) {
            if (a.isLeft(b)) {left = a.Xis; right = b.Xis;}
            else {left = b.Xis; right = a.Xis;}
            if (a.isBelow(b)) {belower = a.Yis; upper = b.Yis;}
            else {belower = b.Yis; upper = a.Yis;}
            leftUpper = new node(left, upper);
            rightBelower = new node(right, belower);
        }
        boolean include (node n) {
            assert n != null;
            return n.isLeft(rightBelower) && n.isBelow(leftUpper);
        }
        boolean isLeft (node n) {
            assert n != null;
            return n.isLeft(leftUpper);
        }
        boolean isBelow (node n) {
            assert n!= null;
            return n.isBelow(rightBelower);
        }
    }
    public void insert (A x, A y) {
        insert(new node(x, y));
    }
    private void insert (node x) {
        assert x != null;
        if (root == null) this.root = new node(x);
        else insertLR(x, root);
    }

    private void insertLR (node x, node root) {
        if (root == null) root = x;
        if (x.equal(root)) return;
        if (root.isLeft(x)) insertBU(x, root.right);
        else insertBU(x, root.left);
    }

    private void insertBU (node x, node root) {
        if (root == null) root = x;
        if (x.equal(root)) return;
        if (root.isBelow(x)) insertLR(x, root.right);
        else insertLR(x, root.left);
    }
    public List<node> search (A a, A b, A c, A d) {
        return search(new grid(a, b, c, d));
    }
    private List<node> search (grid g) {
        return searchLR(g, root);
    }

    private List<node> searchLR(grid g, node root) {
        List<node> li = new ArrayList<>();
        if (g.include(root)) {
            li.add(root);
            li.addAll(searchBU(g, root.right));
            li.addAll(searchBU(g, root.left));
        } else if (g.isLeft(root)) {
            li.addAll(searchBU(g, root.left));
        } else {
            li.addAll(searchBU(g, root.right));
        }
        return li;
    }
    private List<node> searchBU(grid g, node root) {
        List<node> li = new ArrayList<>();
        if (g.include(root)) {
            li.add(root);
            li.addAll(searchLR(g, root.right));
            li.addAll(searchLR(g, root.left));
        } else if (g.isBelow(root)) {
            li.addAll(searchLR(g, root.left));
        } else {
            li.addAll(searchLR(g, root.right));
        }
        return li;
    }

    public static void main(String args[]) {
        KDTree2<Double> kd1 = new KDTree2<>();
        kd1.insert(-1d,2d);
        kd1.insert(-1d,0d);
        kd1.insert(1d,1d);
        kd1.insert(0d,0d);
        kd1.insert(1.5d,2d);
        kd1.insert(3d,3d);
        kd1.insert(0d,-1d);
//        kd1.insert(-1d,2d);
//        kd1.insert(-1d,2d);
//        kd1.insert(-1d,2d);
        kd1.search(4d, 4d, 1.5d,1.5d).forEach(e -> {
            System.out.println(e.Xis + "," + e.Yis);
        });
    }
}
