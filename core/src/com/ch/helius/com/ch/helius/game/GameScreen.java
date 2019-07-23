package com.ch.helius.com.ch.helius.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.com.ch.helius.SimpleDirectionGestureDetector;

public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private final String GAMESCREEN_TAG = "GAMESCREEN_TAG";
    private boolean left = false, right = false, up = false, down = false;

    GameScreen(HeliusGameClass hc, final int LEVEL) {
        this.camera = MenuScreen.getCam();
        this.gameWorld = HeliusGameClass.getGameWorld();
        Gdx.app.log(GAMESCREEN_TAG, GAMESCREEN_TAG);

        tiledMap = new TmxMapLoader().load("map2.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

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

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
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
        tiledMap.dispose();

    }

    private void move() {

        if (up) {
            camera.translate(0, GameWorld.getSpeed());

        }
        if (down) {
            camera.translate(0, -GameWorld.getSpeed());

        }
        if (left) {
            camera.translate(-GameWorld.getSpeed(), 0);

        }
        if (right) {
            camera.translate(GameWorld.getSpeed(), 0);
        }

    }

}
