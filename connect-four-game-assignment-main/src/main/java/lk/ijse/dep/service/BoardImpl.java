package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public class BoardImpl implements Board {

    private final Piece[][] pieces;
    private final BoardUI boardUI;

    public BoardImpl(final BoardUI boardUI) {
        this.pieces = new Piece[6][5];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                this.pieces[i][j] = Piece.EMPTY;
            }
        }
        this.boardUI = boardUI;
    }
    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(final int col) {
        for (int i = 0; i < 5; ++i) {
            if (this.pieces[col][i] == Piece.EMPTY) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(final int col) {
        return this.findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int col = 0; col < 6; ++col) {
            if (this.isLegalMove(col)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(final int col, final Piece move) {
        this.pieces[col][this.findNextAvailableSpot(col)] = move;
    }

    @Override
    public Winner findWinner() {
        for (int col = 0; col < 6; ++col) {
            int count = 1;
            for (int rows = this.findNextAvailableSpot(col), row = 1; row < ((rows == -1) ? 5 : rows); ++row) {
                if (this.pieces[col][row] == this.pieces[col][row - 1]) {
                    if (++count == 4) {
                        return new Winner(this.pieces[col][row], col, row - 3, col, row);
                    }
                }
                else {
                    count = 1;
                    if (row >= 2) {
                        break;
                    }
                }
            }
        }
        for (int row2 = 0; row2 < 5; ++row2) {
            int count = 1;
            for (int col2 = 1; col2 < 6; ++col2) {
                if (this.pieces[col2][row2] == this.pieces[col2 - 1][row2] && this.pieces[col2][row2] != Piece.EMPTY) {
                    if (++count == 4) {
                        return new Winner(this.pieces[col2][row2], col2 - 3, row2, col2, row2);
                    }
                }
                else {
                    count = 1;
                    if (col2 >= 3) {
                        break;
                    }
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}