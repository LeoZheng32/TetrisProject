import java.util.Timer;

public class TetrisRunner {
    public static void main(String[] args) {
        //TetrisFrame frame = new TetrisFrame();
        GameLogic hi = new GameLogic();
        System.out.println();
        hi.printArr();
        for (int i = 0; i < 20; i++) {
            System.out.println();
            if (hi.canMoveDown()) {
                hi.updateFallingBlock("down");
            }
            hi.printArr();
        }

    }
}
