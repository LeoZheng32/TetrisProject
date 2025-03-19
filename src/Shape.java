public class Shape extends Display {
    private String name;
    private int[][] shape;

    public Shape(String name, int[][] shape) {
        this.name = name;
        this.shape = shape;
    }
    public int[][] getShape() {
        return shape;
    }

    public String toString() {
        return name;
    }

    public void rotate() {}
}
