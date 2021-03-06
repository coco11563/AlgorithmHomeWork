package Week_Four_Home_Work;

import edu.princeton.cs.algs4.Stack;
import java.util.Arrays;
/**
 * Created by coco1 on 2016/10/10.
 *
 */
public class Board {
    private int[] blocks;
    private int dimension;
    private int hamming = 0;
    private int manhattan = 0;
    private int blank_row = 0; // 行
    private int blank_col = 0; //列
    private int way = 0; //表示方向，1为左，2为右，3为上，4为下；以保证移动不会移动到之前走过的地方
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {// construct a board from an n-by-n array of blocks
        dimension = blocks.length;
        int len = dimension * dimension;
        this.blocks = new int[len];
        for (int i = 0 ; i < dimension ; i ++) {
            for (int j = 0 ; j < dimension ; j ++) {
                int currentValue = blocks[i][j];
                this.blocks[i * dimension + j] = currentValue;
                if (currentValue != 0) {
                    if (currentValue != ((i * dimension) + j + 1)) {
                        hamming++;
                    }
                    int col = (currentValue - 1) % dimension;
                    int row = (currentValue - col - 1) / dimension;
                    manhattan += (((col > j) ? (col - j) : (j - col)) + ((row > i) ? (row - i) : (i - row)));
                }
            }
        }
    }
    public int dimension() {// board dimension n
        return dimension;
    }
    public int hamming() { // number of blocks out of place
        return hamming;
    }
    public int manhattan() { // sum of Manhattan distances between blocks and goal
        return manhattan;
    }
    public boolean isGoal() {// is this board the goal board?
        return hamming == 0;
    }
    public Board twin() {// a board that is obtained by exchanging any pair of blocks
        int[][] twin = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            System.arraycopy(blocks, (i * dimension), twin[i], 0, dimension);
        }
        if ((twin[0][0] == 0) || (twin[0][1] == 0)) {//真的是随便换一下啊
            exch(twin, 1, 0, 1, 1);
        } else {
            exch(twin, 0, 0, 0, 1);
        }
        return new Board(twin);
    }
    public boolean equals(Object y) {// does this board equal y?
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return Arrays.equals(this.blocks, that.blocks);
    }

    public Iterable<Board> neighbors() {// all neighboring boards
        Board bo;
        Stack<Board> Board_Stack = new Stack<>();
        int[][] clone = new int[dimension][dimension];
        int n = 0;
        for (int i = 0 ; i < dimension ; i ++) {
            for (int j = 0 ; j < dimension ; j ++) {
                clone[i][j] = this.blocks[i * dimension + j];
                if (clone[i][j] == 0) {
                    blank_row = i;
                    blank_col = j;
                }
            }
        }

        if (blank_col != 0 && this.way != 1){//列不是最左
            exch(clone, blank_row, blank_col - 1, blank_row, blank_col);
            bo = new Board(clone);
            bo.way = 1;
            Board_Stack.push(bo);//改变方向为1
            exch(clone, blank_row, blank_col, blank_row, blank_col - 1);
        }
        if (blank_col != dimension - 1 && this.way != 2){ //列不是最右
            exch(clone, blank_row, blank_col + 1, blank_row, blank_col);
            bo = new Board(clone);
            bo.way = 2;
            Board_Stack.push(bo);//改变方向为2
            exch(clone, blank_row, blank_col, blank_row, blank_col + 1);
        }
        if (blank_row != dimension - 1 && this.way != 3) {//行不是最下
            exch(clone, blank_row + 1, blank_col, blank_row, blank_col);
            bo = new Board(clone);
            bo.way = 3;
            Board_Stack.push(bo);//改变方向为3
            exch(clone, blank_row + 1, blank_col, blank_row, blank_col);
        }
        if (blank_row != 0 && this.way != 4){//行不是最上
            exch(clone, blank_row - 1, blank_col, blank_row, blank_col);
            bo = new Board(clone);
            bo.way = 4;
            Board_Stack.push(bo);//改变方向为4
            exch(clone, blank_row, blank_col, blank_row - 1, blank_col);
        }
        return Board_Stack;
    }

    public String toString() { // string representation of this board (in the output format specified below)
        StringBuilder sb = new StringBuilder();
        int len = blocks.length / dimension;
        sb.append(len + "\n");
        for (int i = 0 ; i < blocks.length ; i ++) {
            sb.append(String.format("%2d",blocks[i]));
            sb.append(" ");
            if ((i + 1) % dimension == 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    private void exch(int[][] a, int i, int j, int k, int l) {
        int temp = a[k][l];
        a[k][l] = a[i][j];
        a[i][j] = temp;
    }


    public static void main(String[] args) {// unit tests (not graded)

    }
}
