package Week_Five_Home_Work;

//85
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.List;

public class KdTree {
    private Node root;
    public boolean isEmpty() {                   // is the set empty?
        return size() == 0;
    }
    public int size() {                        // number of points in the set
        if (root == null) {
            return 0;
        }
        return root.size;
    }
    public void insert (Point2D p) {             // add the point to the set (if it is not already in the set)
        root = insertLR(p, root);
    }
    private Node insertLR (Point2D p, Node root) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return new Node(p);
        if (root.equal(p)) return root;
        if (!root.isLeftThan(p)) { // root is on the right
            root.size += 1;
            root.left = insertBU(p, root.left);
        } else {
            root.size += 1;
            root.right = insertBU(p, root.right);
        }
        return root;
    }

    private Node insertBU (Point2D p, Node root) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return new Node(p);
        if (root.equal(p)) return root;
        if (!root.isBelowThan(p)) { // root is on the upside
            root.size += 1;
            root.left = insertLR(p, root.left);
        } else {
            root.size += 1;
            root.right = insertLR(p, root.right);
        }
        return root;
    }
    public boolean contains(Point2D p) {          // does the set contain point p?
        if (p == null) throw new IllegalArgumentException();
        return searchLR(p, root);
    }

    private boolean searchLR(Point2D p, Node root) {
        if (root == null) return false;
        if (root.equal(p)) return true;
        if (root.isLeftThan(p)) { // root is on the upside
            return searchBU(p, root.right);
        } else {
            return searchBU(p, root.left);
        }
    }

    private boolean searchBU(Point2D p, Node root) {
        if (root == null) return false;
        if (root.equal(p)) return true;
        if (root.isBelowThan(p)) { // root is on the upside
            return searchLR(p, root.right);
        } else {
            return searchLR(p, root.left);
        }
    }

    public void draw() {                     // draw all points to standard draw
        draw(root);
    }

    private void draw(Node root) {
        if (root == null) return;
        root.getPoint().draw();
        draw(root.left);
        draw(root.right);
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        if (rect == null) throw new IllegalArgumentException();
        return rangeLR(rect, root);
    }

    private List<Point2D> rangeLR(RectHV rect, Node root) {
        List<Point2D> li = new LinkedList<>();
        if (root == null) return li;
        if (rect.contains(root.getPoint())) {
            li.add(root.getPoint());
            li.addAll(rangeBU(rect, root.left));
            li.addAll(rangeBU(rect, root.right));
        } else {
            if ( root.getPoint().x() < rect.xmax() && root.getPoint().x() > rect.xmin()) {
                li.addAll(rangeBU(rect, root.left));
                li.addAll(rangeBU(rect, root.right));
            } else if (rect.xmax() < root.getPoint().x()) { //rect is on the left
                li.addAll(rangeBU(rect, root.left));
            } else {
                li.addAll(rangeBU(rect, root.right));
            }
        }
        return li;
    }

    private List<Point2D> rangeBU(RectHV rect, Node root) {
        List<Point2D> li = new LinkedList<>();
        if (root == null) return li;
        if (rect.contains(root.getPoint())) {
            li.add(root.getPoint());
            li.addAll(rangeLR(rect, root.left));
            li.addAll(rangeLR(rect, root.right));
        } else {
            if ( root.getPoint().y() < rect.ymax() && root.getPoint().y() > rect.ymin()) {
                li.addAll(rangeLR(rect, root.left));
                li.addAll(rangeLR(rect, root.right));
            } else if (rect.ymax() < root.getPoint().y()) { //rect is on the left
                li.addAll(rangeLR(rect, root.left));
            } else {
                li.addAll(rangeLR(rect, root.right));
            }
        }
        return li;
    }



    public Point2D nearest(Point2D p) {// a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new IllegalArgumentException();
        return nearestLR(p, root);
    }

    private Point2D nearestLR(Point2D p, Node root) {
        if (root == null) return null;
        if (root.getPoint().equals(p)) return root.getPoint();
        if (root.left == null && root.right == null) return  root.getPoint();
        int check = checkNeed(p, root, true);
        switch (check) {
            case 1 : {
                Point2D p2 = nearestBU(p, root.right);
                return getNearest(root.getPoint(), p2, p);
            }
            case -1 : {
                Point2D p2 = nearestBU(p, root.left);
                return getNearest(root.getPoint(), p2, p);
            }
            case 0 : {
                Point2D p2 = nearestBU(p, root.right);
                Point2D p3 = nearestBU(p, root.left);
                return getNearest(root.getPoint(), getNearest(p2, p3, p), p);
            }
            default: throw new IllegalArgumentException();
        }
    }

    private Point2D nearestBU(Point2D p, Node root) {
        if (root == null) return null;
        if (root.getPoint().equals(p)) return root.getPoint();
        if (root.left == null && root.right == null) return  root.getPoint();
        int check = checkNeed(p, root, false);
        switch (check) {
            case 1 : {
                Point2D p2 = nearestLR(p, root.right);
                return getNearest(root.getPoint(), p2, p);
            }
            case -1 : {
                Point2D p2 = nearestLR(p, root.left);
                return getNearest(root.getPoint(), p2, p);
            }
            case 0 : {
                Point2D p2 = nearestLR(p, root.right);
                Point2D p3 = nearestLR(p, root.left);
                return getNearest(root.getPoint(), getNearest(p2, p3, p), p);
            }
            default: throw new IllegalArgumentException();
        }
    }
    private Point2D getNearest (Point2D p1, Point2D p2, Point2D desti) {
        if (p1 == null) {
            if (p2 == null) return null;
            else return p2;
        } else {
            if (p2 == null) return p1;
            else {
                Double d1 = p1.distanceTo(desti);
                Double d2 = p2.distanceTo(desti);
                if (d1 > d2) return p2;
                else return p1;
            }
        }
    }
    private int checkNeed (Point2D p, Node root, boolean isLeft) {
        if (p == null || root == null || root.equal(p) || (root.left == null && root.right == null)) throw new IllegalArgumentException();
        double checkVal1;
        double checkVal2;
        if (isLeft) { //LR
            checkVal1 = p.x();
            checkVal2 = root.getPoint().x();
        } else {  //BU
            checkVal1 = p.y();
            checkVal2 = root.getPoint().y();
        }
        if (checkVal1 < checkVal2) {//left or below
            if (root.left == null) return 1; //check right
            if (root.left.getPoint().distanceTo(p) < checkVal2 - checkVal1) return -1; // check left
            else return 0; // check both
        } else { //right or upside
            if (root.right == null) return -1; //check left
            if (root.right.getPoint().distanceTo(p) < checkVal2 - checkVal1) return 1; // check right
            else return 0; // check both
        }
    }
    public static void main(String[] args) {
    }

    private class Node {
        Point2D point2D;
        Node left;
        Node right;
        int size;
        Node(Point2D p) {
            this.point2D = p;
            size = 1;
        }

        private Point2D getPoint() {
            return point2D;
        }

        void setRight (Node x) {
            this.right = x;
        }

        void setLeft (Node x) {
            this.left = x;
        }

        boolean isLeftThan (Point2D x) {
            if (x == null) throw new IllegalArgumentException();
            return point2D.x() < (x.x());
        }

        boolean isBelowThan (Point2D x) {
            if (x == null) throw new IllegalArgumentException();
            return point2D.y() < (x.y());
        }

        boolean equal(Point2D x) {
            if (x == null) throw new IllegalArgumentException();
            return x.y() == (this.getPoint().y()) && x.x() == (this.getPoint().x());
        }
    }
}



