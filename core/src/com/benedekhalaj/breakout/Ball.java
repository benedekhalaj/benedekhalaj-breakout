package com.benedekhalaj.breakout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    private int x, y, size, xSpeed, ySpeed;
    Color color = Color.WHITE;


    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update() {
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, size);
    }

    public void checkCollision(Paddle paddle) {
        color = (collidesWith(paddle)) ? Color.GREEN : Color.WHITE;
    }

    private boolean collidesWith(Paddle paddle) {
        return x - size <= paddle.getX() + paddle.getWidth() &&
            x + size >= paddle.getX() &&
            y - size <= paddle.getY() + paddle.getHeight() &&
            y + size >= paddle.getY();
    }
}
