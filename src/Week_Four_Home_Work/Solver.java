package Week_Four_Home_Work;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 *
 * Created by coco1 on 2016/10/10.
 *
 */
public class Solver {
    private boolean Solvable = false;
    private int moves = 0;
    private MinPQ<Node> pq = new MinPQ<>(10);
    private Board initial;
    private Node orgin;
    public Solver(Board initial) {// find a solution to the initial board (using the A* algorithm)
        if (initial == null) {
            throw new NullPointerException();
        }
        this.initial = initial;
        Node Process = new Node(initial, null, 0, false);
        Node Twin = new Node(initial.twin(), null, 0, true);
        Solvable = true;
        pq.insert(Process);
        pq.insert(Twin);
        while (!pq.isEmpty()) {
            {
                Node processed = pq.delMin();
                if (!processed.isTwin) {
                    orgin = processed;
                }
                if (processed.board.isGoal()) {
                    if (processed.isTwin) {
                        Solvable = false;
                    }
                    break;
                }
                for (Board b : processed.board.neighbors()) {
                    if (processed.prev == null || !b.equals(processed.prev.board))
                        pq.insert(new Node(b, processed, processed.moves + 1, processed.isTwin));
                }

            }
        }
    }
    public boolean isSolvable() {// is the initial board solvable?
        return Solvable;
    }
    public int moves() {// min number of moves to solve initial board; -1 if unsolvable
        return orgin.moves;
    }

    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        if (!isSolvable()) {
            return null;
        }
        Stack<Board> solution = new Stack<>();
        Stack<Board> solution2 = new Stack<>();
        Node revert = orgin;
        while (revert.prev != null) {
            solution.push(revert.board);
            revert = revert.prev;
        }
        solution2.push(initial);
        while (!solution.isEmpty()) {
            solution2.push(solution.pop());
        }
        return solution2;
    }
    public static void main(String[] args) {

            // create initial board from file
            In in = new In("C:\\Users\\coco1\\IdeaProjects\\AlgorithmHomeWork\\data\\Week_IIII\\puzzle3x3-unsolvable2.txt");
            int n = in.readInt();
            int[][] blocks = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    blocks[i][j] = in.readInt();
                }

            Board initial = new Board(blocks);

            // solve the puzzle
            Solver solver = new Solver(initial);

            // print solution to standard output
            if (!solver.isSolvable())
                StdOut.println("No solution possible");
            else {
                StdOut.println("Minimum number of moves = " + solver.moves());
                for (Board board : solver.solution())
                    StdOut.println(board);
            }
    }

    public class comparator implements Comparator<Board>{
        @Override
        public int compare(Board o1, Board o2) {
            return o1.manhattan() > o2.manhattan() ? 1 : o1.manhattan() == o2.manhattan() ? 0 : -1;
        }
    }
    class Node  implements Comparable<Node>{
        private int moves;
        private Board board;
        private Node prev;
        private boolean isTwin;
         Node(Board initial, Node prev, int moves, boolean isTwin) {
            board = initial;
            this.prev = prev;
            this.moves = moves;
            this.isTwin = isTwin;
        }

        @Override
        public int compareTo(Node o) {
            int this_priority = this.board.manhattan() + moves;
            int that_priority = o.board.manhattan() + moves;

            if (that_priority == this_priority)
                return 0;
            else if (this_priority > that_priority)
                return 1;
            else
                return -1;
        }
    }
}
