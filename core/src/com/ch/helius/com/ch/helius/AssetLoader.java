package com.ch.helius.com.ch.helius;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    private static TextureRegion ggTexture, playButtonBlack, playButtonWite;
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

    }

    private static void loadGG() {

        ggTexture = atlasGG.findRegion("gg", 0);

    }


    private static void loadStore() {

        storeTextureBlack = atlasGG.findRegion("store", 0);
        storeTextureWhite = atlasGG.findRegion("store", 1);
    }


    private static void loadSettings() {

        settingsTexture = atlasGG.findRegion("settings");
    }

    private static void loadPlayButton() {
        playButtonBlack = atlasGG.findRegion("PLAY", 0);
        playButtonWite = atlasGG.findRegion("PLAY", 1);
    }



    ///////////////////////////////////////////////////////////

    public static TextureRegion getPlayButtonBlack() {

        return playButtonBlack;
    }

    public static TextureRegion getPlayButtonWite() {

        return playButtonWite;
    }

    public static TextureRegion getGgTexture() {
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
