package lk.ijse.dep.service;

public interface Board {
    int NO_OF_ROWS=5;
    int NO_OF_COLS=6;

    BoardUI getBoardUI();
    int findNextAvailableSpot(int col);
    boolean isLegalMove(int col);
    boolean existLegalMoves();
    void updateMove(int col, Piece move);
    Winner findWinner();
}
