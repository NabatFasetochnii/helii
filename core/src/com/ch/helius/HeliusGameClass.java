package com.ch.helius;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ch.helius.game.GameWorld;
import com.ch.helius.game.MenuScreen;


public class HeliusGameClass extends Game {


    private static GameWorld gameWorld;
    private static SpriteBatch sb;
    private final String APP_TAG = "APP_TAG";

    public static OrthographicCamera getCam() {
        return cam;
    }

    private static OrthographicCamera cam;

    public static SpriteBatch getSb() {
        return sb;
    }

    public static GameWorld getGameWorld() {
        return gameWorld;
    }

    @Override
    public void create() {

        sb = new SpriteBatch();
        AssetLoader.load();
        gameWorld = new GameWorld();
        gameWorld.loudGP();
        this.setScreen(new MenuScreen(this));

    }

    @Override
    public void render() {
        super.render();

        //gameWorld.update( Gdx.graphics.getDeltaTime() );


    }

    @Override
    public void resize(int width, int height) {
        //super.resize(  );

    }

    @Override
    public void pause() {
        super.pause();
        Gdx.app.log(APP_TAG, "HeliusGC_pause");
    }

    @Override
    public void resume() {
        super.resume();
        Gdx.app.log(APP_TAG, "HeliusGC_resume");
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.log(APP_TAG, "HeliusGC_dispose");
        AssetLoader.dispose();

    }

}
