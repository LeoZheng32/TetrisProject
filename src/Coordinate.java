public class Coordinate {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int rowIdx;
    private int colIdx;
    public Coordinate(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Coordinate(int rowIdx, int colIdx) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
    }

    public int getRowIdx() {
        return rowIdx;
    }

    public int getColIdx() {
        return colIdx;
    }

    public void decrementRowIdx() {
        rowIdx++;
    }

    public void setColIdx(int num) {
        colIdx += num;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
