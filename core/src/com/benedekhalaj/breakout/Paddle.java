package com.benedekhalaj.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    private int x;
    private final int y, width, height;

    public Paddle(int y, int width, int height) {
        this.x = Gdx.input.getX();
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update() {
        x = Gdx.input.getX() - (width / 2);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(x, y, width, height);
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
}
