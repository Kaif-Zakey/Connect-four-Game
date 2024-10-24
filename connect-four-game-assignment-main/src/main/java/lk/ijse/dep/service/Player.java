package lk.ijse.dep.service;

public abstract class Player {
    protected Board board;

    public Player(final Board board) {
        this.board = board;
    }

    public abstract void movePiece(final int pieces);

}
