package com.ch.helius.com.ch.helius.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.ch.helius.HeliusGameClass;

public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private HeliusGameClass hc;

    public GameScreen(GameWorld gameWorld, HeliusGameClass hc, final int LEVEL){

        this.hc = hc;
        this.gameWorld = gameWorld;

        //Gdx.input.setInputProcessor( new InputHandler( gameWorld.getHeliusPers() ) );

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {//TODO AAAAAAAAAAAAAAAAAAAAAAAAAAA


        Gdx.gl.glClearColor( 1, 1, 1f / 255, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

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

    }
}
