package com.benedekhalaj.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    private int x, y, size, xSpeed, ySpeed;
    private boolean isColliding;
    private int brickCounter;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        isColliding = false;
        brickCounter = 0;
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
        if (brickCounter >= 5) {
            brickCounter = 0;
            if (ySpeed < 0) {
                ySpeed--;
            } else {
                ySpeed++;
            }
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(x, y, size);
    }

    public boolean checkCollision(Paddle paddle) {
        if (!isColliding && collidesWith(paddle)) {
            isColliding = true;
            reverseYSpeed();
            return true;
        }
        return false;
    }

    public boolean checkCollision(Brick brick) {
        if (!isColliding && !brick.isDestroyed() && collidesWith(brick)) {
            isColliding = true;
            reverseYSpeed();
            brick.setDestroyed();
            brickCounter++;
            return true;
        }
        return false;
    }

    private boolean collidesWith(Paddle paddle) {
        return x - size < paddle.getX() + paddle.getWidth() &&
            x + size > paddle.getX() &&
            y - size < paddle.getY() + paddle.getHeight() &&
            y + size > paddle.getY();
    }

    private boolean collidesWith(Brick brick) {
        return x - size < brick.getX() + brick.getWidth() &&
            x + size > brick.getX() &&
            y - size < brick.getY() + brick.getHeight() &&
            y + size > brick.getY();
    }

    public void setColliding(boolean colliding) {
        isColliding = colliding;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
