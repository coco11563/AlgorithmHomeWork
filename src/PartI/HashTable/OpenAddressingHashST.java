package PartI.HashTable;

import edu.princeton.cs.algs4.In;

/**
 * 线性探测法 （开址法）
 * 性能依赖于N/M的比值，但意义有所不同，为了保证性能， N/M 要在 1/8到1/2之间，不允许被占满
 * M/2 stuff put spend ==>  3/2
 * full ===>  Sqt(pi * m / 8)
 *  Constant Search/Insert/Delete (Average Case)
 * 1.开放地址法：
 * 容易产生堆积问题；不适于大规模的数据存储；
 * 散列函数的设计对冲突会有很大的影响；
 * 插入时可能会出现多次冲突的现象，删除的元素是多个冲突元素中的一个，需要对后面的元素作处理，实现较复杂；结点规模很大时会浪费很多空间；
 * less wasted
 * better cache performance
 * 2.链地址法：
 * easy to implement delete
 * performance degrade graceful (性能退化缓慢)
 * less sensitive to poorly-designed hash function
 * 处理冲突简单，且无堆积现象，平均查找长度短；链表中的结点是动态申请的，适合构造表不能确定长度的情况；
 * 相对而言，拉链法的指针域可以忽略不计，因此较开放地址法更加节省空间。
 * 插入结点应该在链首，删除结点比较方便，只需调整指针而不需要对其他冲突元素作调整。
 *
 * 优化 ：
 * Double Hashing
 * Cukoo Hashing
 * @param <Key>
 * @param <Value>
 */
//Thread not safe
public class OpenAddressingHashST<Key, Value> {
    private int M = 97;
    private Key[] keys;
    private Value[] values;
    private int count = 0;
    public OpenAddressingHashST() {
//        this.M =
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }
    public OpenAddressingHashST(int n) {
        this.M = n;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }
    public void put(Key key, Value value) {
        if (size() >=  M / 2) { //full
            resize(2 * M);
        }
        int i;
        for (i = hash(key); keys[i] != null ; i = (i + 1) % M) {
            if (keys[i].equals(key)) break;
        }
        keys[i] = key;
        values[i] = value;
        count ++;
    }
    private void delete(Key x) {
        if (!contains(x)) return;
        int i = hash(x);
        while (!x.equals(keys[i])) i = (i + 1) % M;
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            count --;
            put(keyToRedo, valToRedo);
            i = ( i + 1 ) % M;
        }
        count --;
        if (count > 0 && count == M / 8) resize( M / 2 );
    }
    public Value get(Key key) {
        int i = hash(key);
        for (i = hash(key); keys[i] != null ; i = (i + 1) % M) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }
    public String getStatus() {
        StringBuilder status = new StringBuilder();
        int index = 1;
        for (Key n : keys) {
            status.append("\nChain No.").append(index);
            status.append(", Status is : ").append(n == null);
            index ++;
        }
        return status.toString();
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M; //防止为负
    }
    public int size() {
        return count;
    }

    private void resize(int cap) {
        OpenAddressingHashST<Key, Value> t;
        t = new OpenAddressingHashST<>(cap);
        for (int i = 0 ; i < M ; i ++) {
            if (keys[i] != null) {
                t.put(keys[i], values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
        M = t.M;
    }

    public static void main(String args[]) {
        OpenAddressingHashST<String, String> testMap = new OpenAddressingHashST<>();
        String filename = "C:\\Users\\coco1\\IdeaProjects\\AlgorithmHomeWork\\data\\PartI.Week_V\\input10K.txt";
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
