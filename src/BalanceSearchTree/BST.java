package BalanceSearchTree;

import edu.princeton.cs.algs4.Queue;

import java.util.List;

public class BST <Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        private Node left;
        private Node right;
        private Key key;
        private Value value;
        private int N;
        Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
        Node(Node n) {
            this.key = n.key;
            this.value = n.value;
            this.N = 1;
        }
        public void add() {
            this.N = N + 1;
        }
        public Node getRight() {
            return this.right;
        }
        public Node getLeft() {
            return this.left;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node s) {
        if (s == null) return 0;
        else return s.N;
    }


    public Value get(Key key) {
        return get(key, root);
    }

    private Value get(Key key, Node node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return get(key, node.getRight());
        } else if (cmp < 0) {
            return get(key, node.getLeft());
        } else {
            return node.value;
        }
    }

    public boolean contains(Key key) {
        return contains(key, root);
    }

    private boolean contains(Key key, Node node) {
        return get(key, node) != null;
    }
    private Node put(Key key, Value value, Node node) {
        if (node == null) return new Node(key, value, 1);
        else {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node.add(); //insert into this node's son
                node.left = put(key, value, node.left);
            } else if (cmp > 0) {
                node.add(); //insert into this node's son
                node.right = put(key, value, node.right);
            } else node.value = value;
            return node;
        }

    }

    public int rank(Key key) {
        return rank(key, root);
    }
    //
    private int rank(Key key, Node node) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return 1 + size(node.left) + rank(key, node.right);
        } else if (cmp < 0) {
            return rank(key, node.left);
        } else {
            return size(node.left);
        }
    }

    public int range(Key k1, Key k2) {
        return range(k1, k2, root);
    }

    private int range(Key k1, Key k2, Node root) {
        int cmp = k1.compareTo(k2);
        int ret = 0;
        Key bigger;
        if (cmp > 0) {
            bigger = k1;
            ret +=  rank(k1) - rank(k2);
        } else {
            bigger = k2;
            ret += rank(k2) - rank(k1);
        }
        if (contains(bigger)) ret += 1;
        return ret;
    }

    public Key floor(Key key) {
        Node x = floor(key, root);
        if (x == null) return null;
        else return x.key;
    }

    //get a key of node <= this BST
    //底
    private Node floor(Key key, Node node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key); // node compare to keys
        if (cmp == 0) return node;
        if (cmp < 0) return floor(key, node.left);
        Node t = floor(key, node.right);
        if (t != null) return t;
        else return node;
    }
    public void put(Key key, Value value) {
        root = put(key, value, root);
    }

    public Iterable<Key> keys () {
        Queue<Key> q = new Queue<>();
        inorder(q, root);
        return q;
    }

    private void inorder(Queue<Key> q, Node node) {
        if (node == null) return;
        inorder(q, node.left);
        q.enqueue(node.key);
        inorder(q, node.right);
    }

    private Node min(Node node) {
        if (node == null) return null;
        if (node.left != null) return min(node.left);
        else return node;
    }
        //delete part using Hibbard deletion
    public void deleteMin() {
        root = deleteMin(root);
    }

    public void delete(Key key) {
        root = delete(key, root);
    }

    public Node delete(Key key, Node node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = delete(key, node.right);
        } else if (cmp < 0) {
            node.left = delete(key, node.left);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null){
                return node.left;
            } else {
                Node r = node.right;
                Node l = node.left;
                node = new Node(min(r)); //避免
                node.left = l;
                node.right = deleteMin(r);
                node.N = size(node.left) + size(node.right) + 1;
                return node;
            }
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node == null) return null;
        if (node.left != null)  {
            Node left = node.left;
            if (left.left != null) deleteMin(left);
            else node.left = left.right;
        } else {
            return node.right;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node deleteMin2(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
    //如果root == null will cast null point
    public void deleteMin2() {
        root = deleteMin2(root);
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMin(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

     public static void main(String args[]) {
         BST<String, Integer> test = new BST<>();
         BST<String, Integer> test2 = new BST<>();
         BST<String, Integer> test3 = new BST<>();
         test.put("3",1);
         test.put("1",1);
         test.put("7",1);
         test.put("0",1);
         test.put("2",1);
         test.put("5",1);
         test.put("8",1);
         test.put("3",1);
         test.put("4",1);

         test2.put("3",1);
         test2.put("1",1);
         test2.put("7",1);
         test2.put("0",1);
         test2.put("2",1);
         test2.put("5",1);
         test2.put("8",1);
         test2.put("3",1);
         test2.put("4",1);

         test3.put("3",1);
         test3.put("1",1);
         test3.put("7",1);
         test3.put("0",1);
         test3.put("2",1);
         test3.put("5",1);
         test3.put("8",1);
         test3.put("3",1);
         test3.put("4",1);

         System.out.println("xm : " + test.get("xm"));
         System.out.println("rank 1 : " + test.rank("1"));
         System.out.println("rank 2 : " + test.rank("2"));
         System.out.println("floor 1 : " + test.floor("1"));
         System.out.println("floor 6 : " + test.floor("6"));
         System.out.println("range 0 - 8 : " + test.range("5", "8"));


         test.deleteMin();
         test.deleteMin();
         test.deleteMin();
         test.deleteMin();
         test.deleteMin();
         test.deleteMin();
         test.deleteMin();
         test.deleteMin();
         test.deleteMin();

         test2.deleteMin2();
         test2.deleteMin2();
         test2.deleteMin2();
         test2.deleteMin2();
         test2.deleteMin2();
         test2.deleteMin2();
         test2.deleteMin2();
         test2.deleteMin2();
         test.keys().forEach(System.out::print);
         System.out.println();
         test2.keys().forEach(System.out::print);
         System.out.println();
         test3.delete("1");
         test3.delete("10");
         test3.keys().forEach(System.out::print);

    }
}
