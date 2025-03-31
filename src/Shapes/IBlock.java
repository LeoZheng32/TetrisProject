public class IBlock extends Shape {

    public IBlock(int rowPos, int colPos) {
        super("I", rowPos, colPos);
    }

    public IBlock(Shape[][] shapeArr) {
        super(shapeArr);
    }

    @Override
    public Shape[][] rotate() {
        Shape[][] original = getShapeArr();
        Shape[][] editedShape = new Shape[4][4];
        setRotation((getRotation()+1)%4);

        if (getRotation() == 0) {
            editedShape[1][0] = new IBlock(original[3][1].getRowPos()-2, original[3][1].getColPos()-1);
            editedShape[1][1] = new IBlock(original[2][1].getRowPos()-1, original[2][1].getColPos());
            editedShape[1][2] = new IBlock(original[1][1].getRowPos(), original[1][1].getColPos()+1);
            editedShape[1][3] = new IBlock(original[0][1].getRowPos()+1, original[0][1].getColPos()+2);
        } else if (getRotation() == 1) {
            editedShape[0][2] = new IBlock(original[1][3].getRowPos()-1, original[1][3].getColPos()-1);
            editedShape[1][2] = new IBlock(original[1][2].getRowPos(), original[1][2].getColPos());
            editedShape[2][2] = new IBlock(original[1][1].getRowPos()+1, original[1][1].getColPos()+1);
            editedShape[3][2] = new IBlock(original[1][0].getRowPos()+2, original[1][0].getColPos()+2);
        } else if (getRotation() == 2) {
            editedShape[2][0] = new IBlock(original[0][2].getRowPos()+2, original[0][2].getColPos()-2);
            editedShape[2][1] = new IBlock(original[1][2].getRowPos()+1, original[1][2].getColPos()-1);
            editedShape[2][2] = new IBlock(original[2][2].getRowPos(), original[2][2].getColPos());
            editedShape[2][3] = new IBlock(original[3][2].getRowPos()-1, original[3][2].getColPos()+1);
        } else if (getRotation() == 3) {
            editedShape[0][1] = new IBlock(original[2][0].getRowPos()-2, original[2][0].getColPos()+1);
            editedShape[1][1] = new IBlock(original[2][1].getRowPos()-1, original[2][1].getColPos());
            editedShape[2][1] = new IBlock(original[2][2].getRowPos(), original[2][2].getColPos()-1);
            editedShape[3][1] = new IBlock(original[2][3].getRowPos()+1, original[2][3].getColPos()-2);
        }
        return editedShape;
    }
}
