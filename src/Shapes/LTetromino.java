public class LTetromino extends Shape {
    public LTetromino() {
        super("LTetromino", new int[][]{{0, 0, 7},
                                              {7, 7, 7},
                                              {0, 0, 0}});
    }

    @Override
    public void rotate() {
        incrementRotation();
        if (getRotation() == 0) {
            setShape(new int[][]{{0, 0, 7},
                                 {7, 7, 7},
                                 {0, 0, 0}});
        } else if (getRotation() == 1) {
            setShape(new int[][]{{0, 7, 0},
                                 {0, 7, 0},
                                 {0, 7, 7}});
        } else if (getRotation() == 2) {
            setShape(new int[][]{{0, 0, 0},
                                 {7, 7, 7},
                                 {7, 0, 0}});
        }  else if (getRotation() == 3) {
            setShape(new int[][]{{7, 7, 0},
                                 {0, 7, 0},
                                 {0, 7, 0}});
            setRotation(-1);
        }
    }
}
