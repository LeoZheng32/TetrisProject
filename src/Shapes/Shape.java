public class Shape extends Display {
    private String name;
    private int rowPos;
    private int colPos;
    private int rotation;

    public Shape(String name, int rowPos, int colPos) {
        this.name = name;
        this.rowPos = rowPos;
        this.colPos = colPos;
        rotation = 0;
    }

    public int getRowPos() {
        return rowPos;
    }

    public int getColPos() {
        return colPos;
    }

    public void incrementRowPos() {
        rowPos++;
    }

    public void changeColPos(int num) {
        colPos += num;
    }

    public String toString() {
        return name;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int num) {
        rotation = num;
    }

    public void decrementRotation() {
        rotation--;
    }

    public void incrementRotation() {
        rotation++;
    }

    public void rotate() {}
}
