public class JTetromino extends Shape {
    public JTetromino() {
        super("JTetromino", new int[][]{{6, 0, 0},
                                              {6, 6, 6},
                                              {0, 0, 0}});
    }

    @Override
    public void rotate() {
        incrementRotation();
        if (getRotation() == 0) {
            setShape(new int[][]{{6, 0, 0},
                                 {6, 6, 6},
                                 {0, 0, 0}});
        } else if (getRotation() == 1) {
            setShape(new int[][]{{0, 6, 6},
                                 {0, 6, 0},
                                 {0, 6, 0}});
        } else if (getRotation() == 2) {
            setShape(new int[][]{{0, 0, 0},
                                 {6, 6, 6},
                                 {0, 0, 6}});
        }  else if (getRotation() == 3) {
            setShape(new int[][]{{0, 6, 0},
                                 {0, 6, 0},
                                 {6, 6, 0}});
            setRotation(-1);
        }
    }
}
