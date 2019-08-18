package com.ch.helius.com.ch.helius.game_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.com.ch.helius.AssetLoader;
import com.ch.helius.com.ch.helius.game.MenuScreen;

public class GamePers extends Actor {

    private static int mSPEED = 10;
    private static Body gPers;
    private static boolean flip = false;
    private static boolean run = false;
    private static boolean up_flip = false;
    private final int WIDTH = 90,
            HEIGHT = (int) (WIDTH * 1.22);
    private float x;
    private float y;
    private SpriteBatch sb;

    public GamePers(int x, int y) {

        this.x = x;
        this.y = y;

        sb = new SpriteBatch();

        gPers = HeliusGameClass.getGameWorld()
                .createBox(BodyDef.BodyType.DynamicBody,
                        x,
                        y,
                        (int) ((WIDTH / 2.5f)),
                        (int) ((HEIGHT / 2.5f)), 10f
                )
        ;


    }

    public static boolean isUp_flip() {
        return up_flip;
    }

    public static void setUp_flip(boolean up_flip) {
        GamePers.up_flip = up_flip;
    }


    public static Body getgPers() {
        return gPers;
    }

    public static int getSpeed() {
        return mSPEED;
    }

    public static boolean isRun() {
        return run;
    }

    public static void setRun(boolean run) {
        GamePers.run = run;
    }

    public void swordUpdate() {


    }

    public void update(float delta) {

        sb.setProjectionMatrix(MenuScreen.getCam().combined);

        sb.begin();

        p_draw();

        sb.end();

    }


    private void p_draw() {
        if (run) {

            sb.draw(AssetLoader.getGgTexture()[2],
                    gPers.getPosition().x * HeliusGameClass.getGameWorld().getPIX_TO_M() - WIDTH / 2f,
                    gPers.getPosition().y * HeliusGameClass.getGameWorld().getPIX_TO_M() - HEIGHT / 2f,
                    WIDTH, WIDTH);
        } else {

            if (up_flip) {
                sb.draw(AssetLoader.getGgTexture()[1],
                        gPers.getPosition().x * HeliusGameClass.getGameWorld().getPIX_TO_M() - WIDTH / 2f,
                        gPers.getPosition().y * HeliusGameClass.getGameWorld().getPIX_TO_M() - HEIGHT / 2f,
                        WIDTH, HEIGHT);
            } else {

                sb.draw(AssetLoader.getGgTexture()[0],
                        flip ? WIDTH + gPers.getPosition().x * HeliusGameClass.getGameWorld().getPIX_TO_M() - WIDTH / 2f
                                :
                                gPers.getPosition().x * HeliusGameClass.getGameWorld().getPIX_TO_M() - WIDTH / 2f,
                        gPers.getPosition().y * HeliusGameClass.getGameWorld().getPIX_TO_M() - HEIGHT / 2f,
                        flip ? -WIDTH : WIDTH, HEIGHT);
            }
        }
    }

    public boolean isFlip() {
        return flip;
    }

    public static void setFlip(boolean flip) {
        GamePers.flip = flip;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    void setSPEED(int speed) {
        mSPEED = speed;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

}
