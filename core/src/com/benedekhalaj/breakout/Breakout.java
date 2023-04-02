package com.benedekhalaj.breakout;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Breakout extends ApplicationAdapter {
    ShapeRenderer shapeRenderer;
    Ball ball;
    List<Brick> bricks = new ArrayList<>();
    Paddle paddle;
    boolean isBallColliding = false;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        createBalls();
        createPaddle();
        createBricks();
    }

    private void createBalls() {
        ball = new Ball(50, 50, 10, 3, 3);
    }

    private void createPaddle() {
        paddle = new Paddle(20, 100, 10);
    }

    private void createBricks() {
        int blockWidth = 63;
        int blockHeight = 20;
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                bricks.add(new Brick(x, y, blockWidth, blockHeight));
            }
        }
        System.out.println(bricks.size());
    }

    private void preRender() {
        if (bricks.isEmpty()) {
            createBricks();
            ball.setSize(ball.getSize() - 1);
        }
    }

    @Override
    public void render() {
        preRender();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        isBallColliding = false;
        renderBall();
        renderPaddle();
        shapeRenderer.setColor(Color.RED);
        renderBricks();
        removeDestroyedBricks();
        ball.setColliding(isBallColliding);
        shapeRenderer.end();
    }

    private void renderBall() {
        ball.update();
        ball.draw(shapeRenderer);
    }

    private void renderPaddle() {
        paddle.update();
        if (ball.checkCollision(paddle)) {
            isBallColliding = true;
        };
        paddle.draw(shapeRenderer);
    }

    private void renderBricks() {
        bricks.forEach(brick -> {
            brick.draw(shapeRenderer);
            if (ball.checkCollision(brick)) {
                isBallColliding = true;
            };
        });
    }

    private void removeDestroyedBricks() {
        List<Brick> bricks = new ArrayList<>(this.bricks);
        bricks.stream()
            .filter(Brick::isDestroyed)
            .forEach(brick -> this.bricks.remove(brick));
    }
}
