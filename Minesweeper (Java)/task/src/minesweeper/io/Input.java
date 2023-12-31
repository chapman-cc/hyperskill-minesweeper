package minesweeper.io;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Consumer;

public class Input {
    private final Scanner sc;

    public Input() {
        this.sc = new Scanner(System.in);
    }

    public String next() {
        try {
            String input = sc.next();
            sc.nextLine();
            return input;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int nextInt(String regex, Consumer<Exception> errorHandler) {
        while (true) {
            try {
                String input = sc.next();
                if (!input.matches(regex)) {
                    throw new InputMismatchException("Incorrect input");
                }
                return Integer.parseInt(input);

            } catch (InputMismatchException e) {
                errorHandler.accept(e);
            }
        }
    }

    public void close() {
        sc.close();
    }
}
