public class TBlock extends Shape {
    public TBlock(int rowPos, int colPos) {
        super("T", rowPos, colPos);
    }

    public TBlock(Shape[][] shapeArr) {
        super(shapeArr);
    }

    @Override
    public Shape[][] rotate() {
        Shape[][] original = getShapeArr();
        Shape[][] editedShape = new Shape[3][3];
        setRotation((getRotation()+1)%4);

        editedShape[1][1] = new TBlock(original[1][1].getRowPos(), original[1][1].getColPos());
        if (getRotation() == 0) {
            editedShape[0][1] = new TBlock(original[1][0].getRowPos()-1, original[1][0].getColPos()+1);
            editedShape[1][0] = new TBlock(original[2][1].getRowPos()-1, original[2][1].getColPos()-1);
            editedShape[1][2] = new TBlock(original[0][1].getRowPos()+1, original[0][1].getColPos()+1);
        } else if (getRotation() == 1) {
            editedShape[1][2] = new TBlock(original[0][1].getRowPos()+1, original[0][1].getColPos()+1);
            editedShape[0][1] = new TBlock(original[1][0].getRowPos()-1, original[1][0].getColPos()+1);
            editedShape[2][1] = new TBlock(original[1][2].getRowPos()+1, original[1][2].getColPos()-1);
        } else if (getRotation() == 2) {
            editedShape[2][1] = new TBlock(original[1][2].getRowPos()+1, original[1][2].getColPos()-1);
            editedShape[1][2] = new TBlock(original[0][1].getRowPos()+1, original[0][1].getColPos()+1);
            editedShape[1][0] = new TBlock(original[2][1].getRowPos()-1, original[2][1].getColPos()-1);
        } else if (getRotation() == 3) {
            editedShape[1][0] = new TBlock(original[2][1].getRowPos()-1, original[2][1].getColPos()-1);
            editedShape[2][1] = new TBlock(original[1][2].getRowPos()+1, original[1][2].getColPos()-1);
            editedShape[0][1] = new TBlock(original[1][0].getRowPos()-1, original[1][0].getColPos()+1);
        }
        return editedShape;
    }
}
