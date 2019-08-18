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

    GameScreen(HeliusGameClass hc, final int LEVEL) {
        this.gameWorld = HeliusGameClass.getGameWorld();
        Gdx.app.log(GAMESCREEN_TAG, GAMESCREEN_TAG);

        SimpleDirectionGestureDetector gestureDetector = new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onLeft() {

                Gdx.app.log(GAMESCREEN_TAG, "left");
                GamePers.getgPers().setLinearVelocity(-GamePers.getSpeed(), 0);

                GamePers.setRun(true);
                GamePers.setFlip(false);

//                MenuScreen.getCam().translate(-GamePers.getSpeed(), 0);
            }

            @Override
            public void onRight() {

                Gdx.app.log(GAMESCREEN_TAG, "right");
                GamePers.getgPers().setLinearVelocity(GamePers.getSpeed(), 0);

                GamePers.setRun(true);
                GamePers.setFlip(true);
//                MenuScreen.getCam().translate(GamePers.getSpeed(), 0);
            }

            @Override
            public void onUp() {

                Gdx.app.log(GAMESCREEN_TAG, "up");
                GamePers.getgPers().setLinearVelocity(0, GamePers.getSpeed());

                GamePers.setRun(true);
                GamePers.setUp_flip(true);
//                MenuScreen.getCam().translate(0, GamePers.getSpeed());
            }

            @Override
            public void onDown() {

                Gdx.app.log(GAMESCREEN_TAG, "down");
                GamePers.getgPers().setLinearVelocity(0, -GamePers.getSpeed());

                GamePers.setRun(true);
                GamePers.setUp_flip(false);
//                MenuScreen.getCam().translate(0, -GamePers.getSpeed());
            }

            @Override
            public void onTap() {

                GamePers.getgPers().setLinearVelocity(0, 0);
//                MenuScreen.getCam().translate(0, 0);

                GamePers.setRun(false);

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

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        MenuScreen.getCam().lookAt(GamePers.getgPers().getPosition().x, GamePers.getgPers().getPosition().y, 0);
        gameWorld.update(delta);

        MenuScreen.getCam().update();
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

        gameWorld.gameDispose();
    }


}
