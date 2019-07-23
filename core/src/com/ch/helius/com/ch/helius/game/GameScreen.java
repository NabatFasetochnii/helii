package com.ch.helius.com.ch.helius.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.com.ch.helius.SimpleDirectionGestureDetector;
import com.ch.helius.com.ch.helius.game_objects.GamePers;

public class GameScreen implements Screen {

    private final String GAMESCREEN_TAG = "GAMESCREEN_TAG";
    private GameWorld gameWorld;
    private boolean left = false, right = false, up = false, down = false;

    GameScreen(HeliusGameClass hc, final int LEVEL) {
        this.gameWorld = HeliusGameClass.getGameWorld();
        Gdx.app.log(GAMESCREEN_TAG, GAMESCREEN_TAG);

        SimpleDirectionGestureDetector gestureDetector = new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onLeft() {
                left = true;
                right = false;
                Gdx.app.log(GAMESCREEN_TAG, "left");
            }

            @Override
            public void onRight() {
                left = false;
                right = true;
                Gdx.app.log(GAMESCREEN_TAG, "right");
            }

            @Override
            public void onUp() {
                down = false;
                up = true;
            }

            @Override
            public void onDown() {
                down = true;
                up = false;
            }

            @Override
            public void onTap() {
                down = up = left = right = false;

                Gdx.app.log(GAMESCREEN_TAG, "tap");
            }
        });
//h/w=1.22
        Gdx.input.setInputProcessor(gestureDetector);

//        Gdx.app.log(GAMESCREEN_TAG, mapWidth+" "+mapHeight);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        move();

        MenuScreen.getCam().update();

        gameWorld.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
//        tiledMap.dispose();
        gameWorld.gameDispose();
    }

    private void move() {

        if (up) {
            MenuScreen.getCam().translate(0, GamePers.getSpeed());

        }
        if (down) {
            MenuScreen.getCam().translate(0, -GamePers.getSpeed());

        }
        if (left) {
            MenuScreen.getCam().translate(-GamePers.getSpeed(), 0);

        }
        if (right) {
            MenuScreen.getCam().translate(GamePers.getSpeed(), 0);
        }

    }

}
