package minesweeper;

import minesweeper.io.Input;
import minesweeper.io.Output;
import minesweeper.model.BombCell;
import minesweeper.model.BombCell.Coordinate;
import minesweeper.model.MineField;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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

            UserInput userInput = getUserInputCoordinate();
            BombCell cell = mineField.getBombCell(userInput.coordinate);

            if (cell.isBomb() && userInput.keyword != UserInputKeyword.MINE) {
                mineField.revealAllMines();
                output.println(mineField.getFormattedBoard());
                cursePlayer();
                break;
            }
            switch (userInput.keyword) {
                case MINE -> cell.setFlagged(!cell.isFlagged());
                case FREE -> exploreCell(cell);
            }

            if (mineField.areAllBombsMarked() || mineField.areAllCellsMarked()) {
                congratsWinner();
                break;
            }
        }
    }

    private UserInput getUserInputCoordinate() {
        output.print("Set/unset mine marks or claim a cell as free: ");
        Pattern pattern = Pattern.compile("^\\d\\s\\d\\s(free|mine)$", Pattern.CASE_INSENSITIVE);
        String errMsg = "Must be in format \"1 2 free\" or \"3 4 mine\": ";
        String[] strings = input.nextln(pattern, e -> output.print(errMsg)).split(" ");
        int x = Integer.parseInt(strings[0]);
        int y = Integer.parseInt(strings[1]);
        Coordinate coordinate = new Coordinate(x, y);
        UserInputKeyword keyword = UserInputKeyword.valueOf(strings[2].toUpperCase());
        return new UserInput(coordinate, keyword);
    }

    private void exploreCell(BombCell cell) {
        exploreCell(cell, new HashMap<>());
    }

    private void exploreCell(BombCell cell, Map<Coordinate, BombCell> map) {
        if (cell.isBomb()) {
            return;
        }

        cell.setExplored(!cell.isExplored());
        map.put(cell.getCoordinate(), cell);

        for (BombCell c : cell.getSurroundingCells()) {
            if (map.containsKey(c.getCoordinate())) {
                continue;
            }
            if (c.isFlagged() && !c.isBomb()) {
                c.setFlagged(false);
            }
            if (c.getProximityBombsCount() > 0) {
                c.setExplored(true);
                continue;
            }
            map.put(c.getCoordinate(), c);
            exploreCell(c, map);
        }
    }

    private void congratsWinner() {
        output.println("Congratulations! You found all the mines!");
    }

    private void cursePlayer() {
        output.println("You stepped on a mine and failed!");
    }

    enum UserInputKeyword {
        MINE, FREE
    }

    record UserInput(Coordinate coordinate, UserInputKeyword keyword) {

    }
}
