package com.example.simultanouschess;

public class Move {
    Position pieceToMove;
    Position positionToMoveTo;

    public Move(Position pieceToMove, Position positionToMoveTo) {
        this.pieceToMove = pieceToMove;
        this.positionToMoveTo = positionToMoveTo;
    }

    public Move(int[] coordinates) {
        this.pieceToMove = new Position(coordinates[0], coordinates[1]);
        this.positionToMoveTo = new Position(coordinates[2], coordinates[3]);
    }

    public static Move createVictory(){
        return new Move(new int[]{-1, -1, -1, -1});
    }

    public int[] toIntArray(){
        return new int[]{pieceToMove.getX(), pieceToMove.getY(), positionToMoveTo.getX(), positionToMoveTo.getY()};
    }
}
