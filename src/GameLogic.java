import java.util.ArrayList;

public class GameLogic {
    int[][] arr = new int[10][20];
    ArrayList<Coordinate> cords;
    Shape[] shapeArr;

    public GameLogic() {
        shapeArr = new Shape[]{new ITetromino(), new OTetromino(), new TTetromino(), new STetromino(), new ZTetromino(), new JTetromino(), new LTetromino()};
        cords = new ArrayList<>();
        generateGridBox();
    }

    public void generateGridBox() {
        for (int i = 0; i <= 270; i+=30) {
            for (int j = 0; j <= 570; j+= 30) {
                cords.add(new Coordinate(i, j, i+30, j+30));
            }
        }
    }

    public void printCords() {
        for (Coordinate cord : cords) {
            System.out.println("(" + cord.getX1() + ", " + cord.getY1() + "); " + "(" + cord.getX2() + ", " + cord.getY2() + ")");
        }
        System.out.println(cords.size());
    }
}
