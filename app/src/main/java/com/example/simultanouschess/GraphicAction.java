package com.example.simultanouschess;

abstract class GraphicAction {}

class HighLightChecker extends GraphicAction{
    public Position position;

    public HighLightChecker(Position position) {
        this.position = position;
    }
}

class MovePiece extends GraphicAction{
    public Position startPosition;
    public Position endPosition;

    public MovePiece(Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
}

class EatPiece extends GraphicAction{
    public Position startPosition;
    public Position endPosition;

    public EatPiece(Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
}
