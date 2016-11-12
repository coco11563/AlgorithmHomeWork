package BalanceSearchTree;

/**
 * Created by coco1 on 2016/11/9.
 * left-leaning RedBlack BalanceSearchTree (Guibas-Sedgewick 1979 and Sedgewick 2007)
 *
 * represent 2-3 Tree as a BSTs
 * Use "internal" left-leaning links as "glue" for 3-nodes
 *
 * Use red link like internal link to a small one
 *
 * Use black links connect 3-nodes and 2-nodes
 *
 * 没有任何一个节点被两个red Link 链接
 *
 * 红色连接是用于给3-Node内部连接
 *
 * 红黑树有着完美的黑色连接平衡
 */
public class RedBlackBST<Val, Key extends Comparable> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    public RedBlackBST() {
        root = new Node();
    }
    private class Node{
        Node (){

        }
        Node(Key key, Val val, boolean color) {
            this.color = color;
            this.key = key;
            this.val = val;
        }
        boolean color; //key point
        Val val;
        Key key;
        Node left;
        Node right;
    }
    public Val get(Key key) {
        Node x = root;
        while(x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            if (cmp > 0) x = x.right;
            if (cmp == 0) return x.val;
        }
        return null;
    }
    private Node rotateLeft(Node h) {
        assert isRed(h.right); //确认是right leaning
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left); //确认是right leaning
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void colorFlip (Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.right.color = BLACK;
        h.left.color = BLACK;
    }
    private boolean isRed(Node x) {
        //所有的空点全是false的
        return x != null && x.color == RED;
    }

    private Node put(Node h, Key key, Val val) {
        if (h == null) return new Node(key, val, RED);
        int cmp = key.compareTo(h.key);

        if (cmp < 0) h.left = put(h.left, key, val);
        else  if (cmp > 0) h.right = put(h.right, key, val);
        else if (cmp == 0) h.val = val;

        if (isRed(h.right) && !isRed(h.left)) {h = rotateLeft(h);} //lean left
        if (isRed(h.left) && isRed(h.left.left)) {h = rotateRight(h);} //balance 4-Node
        if (isRed(h.left) && isRed(h.right)) {colorFlip(h);} //split 4-node

        return h;
    }
}
