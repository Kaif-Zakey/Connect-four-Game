package lk.ijse.dep.service;

public class AiPlayer extends Player {
    public AiPlayer(final Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {
        do {
            col = (int)Math.floor(Math.random() * 6.0);
        } while (!this.board.isLegalMove(col));


        this.board.updateMove(col, Piece.GREEN);
        this.board.getBoardUI().update(col, false);
        final Winner winner = this.board.findWinner();


        if (winner.getWinningPiece() != Piece.EMPTY) {
            this.board.getBoardUI().notifyWinner(winner);
        }

        else if (!this.board.existLegalMoves()) {
            this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }
}

