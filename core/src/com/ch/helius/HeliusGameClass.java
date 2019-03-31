package com.ch.helius;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.ch.helius.com.ch.helius.AssetLoader;
import com.ch.helius.com.ch.helius.game.GameWorld;
import com.ch.helius.com.ch.helius.game.MenuScreen;


public class HeliusGameClass extends Game {


	private final String APP_TAG = "APP_TAG";
	private GameWorld gameWorld;

	@Override
	public void create () {

		AssetLoader.load();
		gameWorld = new GameWorld();
		this.setScreen( new MenuScreen( gameWorld, this ) );

	}

	@Override
	public void render(){
		super.render();
		//Gdx.gl.glClearColor( 1, 1, 1f / 255, 1 );
		//Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		gameWorld.update( Gdx.graphics.getDeltaTime() );


	}

	@Override
	public void resize(int width, int height) {
		//super.resize(  );

	}


	@Override
	public void pause(){
		super.pause();
		Gdx.app.log( APP_TAG, "HeliusGC_pause" );
	}

	@Override
	public void resume(){
		super.resume();
		Gdx.app.log( APP_TAG, "HeliusGC_resume" );
	}

	@Override
	public void dispose(){
		super.dispose();
		Gdx.app.log( APP_TAG, "HeliusGC_dispose" );
		AssetLoader.dispose();

	}
}
