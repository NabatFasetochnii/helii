package com.ch.helius.com.ch.helius.game_objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ch.helius.com.ch.helius.AssetLoader;

public class GamePers {

    private float x, y;// rotation;
    private int width, height;
    private SpriteBatch sb;
    private float time;
    private Animation<TextureRegion> ggAnimation;
    private TextureRegion frame;


    public GamePers(float x, float y, int width, int height, Vector2 velocity, Vector2 acceleration) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        sb = new SpriteBatch();
        time = 0;

        ggAnimation = new Animation<TextureRegion>(0.03f, AssetLoader.getAtlasGG().findRegions("gg"));
    }

    public void update(float delta) {

        time += delta;
        frame = ggAnimation.getKeyFrame(time, true);

        sb.begin();
        sb.draw(frame, x, y, width, height);
        sb.end();

    }

}
