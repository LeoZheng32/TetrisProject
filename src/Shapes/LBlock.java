import java.rmi.dgc.Lease;

public class LBlock extends Shape {
    public LBlock(int rowPos, int colPos) {
        super("L", rowPos, colPos);
    }

    public LBlock(Shape[][] shapeArr) {
        super(shapeArr);
    }

    @Override
    public Shape[][] rotate() {
        Shape[][] original = getShapeArr();
        Shape[][] editedShape = new Shape[3][3];
        setRotation((getRotation()+1)%4);

        editedShape[1][1] = new LBlock(original[1][1].getRowPos(), original[1][1].getColPos());
        if (getRotation() == 0) {
            editedShape[0][2] = new LBlock(original[0][0].getRowPos(), original[0][0].getColPos()+2);
            editedShape[1][2] = new LBlock(original[0][1].getRowPos()+1, original[0][1].getColPos()+1);
            editedShape[1][0] = new LBlock(original[2][1].getRowPos()-1, original[2][1].getColPos()-1);
        } else if (getRotation() == 1) {
            editedShape[2][2] = new LBlock(original[0][2].getRowPos()+2, original[0][2].getColPos());
            editedShape[2][1] = new LBlock(original[1][2].getRowPos()+1, original[1][2].getColPos()-1);
            editedShape[0][1] = new LBlock(original[1][0].getRowPos()-1, original[1][0].getColPos()+1);
        } else if (getRotation() == 2) {
            editedShape[2][0] = new LBlock(original[2][2].getRowPos(), original[2][2].getColPos()-2);
            editedShape[1][0] = new LBlock(original[2][1].getRowPos()-1, original[2][1].getColPos()-1);
            editedShape[1][2] = new LBlock(original[0][1].getRowPos()+1, original[0][1].getColPos()+1);
        } else if (getRotation() == 3) {
            editedShape[0][0] = new LBlock(original[2][0].getRowPos()-2, original[2][0].getColPos());
            editedShape[0][1] = new LBlock(original[1][0].getRowPos()-1, original[1][0].getColPos()+1);
            editedShape[2][1] = new LBlock(original[1][2].getRowPos()+1, original[1][2].getColPos()-1);
        }
        return editedShape;
    }
}
