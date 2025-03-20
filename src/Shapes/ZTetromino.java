public class ZTetromino extends Shape {
    public ZTetromino() {
        super("ZTetromino", new int[][]{{5, 5, 0},
                                              {0, 5, 5},
                                              {0, 0, 0}});
    }

    @Override
    public void rotate() {
        incrementRotation();
        if (getRotation() == 0) {
            setShape(new int[][]{{5, 5, 0},
                                 {0, 5, 5},
                                 {0, 0, 0}});
        } else if (getRotation() == 1) {
            setShape(new int[][]{{0, 0, 5},
                                 {0, 5, 5},
                                 {0, 5, 0}});
        } else if (getRotation() == 2) {
            setShape(new int[][]{{0, 0, 0},
                                 {5, 5, 0},
                                 {0, 5, 5}});
        }  else if (getRotation() == 3) {
            setShape(new int[][]{{0, 5, 0},
                                 {5, 5, 0},
                                 {5, 0, 0}});
            setRotation(-1);
        }
    }
}
