package com.ch.helius.com.ch.helius.game;

import com.ch.helius.com.ch.helius.game_objects.HeliusPers;


public class GameWorld {

    private final String WORLD_TAG = "WORLD_TAG";
    private HeliusPers heliusPers;


    public GameWorld() {
        heliusPers = new HeliusPers( 1f, 1f, 25, 25 );


    }

    public void update(float delta) {
       // Gdx.app.log( WORLD_TAG, "update" );


       //Gdx.app.log( WORLD_TAG, 1./delta + "" );
    }

    public HeliusPers getHeliusPers(){
        return heliusPers;
    }

    public void setLevel(final int l){


    }
}
