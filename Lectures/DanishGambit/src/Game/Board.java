package Game;

import java.util.HashMap;

public class Board {

    /*
    HashMap modelling a board and their locations
     */
    private HashMap<Piece, String> board;


    public Board() {
        createBoard();
    }

    /*
    Getter method that allows for game class access its information
     */
    public HashMap<Piece, String> getBoard(){
        return this.board;
    }

    private void createBoard() {
        assert !this.board.isEmpty();

        //setting up white side PAWNS
        board.put(new Pawn(true), "a2");
        board.put(new Pawn(true), "b2");
        board.put(new Pawn(true), "c2");
        board.put(new Pawn(true), "d2");
        board.put(new Pawn(true), "e2");
        board.put(new Pawn(true), "f2");
        board.put(new Pawn(true), "g2");
        board.put(new Pawn(true), "h2");

        // setting up black side PAWNS
        board.put(new Pawn(false), "a7");
        board.put(new Pawn(false), "b7");
        board.put(new Pawn(false), "c7");
        board.put(new Pawn(false), "d7");
        board.put(new Pawn(false), "e7");
        board.put(new Pawn(false), "f7");
        board.put(new Pawn(false), "g7");
        board.put(new Pawn(false), "h7");

    }








}
