package lk.ijse.dep.service;

public class HumanPlayer extends Player {
    public HumanPlayer(final Board board) {
        super(board);
    }

    @Override
    public void movePiece(final int col) {
        if (this.board.isLegalMove(col)) {
            this.board.updateMove(col, Piece.BLUE);
            this.board.getBoardUI().update(col, true);
            final Winner winner = this.board.findWinner();

            if (winner.getWinningPiece() != Piece.EMPTY) {
                this.board.getBoardUI().notifyWinner(winner);
            }
            else if (!this.board.existLegalMoves()) {
                this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
}

