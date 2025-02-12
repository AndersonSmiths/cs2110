package Game;

import java.util.HashMap;

public class Game {

    /*
    A standard starting chess board
     */
    private Board board = new Board();


    /**
     * Determines whether a pawn can properly move
     * according to the rules of chess
     */
    private boolean canMove(){

    }

    private void movePawn(String square) {
        HashMap<Piece, String> map = board.getBoard();
        assert map.containsKey(square); // ensures that the peice actually exists

        // if first move pawn can move up two
        if (map.get(square).hasMoved == false) {
            // you can move up 2, up 1, or take if available

            map.get(square).hasMoved = true;
        }

        // if already moved, can only move up


    }
}
