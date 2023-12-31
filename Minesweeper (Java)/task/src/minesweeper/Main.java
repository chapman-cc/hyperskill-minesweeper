package minesweeper;

public class Main {
    public static void main(String[] args) {
        try {
            Application app = new Application();
            app.run();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
