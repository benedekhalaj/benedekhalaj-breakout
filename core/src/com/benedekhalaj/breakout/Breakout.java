package com.benedekhalaj.breakout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Breakout extends ApplicationAdapter {
    ShapeRenderer shapeRenderer;
    List<Ball> balls = new ArrayList<>();
    Paddle paddle;
    Random r = new Random();

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        createBalls();
        createPaddle();
    }

    private void createBalls() {
        Ball e = new Ball(50, 50, 20, 5, 5);
        balls.add(e);
    }

    private void createPaddle() {
        paddle = new Paddle(20, 100, 10);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderBalls();
        renderPaddle();
        shapeRenderer.end();
    }

    private void renderBalls() {
        balls.forEach(b -> {
            b.update();
            b.checkCollision(paddle);
            b.draw(shapeRenderer);
        });
    }

    private void renderPaddle() {
        paddle.update();
        paddle.draw(shapeRenderer);
    }

}
