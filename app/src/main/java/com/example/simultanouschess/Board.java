package com.example.simultanouschess;

public class Board {
    private ChessPiece[][] pieces;

    public Board() {
        pieces = new ChessPiece[8][8];

    }

    public Side getPieceSide(Position position){
        return getPiece(position).getSide();
    }
    public ChessPiece getPiece(Position position) {
        return pieces[position.getX()][position.getY()];
    }
}
