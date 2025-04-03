import java.util.Timer;
import java.util.TimerTask;

public class InitiateGame {
    GameLogic logic;
    TetrisFrame frame;
    public InitiateGame() {
        logic = new GameLogic();
        frame = new TetrisFrame(logic);
        logic.setFrame(frame);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //frame = new TetrisFrame(logic);

    }

    public void play() {
        while (true) {
            logic.generateBlock();

        }
    }
}
