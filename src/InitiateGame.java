public class InitiateGame {
    GameLogic logic;
    TetrisFrame frame;
    public InitiateGame() {
        logic = new GameLogic();
        logic.updateFallingBlock("down");
        frame = new TetrisFrame(logic);
        frame.reRender();

    }
}
