package minesweeper.model;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class BombCell {
    private boolean bomb;
    private boolean flagged;
    private boolean explored;
    private boolean hidden;
    private final Coordinate coordinate;
    private int proximityBombsCount;
    private BombCell top = null,
            topRight = null,
            right = null,
            bottomRight = null,
            bottom = null,
            bottomLeft = null,
            left = null,
            topLeft = null;

    {
        flagged = false;
        explored = false;
        hidden = true;
        proximityBombsCount = 0;
    }

    public BombCell(int x, int y) {
        this(false, x, y);
    }

    public BombCell(boolean bomb, int x, int y) {
        this.bomb = bomb;
        this.coordinate = new Coordinate(x, y);
    }

    public BombCell getTop() {
        return top;
    }

    public void setTop(BombCell cell) {
        if (cell == null || this.top != null) {
            return;
        }
        this.top = cell;
        cell.setBottom(this);
    }

    public BombCell getTopRight() {
        return topRight;
    }

    public void setTopRight(BombCell cell) {
        if (cell == null || this.topRight != null) {
            return;
        }
        this.topRight = cell;
        cell.setBottomLeft(this);
    }

    public BombCell getRight() {
        return right;
    }

    public void setRight(BombCell cell) {
        if (cell == null || this.right != null) {
            return;
        }
        this.right = cell;
        cell.setLeft(this);
    }

    public BombCell getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(BombCell cell) {
        if (cell == null || this.bottomRight != null) {
            return;
        }
        this.bottomRight = cell;
        cell.setTopLeft(this);
    }

    public BombCell getBottom() {
        return bottom;
    }

    public void setBottom(BombCell cell) {
        if (cell == null || this.bottom != null) {
            return;
        }
        this.bottom = cell;
        cell.setTop(this);
    }

    public BombCell getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(BombCell cell) {
        if (cell == null || this.bottomLeft != null) {
            return;
        }
        this.bottomLeft = cell;
        cell.setTopRight(this);
    }

    public BombCell getLeft() {
        return left;
    }

    public void setLeft(BombCell cell) {
        if (cell == null || this.left != null) {
            return;
        }
        this.left = cell;
        cell.setRight(this);
    }

    public BombCell getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(BombCell cell) {
        if (cell == null || this.topLeft != null) {
            return;
        }
        this.topLeft = cell;
        cell.setBottomRight(this);
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb() {
        this.bomb = true;
    }

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getProximityBombsCount() {
        return proximityBombsCount;
    }

    public void addProximityBombsCountBy1() {
        this.proximityBombsCount++;
    }

    public String getPrintFormat() {
        if (bomb && !hidden) {
            return "X";
        }
        if (flagged) {
            return "*";
        }
        if (explored && !bomb) {
            if (proximityBombsCount > 0) {
                return String.valueOf(proximityBombsCount);
            }
            return "/";
        }
        return ".";
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Queue<BombCell> getSurroundingCells() {
        Queue<BombCell> list = new ArrayDeque<>();
        if (top != null) list.add(top);
        if (topRight != null) list.add(topRight);
        if (right != null) list.add(right);
        if (bottomRight != null) list.add(bottomRight);
        if (bottom != null) list.add(bottom);
        if (bottomLeft != null) list.add(bottomLeft);
        if (left != null) list.add(left);
        if (topLeft != null) list.add(topLeft);
        return list;

    }

    @Override
    public String toString() {
        return "BombCell{" +
                "bomb=" + bomb +
                ", flagged=" + flagged +
                ", coordinate=" + coordinate +
                ", proximityBombsCount=" + proximityBombsCount +
                ", marked=" + explored +
                '}';
    }

    public record Coordinate(int x, int y) {
        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Coordinate that = (Coordinate) object;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    enum State {
        UNEXPLORED,
        EXPLORED,
        FLAGGED_AS_MINE,
        REVEALED
    }
}
