package minesweeper.model;

import minesweeper.model.BombCell.Coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MineField {
    public static int ROW_COUNT = 9;
    public static int COL_COUNT = 9;

    private final BombCell[][] field;
    private final Map<Coordinate, BombCell> minesMap;

    public MineField() {
        this.field = new BombCell[COL_COUNT][ROW_COUNT];
        this.minesMap = new HashMap<>();

        for (int col = 0; col < COL_COUNT; col++) {
            for (int row = 0; row < ROW_COUNT; row++) {
                int x = col + 1;
                int y = row + 1;
                BombCell cell = new BombCell(x, y);
                field[COL_COUNT - 1 - row][col] = cell;
                minesMap.put(cell.getCoordinate(), cell);

                cell.setTop(minesMap.get(new Coordinate(x, y + 1)));
                cell.setTopRight(minesMap.get(new Coordinate(x + 1, y + 1)));
                cell.setRight(minesMap.get(new Coordinate(x + 1, y)));
                cell.setBottomRight(minesMap.get(new Coordinate(x + 1, y - 1)));
                cell.setBottom(minesMap.get(new Coordinate(x, y - 1)));
                cell.setBottomLeft(minesMap.get(new Coordinate(x - 1, y - 1)));
                cell.setLeft(minesMap.get(new Coordinate(x - 1, y)));
                cell.setTopLeft(minesMap.get(new Coordinate(x - 1, y + 1)));
            }
        }
    }

    public void setMinesByCount(int count) {
        while (count > 0) {
            Random random = new Random();
            int x = random.nextInt(1, ROW_COUNT + 1);
            int y = random.nextInt(1, COL_COUNT + 1);
            BombCell cell = minesMap.get(new Coordinate(x, y));
            if (cell != null && !cell.isBomb()) {
                cell.setBomb();
                count--;

                for (BombCell c : new BombCell[]{
                        cell.getTop(),
                        cell.getTopRight(),
                        cell.getRight(),
                        cell.getBottomRight(),
                        cell.getBottom(),
                        cell.getBottomLeft(),
                        cell.getLeft(),
                        cell.getTopLeft()
                }) {
                    if (c != null) {
                        c.addProximityBombsCountBy1();
                    }

                }
            }
        }
    }


    public String getFormattedBoard() {
        String emptyLine = "-|---------|";
        StringBuilder sb = new StringBuilder();
        sb.append(" |123456789|").append("\n");
        sb.append(emptyLine).append("\n");
        for (int i = 0; i < COL_COUNT; i++) {
            sb.append(i + 1).append("|");
            for (int j = 0; j < ROW_COUNT; j++) {
                BombCell cell = field[i][j];
                if (cell.isBomb()) {
                    sb.append("X");
                    continue;
                }
                if (cell.getProximityBombsCount() > 0) {
                    sb.append(cell.getProximityBombsCount());
                    continue;
                }
                sb.append(".");
            }

            sb.append("|").append("\n");
        }
        sb.append(emptyLine).append("\n");
        return sb.toString();
    }
}
