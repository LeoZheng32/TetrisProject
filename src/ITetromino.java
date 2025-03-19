public class ITetromino extends Shape {
    int[][] shape;
    int rotation;
    public ITetromino(int[][] shape) {
        super("ITetromino", shape);
        this.shape = shape;
        rotation = 0;
    }

    @Override
    public void rotate() {
        rotation++;
        if (rotation == 0) {
            shape = new int[][]{{1, 1, 1, 1}};
        } else {
            shape = new int[][]{{1}, {1}, {1}, {1}};
            rotation--;
        }
    }
}
