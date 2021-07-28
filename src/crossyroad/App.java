package crossyroad;

/**
 * 程式執行進入點
 */
public class App {
    public static final int width = 1300;
    public static final int height = 846;

    public static void main(String[] args) {
        new Window(width, height, "FOOd", "src/crossyroad/title.png");
    }
}