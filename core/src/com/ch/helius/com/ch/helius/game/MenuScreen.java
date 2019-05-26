package com.ch.helius.com.ch.helius.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.com.ch.helius.AssetLoader;
import com.ch.helius.com.ch.helius.SimpleDirectionGestureDetector;

public class MenuScreen implements Screen {

    private final String MENU_TAG = "MENU_TAG";
    private final int MAX_LEVEL = 10;
    private Viewport viewport;
    private BitmapFont font;
    private SpriteBatch sp;
    private FreeTypeFontGenerator fttG;
    private final int PLAY_X = Gdx.graphics.getWidth() / 2, PLAY_Y = Gdx.graphics.getHeight() / 2,
            STORE_X = 2 * Gdx.graphics.getWidth() / 3, STORE_Y = Gdx.graphics.getHeight() / 2,
            STORE_W = Gdx.graphics.getWidth() / 8, STORE_H = Gdx.graphics.getWidth() / 8,
            SETTINGS_X = 97 * Gdx.graphics.getWidth() / 200, SETTINGS_Y = 19 * Gdx.graphics.getHeight() / 50,//// (int)(PLAY_Y*0.5)
            LEVEL_X = PLAY_X, LEVEL_Y = PLAY_Y;
    //private ShapeRenderer shapeRenderer;
    private int setScreen = 0, level = 1;
    //private final float EPS = 1.e-4f;
    //private Vector3 map_V3 = new Vector3( 0, 0, 0 );
    private SimpleDirectionGestureDetector gestureDetector;
    private GameWorld gameWorld;
    private HeliusGameClass hc;

    public MenuScreen(final GameWorld gameWorld, final HeliusGameClass hc) {

        int SIZE;
        if (Gdx.graphics.getHeight() > Gdx.graphics.getWidth()) {
            SIZE = 120 * Gdx.graphics.getHeight() / 1920;
        } else {
            SIZE = 120 * Gdx.graphics.getWidth() / 1080;
        }

        this.hc = hc;
        this.gameWorld = gameWorld;

        //private HeliusGameClass hc;
        //private GameWorld gameWorld;
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho( true );
        viewport = new ExtendViewport( Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam );

        //shapeRenderer = new ShapeRenderer();
        //shapeRenderer.setProjectionMatrix( cam.combined );

        gestureDetector = new SimpleDirectionGestureDetector( new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onTap() {
                Gdx.app.log( MENU_TAG, "Tap" );
                if (isPlayPress()) {
                    hc.setScreen( new GameScreen( gameWorld, hc, level ) );
                }
                Gdx.input.setInputProcessor( null );
                back_call( 0 );
            }

            @Override
            public void onLeft() {
                Gdx.app.log( MENU_TAG, "Swipe_left" );
                if (level < MAX_LEVEL) {

                    swipeLeft();
                }
            }

            @Override
            public void onRight() {
                Gdx.app.log( MENU_TAG, "Swipe_right" );
                if (level > 1) {

                    swipeRight();
                }
            }

            @Override
            public void onUp() {
                Gdx.app.log( MENU_TAG, "Swipe_up" );
            }

            @Override
            public void onDown() {
                Gdx.app.log( MENU_TAG, "Swipe_down" );
            }
        } );

        fttG = new FreeTypeFontGenerator( Gdx.files.internal( "data/font/vollkornmedium.ttf" ) );//vollkornmedium
        FreeTypeFontGenerator.FreeTypeFontParameter fttP = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fttP.size = SIZE;
        fttP.color = Color.BLACK;
        fttP.magFilter = Texture.TextureFilter.Nearest;
        fttP.minFilter = Texture.TextureFilter.Nearest;

        sp = new SpriteBatch();
        //sp.setProjectionMatrix( cam.combined );

        font = fttG.generateFont( fttP );

        fttG.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // Gdx.app.log( MENU_TAG, "Render_Menu" );

        Gdx.gl.glClearColor( 1, 1, 1f / 255, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        viewport.apply();

        switch (setScreen) {

            case 0:
                set_menu();
                break; //menu
            case 1:
                set_level_map();
                break; //level_map
            case 2:
                set_store();
                break; //store
            case 3:
                set_settings();
                break; //settings
        }
    }


    private void set_store() {

        back_call( 0 );
    }

    private void set_settings() {

        back_call( 0 );
    }

    private void set_level_map() {

        //Gdx.gl.glClearColor( 1, 1, 0.5f / 255, 1 );
        //Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
        sp.begin();
        font.draw( sp, "LEVEL\n" + level, LEVEL_X, LEVEL_Y );

        Gdx.input.setInputProcessor( gestureDetector );

        sp.end();

       /* if (Gdx.input.isTouched()) {
            if (isPlayPress()) {
                Gdx.app.log( MENU_TAG, "play_gameScreen" );
                hc.setScreen( new GameScreen( gameWorld, hc ) );

            }
        }*/
        // Gdx.input.setInputProcessor( null );

    }

    private void swipeRight() {
        level--;

    }

    private void swipeLeft() {
        level++;

    }

    private void set_menu() {
        sp.begin();

        menu_draw();

        if (Gdx.input.isTouched()) {
            Gdx.app.log( MENU_TAG, "Touched" );

            if (isPlayPress()) {
                setScreen = 1; //set_level_map
            }

            Gdx.gl.glClearColor( 1, 0, 0, 1 );
            Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
            menu_draw();
        }

        back_call(-1);

        sp.end();

    }

    private void back_call(final int SET_SCR) {
        if (Gdx.input.isKeyPressed( Input.Keys.BACK )) {
            if (SET_SCR > -1) {
                setScreen = SET_SCR;
            } else {//TODO exit

                Gdx.app.exit();
            }
        }
    }

    private void menu_draw() {

        font.draw( sp, "PLAY", PLAY_X, PLAY_Y );
        sp.draw( AssetLoader.storeTextureBlack, STORE_X, STORE_Y, STORE_W, STORE_H );
        sp.draw( AssetLoader.settingsTexture, SETTINGS_X, SETTINGS_Y, STORE_W, STORE_H );
    }

    private Boolean isPlayPress() {
        //setScreen = 1; //set_level_map
        return PLAY_X <= Gdx.input.getX() && STORE_X + STORE_W >= Gdx.input.getX() &&
                PLAY_Y + 0.6 * STORE_H >= Gdx.input.getY() && SETTINGS_Y + 1.7 * STORE_H <= Gdx.input.getY();

    }

    @Override
    public void resize(int width, int height) {

        Gdx.app.log( MENU_TAG, "Resize" );
        viewport.update( width, height );
    }

    @Override
    public void pause() {
        Gdx.app.log( MENU_TAG, "Pause" );
    }

    @Override
    public void resume() {
        Gdx.app.log( MENU_TAG, "Resume" );
    }

    @Override
    public void hide() {
        Gdx.app.log( MENU_TAG, "Hide" );
    }

    @Override
    public void dispose() {
        Gdx.app.log( MENU_TAG, "Dispose" );

        font.dispose();
        fttG.dispose();

    }
}
