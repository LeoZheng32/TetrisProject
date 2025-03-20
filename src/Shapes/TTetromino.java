public class TTetromino extends Shape {
    public TTetromino() {
        super("TTetromino", new int[][]{{0, 3, 0},
                                              {3, 3, 3},
                                              {0, 0, 0}});
    }

    @Override
    public void rotate() {
        incrementRotation();
        if (getRotation() == 0) {
            setShape(new int[][]{{0, 3, 0},
                                 {3, 3, 3},
                                 {0, 0, 0}});
        } else if (getRotation() == 1) {
            setShape(new int[][]{{0, 3, 0},
                                 {0, 3, 3},
                                 {0, 3, 0}});
        } else if (getRotation() == 2) {
            setShape(new int[][]{{0, 0, 0},
                                 {3, 3, 3},
                                 {0, 3, 0}});
        }  else if (getRotation() == 3) {
            setShape(new int[][]{{0, 3, 0},
                                 {3, 3, 0},
                                 {0, 3, 0}});
            setRotation(-1);
        }
    }
}
