package PartI.HashTable;

/**
 * 假设J：均匀散列假设，我们使用的散列函数可以均匀并独立地将所有的键散布于0至M - 1的范围内
 * （不可能存在，因为不存在一个计算简单但又拥有一致性与均匀性的散列函数是不太可能的）
 * 命题K：在一张含有M条链表和N个键的散列表中，（在假设J成立的前提下）任意一条链表中的键的数量均在N/M的常数因子范围内的概率无限趋向于1
 * 性质L：在一张含有M条链表和N个键的散列表中，未命中查找和插入操作所需的比较次数为 N/M   《--  散列表算法的高性能并不需要散列函数完全符合假设J意义上的均匀性。
 *
 * ****散列表应用于对顺序不敏感的应用中****
 *
 * 优化 ： RESIZING
 * 优化 ： Two Probe Hashing  =》 两个Hash值以选择链较短的插入
 * 拉链法散列表 // 还有另外一个是Open Addressing
 */

import edu.princeton.cs.algs4.In;

public class SeparateChainingHashST<Key, Value> {
    private int M = 97;
    private Node[] st = new Node[M];
    private int count = 0;
    public SeparateChainingHashST() {
//        this.M =
    }
    public SeparateChainingHashST(int n) {
        this.M = n;
    }

    private static class Node {
        private Object Key;
        private Object Value;
        private Node next;

        Node(Object key, Object value, Node node) {
            this.Value = value;
            this.Key = key;
            this.next = node;
        }

        public Object getValue() {
            return Value;
        }

        public int size () {
            int i = 1;
            if (next == null) return i;
            return i + next.size();
        }

        public Node delete() {
            return next;
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M; //防止为负
    }

    public int size() {
        return count;
    }
    public int sizeAt (int i) {
        if (st[i] == null) return 0;
        else return st[i].size();
    }
    public void put(Key key, Value value) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.Key)) {x.Value = value; return;}
        }
        st[i] = new Node(key, value, st[i]);
        count ++;
    }

    public Value get(Key key) {
        int i = hash(key);
        if (st[i] == null) return null;
        for (Node x = st[i] ; x != null; x = x.next) {
            if (key.equals(x.Key)) {return (Value) x.getValue(); } //Java的不足之处 =- =
        }
        return null;
    }

    public String getStatus() {
        StringBuilder status = new StringBuilder();
        int index = 1;
        for (Node n : st) {
            status.append("\nChain No.").append(index);
            status.append(", Status is : ").append(sizeAt(index - 1));
            index ++;
        }
        return status.toString();
    }

    public void delete(Key key) {
        int i = hash(key);
        Node temp = null;
        int index = 0;
        for (Node x = st[i] ; x != null; x = x.next) {
            if (x.Key.equals(key)) { //is target
                if (index == 0) {//first
                    st[i] = x.delete();
                    count --;
                    return;
                } else { //not first
                    temp.next = x.delete();
                    count --;
                    return;
                }
            } else { //not target
                temp = x;
            }
            index ++;
        }
    }
    public static void main(String args[]) {
        SeparateChainingHashST<String, String> testMap = new SeparateChainingHashST<>();
        String filename = "/Users/xgxyi03/IdeaProjects/AlgorithmHomeWork/data/PartI/Week_V/input10K.txt";
        In in = new In(filename);
        while (!in.isEmpty()) {
            String x = in.readString();
            String y = in.readString();
            testMap.put(x, y);
        }
        in.close();
        In in2 = new In(filename);
        System.out.print(testMap.getStatus());
        while (!in2.isEmpty()) {
            String x = in2.readString();
            String y = in2.readString();
            testMap.delete(x);
        }
        System.out.print(testMap.getStatus());
    }
}
