package Game;

public class Piece {

    /*
    The color of a piece. True: White, False: Black
     */
    private final boolean white;

    /*
    The location of a piece is described by a string
    e.x e4
     */
    private String location;

    /*
    The constructor of a Piece object
     */
    public Piece(boolean color) {

        this.white = color;
    }

    /*
    Getter method for the color of a piece
     */
    private boolean getColor() {
        return this.white;
    }






}
