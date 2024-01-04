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
    private final Map<Coordinate, BombCell> bombs;


    public MineField() {
        this.field = new BombCell[COL_COUNT][ROW_COUNT];
        this.minesMap = new HashMap<>();
        this.bombs = new HashMap<>();

        for (int xIdx = 0; xIdx < COL_COUNT; xIdx++) {
            for (int yIdx = 0; yIdx < ROW_COUNT; yIdx++) {
                int x = xIdx + 1;
                int y = yIdx + 1;
                BombCell cell = new BombCell(x, y);
                field[yIdx][xIdx] = cell;
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

    public Map<Coordinate, BombCell> getBombs() {
        return bombs;
    }

    public boolean areAllCellsMarked() {
        for (BombCell[] col : field) {
            for (BombCell cell : col) {
                if (!cell.isExplored()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean areAllBombsMarked() {
        for (BombCell bomb : bombs.values()) {
            if (!bomb.isFlagged()) {
                return false;
            }
        }
        return true;
    }

    public void revealAllMines() {
        for (BombCell bomb : bombs.values()) {
            bomb.setHidden(false);
        }
    }

    public BombCell getBombCell(Coordinate coordinate) {
        return minesMap.get(coordinate);
    }

    public void setMinesByCount(int count) {
        while (count > 0) {
            Random random = new Random();
            int x = random.nextInt(1, ROW_COUNT + 1);
            int y = random.nextInt(1, COL_COUNT + 1);
            BombCell cell = minesMap.get(new Coordinate(x, y));
            if (cell != null && !cell.isBomb()) {
                cell.setBomb();
                bombs.put(cell.getCoordinate(), cell);
                count--;
            }
        }

        for (BombCell bomb : bombs.values()) {
            for (BombCell surrounding : bomb.getSurroundingCells()) {
                if (!surrounding.isBomb()) {
                    surrounding.addProximityBombsCountBy1();
                }
            }
        }

    }


    public String getFormattedBoard() {
        String headerRow = " |123456789|";
        String emptyRow = "-|---------|";
        StringBuilder sb = new StringBuilder();
        sb.append(headerRow).append("\n");
        sb.append(emptyRow).append("\n");
        for (int i = 0; i < COL_COUNT; i++) {
            sb.append(i + 1).append("|");
            for (int j = 0; j < ROW_COUNT; j++) {
                BombCell cell = field[i][j];
                sb.append(cell.getPrintFormat());
            }
            sb.append("|").append("\n");
        }
        sb.append(emptyRow).append("\n");
        return sb.toString();
    }
}
