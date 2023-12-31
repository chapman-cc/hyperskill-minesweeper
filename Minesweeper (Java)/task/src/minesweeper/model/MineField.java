package minesweeper.model;

import minesweeper.model.BombCell.Coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

public class MineField {
    public static int ROW_COUNT = 9;
    public static int COL_COUNT = 9;

    private BombCell[][] field;
    private Map<Coordinate, BombCell> minesMap;

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
            }
        }
    }

    public void print(Consumer<String> printer) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < COL_COUNT; i++) {
            for (int j = 0; j < ROW_COUNT; j++) {
                BombCell cell = field[i][j];
                sb.append(cell.isBomb() ? "X" : ".");
            }
            sb.append("\n");
        }
        printer.accept(sb.toString());
    }
}
