package com.ch.helius.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ch.helius.AssetLoader;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.game.GameScreen;
import com.ch.helius.game.GameWorld;

public class GamePers extends Actor {

    private static int mSPEED = 10;
    private static float mSWORD_SPEED = 10;
    private static Body gPers;
    private static Body sword;
    private static boolean flip = false;
    private static boolean run = false;
    private static boolean hit = false;
    private static RevoluteJoint revoluteJoint;
    private static boolean alive = true;
    private final int WIDTH = 40;
    private final int HEIGHT = (int) (WIDTH * 1.22);
    private final int HIT_WIDTH = (int) (WIDTH * 1.42);
    private Animation<TextureRegion> runAnim, hitAnim;
    private float time = 0;
    private float t = 0;
    private float x;
    private float y;
    private SpriteBatch sb;


    public GamePers(int x, int y) {

        this.x = x;
        this.y = y;

        sb = new SpriteBatch();

        sword = HeliusGameClass.getGameWorld()
                .createBox(BodyDef.BodyType.DynamicBody,
                        x + 1,
                        y, ((WIDTH / 7f)), ((WIDTH / 2.3f)), 0.1f);

        sword.setFixedRotation(false);

        gPers = HeliusGameClass.getGameWorld()
                .createBox(BodyDef.BodyType.DynamicBody,
                        x,
                        y,
                        ((WIDTH / 2.5f)),
                        ((HEIGHT / 2.5f)), 100f
                )
        ;

        swordInGG(sword, gPers);

        sword.setUserData("gg_sword");
        gPers.setUserData("gg");


        runAnim = new Animation<TextureRegion>(0.03f, AssetLoader.getGgTexture_run());
        runAnim.setPlayMode(Animation.PlayMode.LOOP);
        hitAnim = new Animation<TextureRegion>(0.01f, AssetLoader.getGgTexture_hit());
        hitAnim.setPlayMode(Animation.PlayMode.LOOP);

    }

    public static boolean isAlive() {

        return alive;
    }

    public static void setAlive(boolean alive) {
        GamePers.alive = alive;
    }

    public static RevoluteJoint getRevoluteJoint() {
        return revoluteJoint;
    }

    public static float getmSWORD_SPEED() {
        return mSWORD_SPEED;
    }

    public static void setmSWORD_SPEED(float mSWORD_SPEED) {
        GamePers.mSWORD_SPEED = mSWORD_SPEED;
    }

    public static Body getSword() {

        return sword;
    }

    public static boolean isHit() {
        return hit;
    }

    public static void setHit(boolean hit) {
        GamePers.hit = hit;
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

    public static void setRunSpeed(float x, float y) {
        gPers.setLinearVelocity(x * mSPEED, y * mSPEED);
        sword.setLinearVelocity(x * mSPEED, y * mSPEED);
    }

    public static void setTupRunSpeed() {

        gPers.setLinearVelocity(0, gPers.getLinearVelocity().y);
        sword.setLinearVelocity(0, sword.getLinearVelocity().y);
    }

    public static void setRunSpeed(Vector2 vector2) {

        gPers.setLinearVelocity(vector2.scl(mSPEED));
        sword.setLinearVelocity(vector2.scl(mSPEED));
    }

    public static void swordFight() {

        if (flip) {
            revoluteJoint.setMotorSpeed(mSWORD_SPEED);
//                revoluteJoint.enableMotor(true);
        } else {
            revoluteJoint.setMotorSpeed(-mSWORD_SPEED);
        }

        Gdx.app.log("GPers", "sword_hit");

    }

    private void swordInGG(Body sword, Body gg) {
        RevoluteJointDef revoluteJointDef = new RevoluteJointDef();

        revoluteJointDef.bodyA = gg;
        revoluteJointDef.bodyB = sword;

        revoluteJointDef.collideConnected = false;

        Vector2 vector2 = new Vector2(0f, 0.15f);
        Vector2 vector21 = new Vector2(0f, 0.1f);

        revoluteJointDef.localAnchorA.set(gg.getLocalCenter().add(vector2));
        revoluteJointDef.localAnchorB.set(sword.getLocalCenter().add(vector21));

        revoluteJointDef.enableMotor = true;
        revoluteJointDef.motorSpeed = 0f;
        revoluteJointDef.maxMotorTorque = 4f;

        revoluteJointDef.enableLimit = true;
        revoluteJointDef.lowerAngle = -1.5f;
        revoluteJointDef.upperAngle = 1.5f;

        revoluteJoint = (RevoluteJoint) GameWorld.getWorld().createJoint(revoluteJointDef);

    }

    public void update(float delta) {

        sb.setProjectionMatrix(GameScreen.getCam().combined);

        swordMeh();

        if (Math.abs(gPers.getLinearVelocity().x) < 1) {
            setRun(false);
        }

        sb.begin();

        p_draw(delta);

        sb.end();

    }

    private void swordMeh() {
        if (Math.abs(revoluteJoint.getJointAngle() - revoluteJoint.getLowerLimit()) < 1.e-1
        ) {
            Gdx.app.log("GPers", "get_limit");
            revoluteJoint.setMotorSpeed(mSWORD_SPEED - 5);
        } else if (Math.abs(revoluteJoint.getJointAngle() - revoluteJoint.getUpperLimit()) < 1.e-1) {
            revoluteJoint.setMotorSpeed(-mSWORD_SPEED + 5);
        }


        if (Math.abs(revoluteJoint.getJointAngle()) < 1.e-1 &&
                Math.abs(Math.abs(revoluteJoint.getJointSpeed()) - mSWORD_SPEED + 5) < 1.e-1) {

            Gdx.app.log("GPers", "stop_hit");

            revoluteJoint.setMotorSpeed(0);
        }
    }

    private void p_draw(float delta) {

        time += delta;
        if (run) {

            sb.draw(runAnim.getKeyFrame(time),
                    flip ? WIDTH + needX() - WIDTH / 2f
                            :
                            needX() - WIDTH / 2f,
                    needY() - HEIGHT / 2f,
                    flip ? -WIDTH : WIDTH, HEIGHT);

        } else {

            if (hit) {
                t += delta;
                sb.draw(hitAnim.getKeyFrame(t),
                        flip ? WIDTH + needX() - WIDTH / 2f + WIDTH / 5f
                                :
                                needX() - WIDTH / 2f - WIDTH / 5f,
                        needY() - HEIGHT / 2f,
                        flip ? -HIT_WIDTH : HIT_WIDTH, HEIGHT);
//                hit = false;

                if (hitAnim.isAnimationFinished(t)) {
                    hit = false;
                    t = 0;
                }
            } else {
                sb.draw(AssetLoader.getGgTexture_run().get(0),
                        flip ? WIDTH + needX() - WIDTH / 2f
                                :
                                needX() - WIDTH / 2f,
                        needY() - HEIGHT / 2f,
                        flip ? -WIDTH : WIDTH, HEIGHT);
            }
        }
    }

    private float needX() {
        return gPers.getPosition().x * HeliusGameClass.getGameWorld().getPIX_TO_M();
    }

    private float needY() {
        return gPers.getPosition().y * HeliusGameClass.getGameWorld().getPIX_TO_M();
    }

    public boolean isFlip() {
        return flip;
    }

    public static void setFlip(boolean flip) {
        GamePers.flip = flip;
    }

    public int getHIT_WIDTH() {
        return HIT_WIDTH;
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
