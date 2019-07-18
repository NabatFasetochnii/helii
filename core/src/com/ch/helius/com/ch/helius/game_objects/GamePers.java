package com.ch.helius.com.ch.helius.game_objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ch.helius.com.ch.helius.AssetLoader;

public class GamePers {

    private Vector2 position, velocity, acceleration;
    private float x, y, rotation;
    private int width, height;
    private SpriteBatch sb;
    private TextureRegion texture;
    private Sprite sprite;
    private String FILE_PATH;
    private float time;
    private Animation<TextureRegion> ggAnimation;
    private TextureRegion frame;

    public GamePers(float x, float y, int width, int height, Vector2 velocity, Vector2 acceleration,  final String file_texture) {
//Vector2 velocity, Vector2 acceleration,
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        position = new Vector2(x, y);
        //this.velocity = velocity;
        //this.acceleration = acceleration;
        this.FILE_PATH = file_texture;

        sb = new SpriteBatch();
        //texture = new Texture(Gdx.files.internal(FILE_PATH));
        //texture = AssetLoader.getGgTexture();
       //sprite = new Sprite(texture);
        time = 0;

        ggAnimation = new Animation<TextureRegion>(0.07f, AssetLoader.getAtlasGG().findRegions("gg"));
    }

/*
    public double getX() {

        return x;
    }

    public double getY() {

        return y;
    }

    public void setX(float X) {
        this.x = X;
    }

    public void setX(double X) {
        this.x = (float) X;
    }

    public void setY(float Y) {
        this.y = Y;
    }

    public void setY(double Y) {
        this.y = (float) Y;
    }
*/

    public void update(float delta) {

        time +=delta;
        frame = ggAnimation.getKeyFrame(time, true);
        sb.begin();
        sb.draw(frame, 50, 50);
        // sprite.draw(sb);
        sb.end();

    }

}
