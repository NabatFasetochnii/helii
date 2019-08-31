package com.ch.helius.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.Systems.AnimationSystem;
import com.ch.helius.Systems.CollisionSystem;
import com.ch.helius.Systems.PhysicsDebugSystem;
import com.ch.helius.Systems.PhysicsSystem;
import com.ch.helius.Systems.PlayerControlSystem;
import com.ch.helius.Systems.RenderingSystem;

public class GameScreen implements Screen {

    private final String GAMESCREEN_TAG = "GAMESCREEN_TAG";
    private SpriteBatch spriteBatch;
    private static PooledEngine engine;
    private GameWorld gameWorld;
    private OrthographicCamera cam;

    GameScreen(HeliusGameClass hc) {

        this.gameWorld = HeliusGameClass.getGameWorld();
        Gdx.app.log(GAMESCREEN_TAG, GAMESCREEN_TAG);

        /*SimpleDirectionGestureDetector gestureDetector = new SimpleDirectionGestureDetector
                (new SimpleDirectionGestureDetector.DirectionListener() {

                    @Override
                    public void onLeft() {

                        Gdx.app.log(GAMESCREEN_TAG, "left");

                        GamePers.setRunSpeed(-1, 0);

                        GamePers.setRun(true);
                        GamePers.setFlip(false);

                    }

                    @Override
                    public void onRight() {

                        Gdx.app.log(GAMESCREEN_TAG, "right");

                        GamePers.setRunSpeed(1, 0);

                        GamePers.setRun(true);
                        GamePers.setFlip(true);

                    }

                    @Override
                    public void onUp() {

                        Gdx.app.log(GAMESCREEN_TAG, "up");

                        GamePers.setRunSpeed(0, 1);

                        GamePers.setRun(false);

                    }

                    @Override
                    public void onDown() {

                        Gdx.app.log(GAMESCREEN_TAG, "down");

                    }

                    @Override
                    public void onTap() {

//                     GamePers.setRunSpeed(0, 0);

                        GamePers.setTupRunSpeed();

                     GamePers.setRun(false);
                     GamePers.setHit(true);

                     GamePers.swordFight();

                     Gdx.app.log(GAMESCREEN_TAG, "tap");
                    }
                });
//h/w=1.22
        Gdx.input.setInputProcessor(gestureDetector);
*/

        spriteBatch = new SpriteBatch();
        RenderingSystem renderingSystem = new RenderingSystem(spriteBatch);
        cam = renderingSystem.getCamera();
        spriteBatch.setProjectionMatrix(cam.combined);

        cam.zoom = 0.4f;

        engine = new PooledEngine();

        // add all the relevant systems our engine should run
        engine.addSystem(new AnimationSystem());
        engine.addSystem(renderingSystem);
        engine.addSystem(new PhysicsSystem(GameWorld.getWorld()));
        engine.addSystem(new PhysicsDebugSystem(GameWorld.getWorld(), renderingSystem.getCamera()));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new PlayerControlSystem());

//        Gdx.app.log(GAMESCREEN_TAG, mapWidth+" "+mapHeight);
    }

    public static PooledEngine getEngine() {
        return engine;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameWorld.update(delta);

        cam.update();
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
