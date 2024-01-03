package minesweeper.io;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Input {
    private final Scanner sc;

    public Input() {
        this.sc = new Scanner(System.in);
    }


    public String next() {
        return next(e -> {
        });
    }

    public String next(Consumer<Exception> errorHandler) {
        return next(".+", errorHandler);
    }

    public String next(String regex, Consumer<Exception> errorHandler) {
        while (true) {
            try {
                String input = sc.next();
                sc.nextLine();
                if (!input.matches(regex)) {
                    throw new InputMismatchException("Incorrect Input");
                }
                return input;
            } catch (Exception e) {
                errorHandler.accept(e);
            }
        }
    }

    public String nextln(String regex, Consumer<Exception> errorHandler) {
        while (true) {
            try {
                String input = sc.nextLine();
                if (!input.matches(regex)) {
                    throw new InputMismatchException("Incorrect Input");
                }
                return input;
            } catch (Exception e) {
                errorHandler.accept(e);
            }
        }
    }

    public int nextInt(Predicate<String> predicate, Consumer<Exception> errorHandler) {
        while (true) {
            try {
                String input = sc.next();
                if (!predicate.test(input)) {
                    throw new InputMismatchException("Incorrect Input");
                }
                return Integer.parseInt(input);
            } catch (InputMismatchException e) {
                errorHandler.accept(e);
            }
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
            } finally {
                sc.nextLine();
            }
        }
    }

    public void close() {
        sc.close();
    }
}
