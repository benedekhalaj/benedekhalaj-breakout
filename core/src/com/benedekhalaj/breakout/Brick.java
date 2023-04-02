package com.benedekhalaj.breakout;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Brick {
    private int x, y, width, height;
    private boolean destroyed;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroyed = false;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        if (!destroyed) {
            shapeRenderer.rect(x, y, width, height);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed() {
        this.destroyed = true;
    }
}
