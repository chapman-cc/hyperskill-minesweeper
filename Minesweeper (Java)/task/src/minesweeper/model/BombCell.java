package minesweeper.model;

import java.util.Objects;

public class BombCell {
    private boolean bomb;
    private boolean hidden;
    private final Coordinate coordinate;
    private int proximityBombsCount;
    private BombCell top = null;
    private BombCell topRight = null;
    private BombCell right = null;
    private BombCell bottomRight = null;
    private BombCell bottom = null;
    private BombCell bottomLeft = null;
    private BombCell left = null;
    private BombCell topLeft = null;


    public BombCell(int x, int y) {
        this(false, x, y);
    }

    public BombCell(boolean bomb, int x, int y) {
        this.bomb = bomb;
        this.hidden = true;
        this.coordinate = new Coordinate(x, y);
        this.proximityBombsCount = 0;
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

    public int getProximityBombsCount() {
        return proximityBombsCount;
    }

    public void setProximityBombsCount(int proximityBombsCount) {
        this.proximityBombsCount = proximityBombsCount;
    }

    public void addProximityBombsCountBy1() {
        this.proximityBombsCount++;
    }

    public void reveal() {
        this.hidden = true;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "BombCell{" +
                "bomb=" + bomb +
                ", hidden=" + hidden +
                ", coordinate=" + coordinate +
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
}
