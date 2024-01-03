package minesweeper;

import minesweeper.io.Input;
import minesweeper.io.Output;
import minesweeper.model.BombCell;
import minesweeper.model.BombCell.Coordinate;
import minesweeper.model.MineField;

public class Application {
    private final Input input;
    private final Output output;
    private final MineField mineField;

    public Application() {
        this.input = new Input();
        this.output = new Output();
        this.mineField = new MineField();
    }

    public void run() {
        output.print("How many mines do you want on the field? ");
        int MAX_COUNT = MineField.ROW_COUNT * MineField.COL_COUNT;
        int minesCount = input.nextInt("\\b[1-9]\\b|\\b[1-7][0-9]\\b|\\b8[0-1]\\b", (e) -> output.printf("Only 1 to %d ", MAX_COUNT));
        mineField.setMinesByCount(minesCount);

        while (true) {
            String board = mineField.getFormattedBoard();
            output.println(board);
            Coordinate coordinate = getUserInputCoordinate();
            BombCell cell = mineField.getBombCell(coordinate);
            if (!cell.isBomb() && cell.getProximityBombsCount() > 0) {
                output.println("There is a number here!");
                continue;
            }
            markCell(cell);

            if (mineField.areAllBombsMarked()) {
                congratsWinner();
                break;
            }
        }


    }

    private Coordinate getUserInputCoordinate() {
        output.print("Set/delete mines marks (x and y coordinates): ");
        String[] coorString = input.nextln("^\\d\\s\\d$", e -> output.print("Must be in this format \"1 2\": ")).split(" ");
        int x = Integer.parseInt(coorString[0]);
        int y = Integer.parseInt(coorString[1]);
        return new Coordinate(x, y);
    }

    private void markCell(BombCell cell) {
        cell.setMarked(!cell.isMarked());
    }

    private void congratsWinner() {
        output.println("Congratulations! You found all the mines!");
    }
}
