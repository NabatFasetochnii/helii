package com.ch.helius.com.ch.helius.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.com.ch.helius.SimpleDirectionGestureDetector;
import com.ch.helius.com.ch.helius.game_objects.GamePers;

public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private HeliusGameClass hc;
    private OrthographicCamera camera;
    //Texture img;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private final String GAMESCREEN_TAG = "GAMESCREEN_TAG";
    private boolean left = false, right = false, up = false, down = false;
    private final int SPEED = 20;
    GamePers gamePers;

    GameScreen(GameWorld gameWorld, HeliusGameClass hc, final int LEVEL, final OrthographicCamera camera) {
        this.camera = camera;
        this.hc = hc;
        this.gameWorld = gameWorld;
        Gdx.app.log(GAMESCREEN_TAG, GAMESCREEN_TAG);

        //camera.position.set(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        tiledMap = new TmxMapLoader().load("map1_1.tmx");
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
        Gdx.input.setInputProcessor(gestureDetector);
        Vector2 a = new Vector2(0f, 0f);
        gamePers = new GamePers(0, 0, 0, 0, a, a, " ");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // Gdx.app.log( GAMESCREEN_TAG, "Render_GS" );
        Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //camera.translate(50, 50);
        move();

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        gamePers.update(delta);
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

    boolean move() {

        if (up) {
            camera.translate(0, SPEED);
            return true;
        }
        if (down) {
            camera.translate(0, -SPEED);
            return true;
        }
        if (left) {
            camera.translate(-SPEED, 0);
            return true;
        }
        if (right) {
            camera.translate(SPEED, 0);
            return true;
        }

        return false;
    }

}
