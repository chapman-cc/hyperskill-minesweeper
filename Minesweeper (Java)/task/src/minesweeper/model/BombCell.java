package minesweeper.model;

import java.util.Objects;

public class BombCell {
    private boolean bomb;
    private boolean hidden;
    private final Coordinate coordinate;

    public BombCell(int x, int y) {
        this(false, x, y);
    }

    public BombCell(boolean bomb, int x, int y) {
        this.bomb = bomb;
        this.hidden = true;
        this.coordinate = new Coordinate(x, y);
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb() {
        this.bomb = true;
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
