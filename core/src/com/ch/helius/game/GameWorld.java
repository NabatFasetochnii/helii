package com.ch.helius.game;

import com.badlogic.ashley.core.Entity;
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
import com.ch.helius.Components.B2dBodyComponent;
import com.ch.helius.Components.CollisionComponent;
import com.ch.helius.Components.EnemyComponent;
import com.ch.helius.Components.StateComponent;
import com.ch.helius.Components.SteeringComponent;
import com.ch.helius.Components.TextureComponent;
import com.ch.helius.Components.TransformComponent;
import com.ch.helius.Components.TypeComponent;
import com.ch.helius.SteeringPresets;
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
                /*if (GamePers.isRun()) {//TODO
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

    public Entity createSeeker(float x, float y) {
        Entity entity = engine.createEntity();
        B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);
        EnemyComponent enemy = engine.createComponent(EnemyComponent.class);
        SteeringComponent scom = engine.createComponent(SteeringComponent.class);


        b2dbody.body = bodyFactory.makeCirclePolyBody(x,y,1, BodyFactory.STONE, BodyDef.BodyType.DynamicBody,true);
        b2dbody.body.setGravityScale(0f);  // no gravity for our floating enemy
        b2dbody.body.setLinearDamping(0.3f); // setting linear dampening so the enemy slows down in our box2d world(or it can float on forever)

        position.position.set(x,y,0);
        texture.region = atlas.findRegion("player");
        type.type = TypeComponent.ENEMY;
        stateCom.set(StateComponent.STATE_NORMAL);
        b2dbody.body.setUserData(entity);
        bodyFactory.makeAllFixturesSensors(b2dbody.body); // seeker  should fly about not fall
        scom.body = b2dbody.body;
        //enemy.enemyType = EnemyComponent.Type.CLOUD; // used later in tutorial

        // set out steering behaviour
        scom.steeringBehavior  = SteeringPresets.getWander(scom);
        scom.currentMode = SteeringComponent.SteeringState.WANDER;

        entity.add(b2dbody);
        entity.add(position);
        entity.add(texture);
        entity.add(colComp);
        entity.add(type);
        entity.add(enemy);
        entity.add(stateCom);
        entity.add(scom);

        engine.addEntity(entity);
        return entity;

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

        tiledMapRenderer.setView(GameScreen.getCam());
        tiledMapRenderer.render();

        helius.update(delta);

        world.step(1 / 60f, 6, 2);

        box2DDebugRenderer.render(world, GameScreen.getCam().combined.cpy().scale(PIX_TO_M, PIX_TO_M, 0));

        GameScreen.getCam().position.set(GamePers.getgPers().getPosition().x * PIX_TO_M,
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
