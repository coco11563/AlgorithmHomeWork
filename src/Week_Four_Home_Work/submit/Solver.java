package Week_Four_Home_Work.submit;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;


public class Solver {
    private boolean solvable;
    private GameNode originLast;

    public Solver(Board initial) // find a solution to the initial board (using the A* algorithm)
     {
        if (initial == null) {
            throw new NullPointerException();
        }

        solvable = true;

        MinPQ<GameNode> queue = new MinPQ<GameNode>();

        queue.insert(new GameNode(initial, null, 0, false));
        queue.insert(new GameNode(initial.twin(), null, 0, true));

        while (!queue.isEmpty()) {
            GameNode processed = queue.delMin();

            if (!processed.isTwin) {
                originLast = processed;
            }

            if (processed.boardValue.isGoal()) {
                if (processed.isTwin) {
                    solvable = false;
                }

                break;
            }

            for (Board neighbor : processed.boardValue.neighbors()) {
                if ((processed.prev == null) ||
                        !processed.prev.boardValue.equals(neighbor)) {
                    queue.insert(new GameNode(neighbor, processed,
                            processed.moves + 1, processed.isTwin));
                }
            }
        }
    }

    public boolean isSolvable() // is the initial board solvable?
     {
        return solvable;
    }

    public int moves() // min number of moves to solve initial board; -1 if unsolvable
     {
        if (isSolvable()) {
            return originLast.moves;
        } else {
            return -1;
        }
    }

    public Iterable<Board> solution() // sequence of boards in a shortest solution; null if unsolvable
     {
        if (!isSolvable()) {
            return null;
        }

        Stack<Board> solutions = new Stack<Board>();
        GameNode current = originLast;

        while (current.prev != null) {
            solutions.push(current.boardValue);
            current = current.prev;
        }

        solutions.push(current.boardValue);

        Stack<Board> solutions2 = new Stack<Board>();

        while (!solutions.empty()) {
            solutions2.push(solutions.pop());
        }

        return solutions2;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();

        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());

            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class GameNode implements Comparable<GameNode> {
        private Board boardValue;
        private GameNode prev;
        private int moves;
        private boolean isTwin;

        public GameNode(Board current, GameNode prev, int moves, boolean isTwin) {
            this.boardValue = current;
            this.prev = prev;
            this.moves = moves;
            this.isTwin = isTwin;
        }

        public int compareTo(GameNode that) {
            int priority1 = this.boardValue.manhattan() + this.moves;
            int priority2 = that.boardValue.manhattan() + that.moves;

            if (priority1 == priority2) {
                return 0;
            } else if (priority1 > priority2) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}