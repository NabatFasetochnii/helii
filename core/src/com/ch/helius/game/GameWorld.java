package com.ch.helius.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ch.helius.game_objects.GamePers;

public class GameWorld {

    private static GamePers helius;
    private static World world;
    private final String WORLD_TAG = "WORLD_TAG";
    private final int PIX_TO_M = 100;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private Array<Body> wall;
    private Box2DDebugRenderer box2DDebugRenderer;

    public GameWorld() {

        Gdx.app.log(WORLD_TAG, WORLD_TAG);

        world = new World(new Vector2(0, -9.81f), true);

        tiledMap = new TmxMapLoader().load("new2_level1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        box2DDebugRenderer = new Box2DDebugRenderer(true,
                true,true,true,
                true,true);
        wall = MapBodyBuilder.buildShapes(tiledMap, world);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
//                Gdx.app.log(WORLD_TAG, "beginContact");
               /* if (GamePers.isRun()) {
                    GamePers.setRun(false);
                }*/

                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                Gdx.app.log(WORLD_TAG, fixtureA.getBody().getUserData()
                        + " contact with " + fixtureB.getBody().getUserData());

            }

            @Override
            public void endContact(Contact contact) {
//                Gdx.app.log(WORLD_TAG, "endContact");


//                GamePers.setRun(false);
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
//                Gdx.app.log(WORLD_TAG, "preSolve");


//                GamePers.setRun(false);
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
//                Gdx.app.log(WORLD_TAG, "postSolve");
            }

        });

    }

    public static World getWorld() {
        return world;
    }

    public static GamePers getHelius() {
        return helius;
    }

    public int getPIX_TO_M() {
        return PIX_TO_M;
    }

    public void loudGP() {
//        helius = new GamePers(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        helius = new GamePers(90, 4550);
    }

    void update(float delta) {

        tiledMapRenderer.setView(MenuScreen.getCam());
        tiledMapRenderer.render();

        helius.update(delta);

        world.step(1 / 60f, 6, 2);

        box2DDebugRenderer.render(world, MenuScreen.getCam().combined.cpy().scale(PIX_TO_M, PIX_TO_M, 0));

        MenuScreen.getCam().position.set(GamePers.getgPers().getPosition().x * PIX_TO_M,
                GamePers.getgPers().getPosition().y * PIX_TO_M, 0);

    }

    public void setLevel(final int l) {

        Gdx.app.log(WORLD_TAG, "setLevel");

    }

    public Body createBox(BodyDef.BodyType type, int x, int y, float width, float height, float density) {

        BodyDef def = new BodyDef();
//        def.position.set(((x+(width/1f))/PIX_TO_M), ((y+(height/1f))/PIX_TO_M));
        def.position.set(((float) (x) / PIX_TO_M), ((float) (y) / PIX_TO_M));
        def.type = type;
        Body box = world.createBody(def);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width / PIX_TO_M, height / PIX_TO_M);
        box.setFixedRotation(true);
        box.createFixture(poly, density);
        poly.dispose();

        return box;
    }

    public Body createCircle(BodyDef.BodyType type, int x, int y, float radius, float density) {

        BodyDef def = new BodyDef();
        def.position.set((float) x / PIX_TO_M, (float) y / PIX_TO_M);
        def.type = type;
        Body box = world.createBody(def);
        CircleShape circle = new CircleShape();
        circle.setRadius(radius / PIX_TO_M);
        box.createFixture(circle, density);
        circle.dispose();

        return box;
    }

    void gameDispose() {
        tiledMap.dispose();
        world.dispose();
    }

    public Array<Body> getWall() {
        return wall;
    }

    public void setWall(Array<Body> wall) {
        this.wall = wall;
    }
}
