package com.ch.helius.com.ch.helius.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.ch.helius.com.ch.helius.game_objects.GamePers;


public class GameWorld {

    private static GamePers helius;
    private static World world;
    private final String WORLD_TAG = "WORLD_TAG";
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private Array<Body> wall;

    public GameWorld() {

        Gdx.app.log(WORLD_TAG, WORLD_TAG);

        world = new World(new Vector2(0, 0), true);

        tiledMap = new TmxMapLoader().load("map2.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        wall = MapBodyBuilder.buildShapes(tiledMap, world);

    }

    public static GamePers getHelius() {
        return helius;
    }

    public void loudGP() {
        helius = new GamePers(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 4f);
    }

    void update(float delta) {

        tiledMapRenderer.setView(MenuScreen.getCam());
        tiledMapRenderer.render();
        helius.update(delta);


    }

    public void setLevel(final int l) {

        Gdx.app.log(WORLD_TAG, "setLevel");

    }

    public Body createBox(BodyDef.BodyType type, float width, float height, float density) {

        BodyDef def = new BodyDef();
        def.type = type;
        Body box = world.createBody(def);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width, height);
        box.createFixture(poly, density);
        poly.dispose();

        return box;
    }

    public Body createCircle(BodyDef.BodyType type, float radius, float density) {

        BodyDef def = new BodyDef();
        def.type = type;
        Body box = world.createBody(def);
        CircleShape circle = new CircleShape();
        circle.setRadius(radius);
        box.createFixture(circle, density);
        circle.dispose();

        return box;
    }

    void gameDispose() {
        tiledMap.dispose();
    }

    public Array<Body> getWall() {
        return wall;
    }

    public void setWall(Array<Body> wall) {
        this.wall = wall;
    }
}
