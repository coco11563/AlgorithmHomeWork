package Week_Four_Home_Work.submit;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;


public class Board {
    private int ngrid;
    private char[] board;
    private int hammingCache;
    private int manhattanCache;

    public Board(int[][] blocks) {
        ngrid = blocks.length;

        if (ngrid < 2) {
            throw new NullPointerException();
        }

        board = new char[ngrid * ngrid];
        hammingCache = 0;
        manhattanCache = 0;

        for (int i = 0; i < ngrid; i++) {
            for (int j = 0; j < ngrid; j++) {
                int currentValue = blocks[i][j];
                board[(i * ngrid) + j] = (char) currentValue;

                if (currentValue != 0) {
                    if (currentValue != ((i * ngrid) + j + 1)) {
                        hammingCache++;
                    }

                    int col = (currentValue - 1) % ngrid;
                    int row = (currentValue - col - 1) / ngrid;
                    // StdOut.println("v:"+currentValue+"r:"+row+"c:"+col);
                    manhattanCache += (((col > j) ? (col - j) : (j - col)) +
                    ((row > i) ? (row - i) : (i - row)));
                }
            }
        }
    }

    public int dimension() // board dimension n
     {
        return ngrid;
    }

    public int hamming() // number of blocks out of place
     {
        return hammingCache;
    }

    public int manhattan() // sum of Manhattan distances between blocks and goal
     {
        return manhattanCache;
    }

    public boolean isGoal() // is this board the goal board?
     {
        return hammingCache == 0;
    }

    public Board twin() // a board that is obtained by exchanging any pair of blocks
     {
        int[][] twin = new int[ngrid][ngrid];

        for (int i = 0; i < ngrid; i++) {
            for (int j = 0; j < ngrid; j++) {
                twin[i][j] = (int) board[(i * ngrid) + j];
            }
        }

        if ((twin[0][0] == 0) || (twin[0][1] == 0)) {
            swap(twin, 1, 0, 1, 1);
        } else {
            swap(twin, 0, 0, 0, 1);
        }

        return new Board(twin);
    }

    public boolean equals(Object y) // does this board equal y?
     {
        if (y == this) {
            return true;
        }

        if (y == null) {
            return false;
        }

        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;
        if (that.dimension() != ngrid) return false;
        for (int i = 0; i < (ngrid * ngrid); i++) {
            if (this.board[i] != that.board[i]) {
                return false;
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() // all neighboring boards
     {
        int blankRow = 0;
        int blankCol = 0;
        Stack<Board> neighbours = new Stack<Board>();

        int[][] clone = new int[ngrid][ngrid];

        for (int i = 0; i < ngrid; i++) {
            for (int j = 0; j < ngrid; j++) {
                clone[i][j] = (int) board[(i * ngrid) + j];

                if (clone[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
            }
        }

        if (blankCol != 0) {
            swap(clone, blankRow, blankCol - 1, blankRow, blankCol);
            neighbours.push(new Board(clone));
            swap(clone, blankRow, blankCol - 1, blankRow, blankCol);
        }

        if (blankCol != (ngrid - 1)) {
            swap(clone, blankRow, blankCol + 1, blankRow, blankCol);
            neighbours.push(new Board(clone));
            swap(clone, blankRow, blankCol + 1, blankRow, blankCol);
        }

        if (blankRow != 0) {
            swap(clone, blankRow - 1, blankCol, blankRow, blankCol);
            neighbours.push(new Board(clone));
            swap(clone, blankRow - 1, blankCol, blankRow, blankCol);
        }

        if (blankRow != (ngrid - 1)) {
            swap(clone, blankRow + 1, blankCol, blankRow, blankCol);
            neighbours.push(new Board(clone));
        }

        return neighbours;
    }

    private void swap(int[][] array, int i, int j, int a, int b) {
        int temp = array[i][j];
        array[i][j] = array[a][b];
        array[a][b] = temp;
    }

    public String toString() // string representation of this board (in the output format specified below)
     {
        StringBuilder s = new StringBuilder();
        s.append(ngrid + "\n");
        for (int i = 0; i < ngrid; i++) {
            for (int j = 0; j < ngrid; j++) {
                s.append(String.format("%2d ", (int) board[(i * ngrid) + j]));
            }

            s.append("\n");
        }

        return s.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
     {
        // read in the board specified in the filename
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        // solve the slider puzzle
        Board initial = new Board(tiles);
        StdOut.printf("hamming:%d manhattan:%d \n", initial.hamming(),
            initial.manhattan());
        StdOut.println("dim:" + initial.dimension());
        StdOut.println(initial.toString());
        StdOut.println("goal:" + initial.isGoal());
        StdOut.println("twin:\n" + initial.twin().toString());

        StdOut.println("neighbours:");

        for (Board s : initial.neighbors()) {
            StdOut.println(s.toString());
        }
    }
}