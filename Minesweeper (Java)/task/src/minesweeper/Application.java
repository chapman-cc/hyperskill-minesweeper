package minesweeper;

import minesweeper.io.Input;
import minesweeper.io.Output;
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
        String board = mineField.getFormattedBoard();
        output.println(board);
    }
}
