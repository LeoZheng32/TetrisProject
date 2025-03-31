public class Shape extends Display {
    private String name;
    private int rowPos;
    private int colPos;
    private int rotation;

    public Shape[][] shapeArr;

    public Shape(String name, int rowPos, int colPos) {
        this.name = name;
        this.rowPos = rowPos;
        this.colPos = colPos;
        rotation = 0;
    }

    public Shape[][] getShapeArr() {
        return shapeArr;
    }

    public void setShapeArr(Shape[][] arr) {
        shapeArr = arr;
    }

    public Shape(Shape[][] shapeArr) {
        this.shapeArr = shapeArr;
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

    public void changeRowPos(int num) {
        rowPos += num;
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

    public Shape[][] rotate() {
        return shapeArr;
    }
}
