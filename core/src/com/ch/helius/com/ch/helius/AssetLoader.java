package com.ch.helius.com.ch.helius;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {


    public static Texture texture; //
    public static TextureRegion ggTexture;
    public static TextureRegion wallTexture, storeTextureBlack, storeTextureWhite, settingsTexture, likeTexture, menuTexture;



    public static Animation<TextureRegion> storeAnimation;


    public AssetLoader (){


    }

    public static void load(){

        texture = new Texture( Gdx.files.internal( "data/texture.png" ) );
        texture.setFilter( Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        loadStore();
        loadGG();
        loadSettings();

    }


    public static void loadGG(){

        ggTexture = new TextureRegion( texture, 0, 102, 395,549 );
    }

    public static void loadWall(int x){


        switch (x) {
            case -1: //error
            case 0: wallTexture = new TextureRegion( texture, 0,   0, 113,102 ); break; //black
            case 1: wallTexture = new TextureRegion( texture, 113, 0, 113,102 ); break; //withe
            case 2: wallTexture = new TextureRegion( texture, 226, 0, 113,102 ); break; //yellow
            case 3: wallTexture = new TextureRegion( texture, 339, 0, 113,102 ); break; //blue
            case 4: wallTexture = new TextureRegion( texture, 452, 0, 113,102 ); break; //green


        }

    }

    public static void loadStore(){

        storeTextureBlack = new TextureRegion( texture, 421, 115, 200,198 );
        storeTextureWhite = new TextureRegion( texture, 683, 115, 200,198 );


    }

    public static void alertStore(){
        final TextureRegion[] store = {storeTextureBlack, storeTextureWhite};
        storeAnimation = new Animation<TextureRegion>( 1f, store );
        storeAnimation.setPlayMode( Animation.PlayMode.LOOP );
    }


    public static void loadSettings(){

        settingsTexture = new TextureRegion( texture, 449, 301, 120,120 );

    }

    public static void loadLike(){

        likeTexture = new TextureRegion( texture, 449, 421, 120,120 );

    }

    public static void loadMenu(){

        menuTexture = new TextureRegion( texture, 449, 540, 120,120 );

    }



    public static void dispose(){

        texture.dispose();
    }

}
