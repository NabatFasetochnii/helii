package com.ch.helius.com.ch.helius.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.com.ch.helius.AssetLoader;

public class GamePers extends Actor {

    private static int SPEED = 20;
    private final int WIDTH = (int) ((Gdx.graphics.getWidth() / 3) / 1.22),
            HEIGHT = Gdx.graphics.getWidth() / 3;
    private Body gPers, cPers, swordLine;
    private float x, y;// rotation;
    private SpriteBatch sb;
    private float time;
    private Animation<TextureRegion> ggAnimation;
    private TextureRegion frame;

    public GamePers(float x, float y) {

        this.x = x;
        this.y = y;

        sb = new SpriteBatch();
        time = 0;

        gPers = HeliusGameClass.getGameWorld()
                .createBox(BodyDef.BodyType.KinematicBody, (int) (this.WIDTH / 1.05), (int) (this.HEIGHT / 1.05), 1f);

        cPers = HeliusGameClass.getGameWorld()
                .createCircle(BodyDef.BodyType.KinematicBody, WIDTH / 2.1f, 1f);

        swordLine = HeliusGameClass.getGameWorld().createBox(BodyDef.BodyType.KinematicBody, (float) HEIGHT, WIDTH / 5f, 1);

        ggAnimation = new Animation<TextureRegion>
                (0.03f, AssetLoader.getAtlasGG().findRegions("gg"));
    }

    public static int getSpeed() {
        return SPEED;
    }

    public void update(float delta) {

        time += delta;
        frame = ggAnimation.getKeyFrame(time, true);

        sb.begin();
        sb.draw(frame, x, y, WIDTH, HEIGHT);
        sb.end();

    }

    void setSPEED(int speed) {
        SPEED = speed;
    }


    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

}
