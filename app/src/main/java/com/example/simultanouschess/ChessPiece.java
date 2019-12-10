package com.example.simultanouschess;

import java.util.Set;

enum Side {
    BLACK, WHITE;
}

abstract public class ChessPiece {
    private Side side;
    private Position position;

    public ChessPiece(Side side){
        this.side = side;
    }

    public Side getSide(){
        return side;
    }

    abstract public Set<Position> getPossibleMoves(Board board);
}
