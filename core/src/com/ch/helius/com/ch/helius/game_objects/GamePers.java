package com.ch.helius.com.ch.helius.game_objects;

import com.badlogic.gdx.math.Vector2;

public class GamePers {

    private Vector2 position, velocity, acceleration;
    private float x, y, rotation;
    private int width, height;

    GamePers(float x, float y, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        position = new Vector2( x, y );
        velocity = new Vector2( 0, 0 );
        acceleration = new Vector2 (0, 0);

    }


    public double getX(){

        return x;
    }


    public double getY(){

        return y;
    }

    public void update( float delta ){


    }


}
