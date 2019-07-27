package com.ch.helius.com.ch.helius.game_objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.com.ch.helius.AssetLoader;
import com.ch.helius.com.ch.helius.game.MenuScreen;

public class GamePers extends Actor {

    private static int SPEED = 10;
    private static Body gPers;
    private final int WIDTH = 100,
            HEIGHT = (int) (WIDTH * 1.22);
    private Body cPers;
    private Body swordLine;
    private float x;
    private float y;// rotation;
    private SpriteBatch sb;
    private float time;
    private Animation<TextureRegion> ggAnimation;
    private TextureRegion frame;

    public GamePers(int x, int y) {

        this.x = x;
        this.y = y;

        sb = new SpriteBatch();
        time = 0;

        gPers = HeliusGameClass.getGameWorld()
                .createBox(BodyDef.BodyType.DynamicBody,
                        x,
                        y,
                        (int) ((WIDTH / 1.5)),
                        (int) ((HEIGHT / 1.5)), 1f);

//                   gPers.set

//                    cPers = HeliusGameClass.getGameWorld()
//                .createCircle(BodyDef.BodyType.KinematicBody, x, y, WIDTH / 2.1f, 1f);
//
//               swordLine = HeliusGameClass.getGameWorld().createBox(BodyDef.BodyType.KinematicBody, x, y, (float) HEIGHT, WIDTH / 5f, 1);

        ggAnimation = new Animation<TextureRegion>
                (0.03f, AssetLoader.getAtlasGG().findRegions("gg"));


    }

    public static Body getgPers() {
        return gPers;
    }

    public static int getSpeed() {
        return SPEED;
    }

    public TextureRegion getFrame() {
        return frame;
    }

    public void update(float delta) {

        time += delta;
        frame = ggAnimation.getKeyFrame(time, true);

        sb.setProjectionMatrix(MenuScreen.getCam().combined);

        sb.begin();
        sb.draw(frame, gPers.getPosition().x * HeliusGameClass.getGameWorld().getPIX_TO_M() - WIDTH / 2f,
                gPers.getPosition().y * HeliusGameClass.getGameWorld().getPIX_TO_M() - HEIGHT / 2f,
                WIDTH, HEIGHT);
        sb.end();

    }


    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public Body getcPers() {
        return cPers;
    }

    public Body getSwordLine() {
        return swordLine;
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
