public class Shape extends Display {
    private String name;
    private int[][] shape;
    private int rotation;

    public Shape(String name, int[][] shape) {
        this.name = name;
        this.shape = shape;
        rotation = 0;
    }
    public int[][] getShape() {
        return shape;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
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
