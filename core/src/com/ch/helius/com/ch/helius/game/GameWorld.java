package com.ch.helius.com.ch.helius.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ch.helius.com.ch.helius.game_objects.GamePers;
import com.ch.helius.com.ch.helius.game_objects.HeliusPers;


public class GameWorld { //TODO static

    private final String WORLD_TAG = "WORLD_TAG";
    private static GamePers helius;
    private static int SPEED = 20;

    public GameWorld() {

        Gdx.app.log(WORLD_TAG, WORLD_TAG);

        Vector2 a = new Vector2(0f, 0f);
        helius = new GamePers(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 4f,
                (int) ((Gdx.graphics.getWidth() / 3) / 1.22), Gdx.graphics.getWidth() / 3, a, a);

    }

    void update(float delta) {

        helius.update(delta);


    }

    public void setLevel(final int l) {

        Gdx.app.log(WORLD_TAG, "setLevel");

    }

    public static HeliusPers getHelius() {
        return (HeliusPers) helius;
    }

    static int getSpeed() {
        return SPEED;
    }

    void setSPEED(int speed) {
        SPEED = speed;
    }
}
