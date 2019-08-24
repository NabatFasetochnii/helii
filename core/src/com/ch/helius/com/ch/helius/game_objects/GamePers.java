package com.ch.helius.com.ch.helius.game_objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.com.ch.helius.AssetLoader;
import com.ch.helius.com.ch.helius.game.GameWorld;
import com.ch.helius.com.ch.helius.game.MenuScreen;

public class GamePers extends Actor {

    private static int mSPEED = 10;
    private static float mSWORD_SPEED = 10;
    private static Body gPers;
    private static Body sword;
    private static boolean flip = false;
    private static boolean run = false;
    private static boolean hit = false;
    private final int WIDTH = 40;
    private final int HEIGHT = (int) (WIDTH * 1.22);
    private final int HIT_WIDTH = (int) (WIDTH * 1.42);
    private Animation<TextureRegion> runAnim, hitAnim;
    private float time = 0;
    private float t = 0;
    private float x;
    private float y;
    private SpriteBatch sb;

    public static RevoluteJoint getRevoluteJoint() {
        return revoluteJoint;
    }

    private static RevoluteJoint revoluteJoint;

    public GamePers(int x, int y) {

        this.x = x;
        this.y = y;

        sb = new SpriteBatch();
        sword = HeliusGameClass.getGameWorld()
                .createBox(BodyDef.BodyType.DynamicBody,
                        x + 1,
                        y, (int) ((WIDTH / 7f)), (int) ((WIDTH / 2f)), 0.1f);

        sword.setFixedRotation(false);

        gPers = HeliusGameClass.getGameWorld()
                .createBox(BodyDef.BodyType.DynamicBody,
                        x,
                        y,
                        (int) ((WIDTH / 2.5f)),
                        (int) ((HEIGHT / 2.5f)), 10f
                )
        ;

        swordInGG(revoluteJoint, sword, gPers);

        runAnim = new Animation<TextureRegion>(0.03f, AssetLoader.getGgTexture_run());
        runAnim.setPlayMode(Animation.PlayMode.LOOP);
        hitAnim = new Animation<TextureRegion>(0.01f, AssetLoader.getGgTexture_hit());
        hitAnim.setPlayMode(Animation.PlayMode.LOOP);

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

    public static void setRunSpeed(Vector2 vector2) {

        gPers.setLinearVelocity(vector2.scl(mSPEED));
        sword.setLinearVelocity(vector2.scl(mSPEED));
    }

    private void swordInGG(RevoluteJoint revoluteJoint, Body sword, Body gg) {
        RevoluteJointDef revoluteJointDef = new RevoluteJointDef();

        revoluteJointDef.bodyA = gg;
        revoluteJointDef.bodyB = sword;

        revoluteJointDef.collideConnected = false;

        Vector2 vector2 = new Vector2(0f, 0.11f);
        Vector2 vector21 = new Vector2(0f, 0.1f);

        revoluteJointDef.localAnchorA.set(gg.getLocalCenter().add(vector2));
        revoluteJointDef.localAnchorB.set(sword.getLocalCenter().add(vector21));

        revoluteJointDef.enableMotor = true;
        revoluteJointDef.motorSpeed = 3f;
        revoluteJointDef.maxMotorTorque = 3f;

        revoluteJointDef.enableLimit = false;
        revoluteJointDef.lowerAngle = -0.985f;
        revoluteJointDef.upperAngle = 0.985f;


        revoluteJoint = (RevoluteJoint) GameWorld.getWorld().createJoint(revoluteJointDef);

    }

    public void update(float delta) {

        sb.setProjectionMatrix(MenuScreen.getCam().combined);

//        swordFight();

        sb.begin();

        p_draw(delta);

        sb.end();

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

    public static void swordFight() {

        revoluteJoint.setMotorSpeed(-1);

       /* if (hit) {


            if (flip) {
//                revoluteJoint.setMotorSpeed(mSWORD_SPEED);
                revoluteJoint.enableMotor(true);
            } else {
//                revoluteJoint.setMotorSpeed(-mSWORD_SPEED);
            }
        }*/

//        if(revoluteJoint.getJointAngle()==revoluteJoint.getUpperLimit())

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
