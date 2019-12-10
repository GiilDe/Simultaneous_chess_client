package com.example.simultanouschess;

public class Position {
    public int getX() {
        return x;
    }
    private int x;

    public int getY() {
        return y;
    }

    private int y;

    public Position(int x, int y){
        if(x > 8 || x < 0 || y > 8 || y < 0){
            //do something
        }
        this.x = x;
        this.y = y;
    }

    public void setPosition(int x, int y){
        if(x > 8 || x < 0 || y > 8 || y < 0){
            //do something
        }
        this.x = x;
        this.y = y;
    }
}
