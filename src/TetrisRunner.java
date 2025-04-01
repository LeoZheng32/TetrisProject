import java.util.Timer;

public class TetrisRunner {
    public static void main(String[] args) {
        //TetrisFrame frame = new TetrisFrame();
        GameLogic hi = new GameLogic();
        System.out.println();
        hi.printArr();
        System.out.println();
        hi.updateFallingBlock("down");
        hi.updateFallingBlock("down");
        hi.updateFallingBlock("down");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < 4; i++) {
            System.out.println();
            hi.rotateBlock();
            hi.printArr();
        }
    }
}
