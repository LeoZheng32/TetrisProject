import java.util.Timer;

public class TetrisRunner {
    public static void main(String[] args) {
        //TetrisFrame frame = new TetrisFrame();
        GameLogic hi = new GameLogic();
        hi.printArr();
        hi.updateFallingBlock("right");
        hi.updateFallingBlock("right");
        System.out.println();
        for (int i = 0; i < 20; i++) {
            if (hi.canMoveDown()) {
                hi.updateFallingBlock("down");
            } else {
                System.out.println(hi.canMoveLeft());
            }
            hi.printArr();
            System.out.println();
        }

    }
}
