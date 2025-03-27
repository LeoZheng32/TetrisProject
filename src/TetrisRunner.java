import java.util.Timer;

public class TetrisRunner {
    public static void main(String[] args) {
        TetrisFrame frame = new TetrisFrame();
        GameLogic hi = new GameLogic();
        hi.printCords();
        hi.printArr();

    }
}
