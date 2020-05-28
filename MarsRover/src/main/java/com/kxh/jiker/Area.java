package com.kxh.jiker;

public class Area {
    private int X;
    private int Y;
    private int originalx;
    private int originaly;

    public Area(int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.originalx = 0;
        this.originaly = 0;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getOriginalx() {
        return originalx;
    }

    public int getOriginaly() {
        return originaly;
    }
}
