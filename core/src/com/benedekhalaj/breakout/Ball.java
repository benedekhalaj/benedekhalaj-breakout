package com.benedekhalaj.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    private int x, y, size, xSpeed, ySpeed;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    private void reverseXSpeed() {
        xSpeed = -xSpeed;
    }

    private void reverseYSpeed() {
        ySpeed = -ySpeed;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;
        if (x - size < 0 || x + size > Gdx.graphics.getWidth()) {
            reverseXSpeed();
        }
        if (y - size < 0 || y + size > Gdx.graphics.getHeight()) {
            reverseYSpeed();
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(x, y, size);
    }

    public void checkCollision(Paddle paddle) {
        if (collidesWith(paddle)) {
            reverseYSpeed();
        }
    }

    private boolean collidesWith(Paddle paddle) {
        return x - size <= paddle.getX() + paddle.getWidth() &&
            x + size >= paddle.getX() &&
            y - size <= paddle.getY() + paddle.getHeight() &&
            y + size >= paddle.getY();
    }
}
