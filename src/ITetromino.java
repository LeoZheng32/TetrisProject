public class ITetromino extends Shape {

    public ITetromino() {
        super("ITetromino", new int[][]{{1, 1, 1, 1}});
    }

    @Override
    public void rotate() {
        incrementRotation();
        if (getRotation() == 0) {
             setShape(new int[][]{{1, 1, 1, 1}});
        } else {
            setShape(new int[][]{{1}, {1}, {1}, {1}});
            decrementRotation();
        }
    }
}
