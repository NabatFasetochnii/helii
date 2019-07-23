package com.ch.helius.com.ch.helius.game_objects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ch.helius.HeliusGameClass;

public class Wall {//TODO create wall

    Body wall;
    float width, height;

    Wall(float width, float height) {

        this.width = width;
        this.height = height;

        wall = HeliusGameClass.getGameWorld().createBox(BodyDef.BodyType.StaticBody, this.width, this.height, 1);

    }

}
