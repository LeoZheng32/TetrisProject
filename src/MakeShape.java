public class MakeShape {
    public MakeShape() {}

    public Shape[][] randomSelectedShape() {
        int rand = (int) (Math.random() * 7);
        if (rand == 0) {
            return IShape();
        } else if (rand == 1) {
            return OShape();
        } else if (rand == 2) {
            return TShape();
        } else if (rand == 3) {
            return SShape();
        } else if (rand == 4) {
            return ZShape();
        } else if (rand == 5) {
            return JShape();
        } else  {
            return LShape();
        }
    }

    private Shape[][] IShape() {
        return new Shape[][]{
                {null, null, null, null},
                {new IBlock(0, 3), new IBlock(0, 4), new IBlock(0, 5), new IBlock(0, 6)},
                {null, null, null, null},
                {null, null, null, null}};
    }

    private Shape[][] OShape() {
        return new Shape[][]{{new OBlock(0, 3), new OBlock(0, 4)}, {new OBlock(1, 3), new OBlock(1, 4)}};
    }

    private Shape[][] TShape() {
        return new Shape[][]{
                {null, new TBlock(0, 4), null},
                {new TBlock(1, 3), new TBlock(1, 4), new TBlock(1, 5)},
                {null, null, null, null}};
    }

    private Shape[][] SShape() {
        return new Shape[][]{
                {null, new SBlock(0, 4), new SBlock(0, 5)},
                {new SBlock(1, 3), new SBlock(1, 4), null},
                {null, null, null, null}};
    }

    private Shape[][] ZShape() {
        return new Shape[][]{
                {new ZBlock(0, 3), new ZBlock(0, 4), null},
                {null, new ZBlock(1, 4), new ZBlock(1, 5)},
                {null, null, null, null}};
    }

    private Shape[][] JShape() {
        return new Shape[][]{
                {new JBlock(0, 3), null, null},
                {new JBlock(1, 3), new JBlock(1, 4), new JBlock(1, 5)},
                {null, null, null, null}};
    }

    private Shape[][] LShape() {
        return new Shape[][]{
                {null, null, new LBlock(0, 5)},
                {new LBlock(1, 3), new LBlock(1, 4), new LBlock(1, 5)},
                {null, null, null, null}};
    }
}
