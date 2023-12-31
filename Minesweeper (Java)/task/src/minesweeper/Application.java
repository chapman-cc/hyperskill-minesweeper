package minesweeper;

import minesweeper.io.Input;
import minesweeper.io.Output;
import minesweeper.model.MineField;

public class Application {
    private final Input input;
    private final Output output;
    private MineField minefiled;

    public Application() {
        this.input = new Input();
        this.output = new Output();
        this.minefiled = new MineField();
    }

    public void run() {
        output.print("How many mines do you want on the field? ");
        int minesCount = input.nextInt("\\d+", e->{});
        minefiled.setMinesByCount(minesCount);
        minefiled.print(output::println);
    }
}
