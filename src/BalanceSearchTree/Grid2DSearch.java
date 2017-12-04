package BalanceSearchTree;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grid2DSearch <X extends Comparable<X>, Y extends Comparable<Y>>{
    final class Point {
        X x;
        Y y;
        Point (X x, Y y) {
            this.x = x;
            this.y = y;
        }
        private boolean isLeft (X x) {
            return this.x.compareTo(x) < 0;
        }
        private boolean isBelow (Y y) {
            return this.y.compareTo(y) < 0;
        }
        private boolean isEqual (Point p) {
            return p != null && p.y == this.y && p.x == this.x;
        }
    }
    private class Grid {
        List<Point> gridList;
        Point leftUpper;
        Point rightDown;
        Grid(Point leftUpper, Point rightDown) {
            this.leftUpper = leftUpper;
            this.rightDown = rightDown;
            gridList = new ArrayList<Point>();
        }
        void insert(Point p) throws IOException {
            if (!contain(p)) throw new IOException("not in this zoom");
            gridList.add(p);
        }
        boolean contain(Point p) {
            return rightDown.isBelow(p.y) && !leftUpper.isBelow(p.y)
                    && rightDown.isLeft(p.x) && !leftUpper.isLeft(p.x);
        }
        boolean check(Point p) {
            return gridList.contains(p);
        }
    }
}
