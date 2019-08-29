package com.ch.helius.com.ch.helius.game_objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.ch.helius.HeliusGameClass;

public class Enemy extends Actor {

    private final int MAX_HP = 5;
    private boolean dead = false;
    private int hp = MAX_HP;
    private int X, Y;
    private Body enemy_box;
    private Array<TextureAtlas.AtlasRegion> atlasRegions;


    public Enemy(int x, int y, float W, float H, Array<TextureAtlas.AtlasRegion> array) {

        this.X = x;
        this.Y = y;
        this.atlasRegions = array;

        enemy_box = HeliusGameClass.getGameWorld()
                .createBox(BodyDef.BodyType.DynamicBody, X, Y, W, H, 100f);
        enemy_box.setUserData("enemy");
    }

}
