public class STetromino extends Shape {
    public STetromino() {
        super("STetromino", new int[][]{{0, 4, 4},
                                              {4, 4, 0},
                                              {0, 0, 0}});
    }

    @Override
    public void rotate() {
        incrementRotation();
        if (getRotation() == 0 || getRotation() == 4) {
            setShape(new int[][]{{0, 4, 4},
                                 {4, 4, 0},
                                 {0, 0, 0}});
        } else if (getRotation() == 1) {
            setShape(new int[][]{{0, 4, 0},
                                 {0, 4, 4},
                                 {0, 0, 4}});
        } else if (getRotation() == 2) {
            setShape(new int[][]{{0, 0, 0},
                                 {0, 4, 4},
                                 {4, 4, 0}});
        }  else if (getRotation() == 3) {
            setShape(new int[][]{{4, 0, 0},
                                 {4, 4, 0},
                                 {0, 4, 0}});
            setRotation(-1);
        }
    }
}
