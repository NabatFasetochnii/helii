package com.ch.helius.com.ch.helius;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    private static TextureRegion playButtonBlack;
    private static TextureRegion playButtonWhite;
//    private static TextureRegion sword;
    private static TextureRegion[] ggTexture = new TextureRegion[3];
    private static TextureRegion storeTextureBlack, storeTextureWhite, settingsTexture;
    private static TextureAtlas atlasGG;

    public AssetLoader() {

    }


    public static void load() {

        //texture = new Texture(Gdx.files.internal("data/texture.png"));
        //texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        atlasGG = new TextureAtlas(Gdx.files.internal("data//gg.atlas"));
        loadStore();
        loadGG();
        loadSettings();
        loadPlayButton();
//        loadSword();

    }

    private static void loadGG() {

        ggTexture[0] = atlasGG.findRegion("gg", 0);
//        ggTexture[1] = atlasGG.findRegion("ggB", 0);
        ggTexture[2] = atlasGG.findRegion("run");

    }

   /* private static void loadSword() {
        sword = atlasGG.findRegion("sword", 0);
    }*/


    private static void loadStore() {

        storeTextureBlack = atlasGG.findRegion("store", 0);
        storeTextureWhite = atlasGG.findRegion("store", 1);
    }


    private static void loadSettings() {

        settingsTexture = atlasGG.findRegion("settings");
    }

    private static void loadPlayButton() {
        playButtonBlack = atlasGG.findRegion("PLAY", 0);
        playButtonWhite = atlasGG.findRegion("PLAY", 1);
    }


    ///////////////////////////////////////////////////////////


    public static TextureRegion getPlayButtonBlack() {

        return playButtonBlack;
    }

    public static TextureRegion getPlayButtonWhite() {

        return playButtonWhite;
    }

    public static TextureRegion[] getGgTexture() {
        return ggTexture;
    }

    public static TextureRegion getStoreTextureWhite() {
        return storeTextureWhite;
    }

    public static TextureRegion getStoreTextureBlack() {
        return storeTextureBlack;
    }

    public static TextureRegion getSettingsTexture() {
        return settingsTexture;
    }

    public static TextureAtlas getAtlasGG() {
        return atlasGG;
    }

    public static void dispose() {
        atlasGG.dispose();
        //texture.dispose();
    }

}
