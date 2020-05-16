package com.ch.helius.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ch.helius.AssetLoader;
import com.ch.helius.HeliusGameClass;
import com.ch.helius.SimpleDirectionGestureDetector;

public class MenuScreen implements Screen {
    private TextureRegionDrawable textureRegionDrawable_play, trd_store, trd_settings;
    private ImageButton button, b_store, b_settings;
    private final String MENU_TAG = "MENU_TAG";
    private final int MAX_LEVEL = 10;
    private static Viewport viewport;
    private final int PLAY_W = (int) (Gdx.graphics.getWidth() / 2.2f), PLAY_H = (int) (PLAY_W / 3.44f),
            PLAY_X = Gdx.graphics.getWidth() / 2, PLAY_Y = (int) (Gdx.graphics.getHeight() / 2f - PLAY_H),//
            STORE_W = Gdx.graphics.getWidth() / 8, STORE_H = Gdx.graphics.getWidth() / 8,
            STORE_X = PLAY_X + PLAY_W - STORE_W, STORE_Y = Gdx.graphics.getHeight() / 2,
            SETTINGS_X = PLAY_X, SETTINGS_Y = PLAY_Y - PLAY_H;
    private int setScreen = 0, level = 1;
    private SimpleDirectionGestureDetector gestureDetector;
    private HeliusGameClass hc;
    private Stage stage, stage_lvl;
    private Boolean isPlayPress = false;
    private TextButton b_lvl;
    private TextButton.TextButtonStyle textButtonStyle;
    private BitmapFont font;
    private FreeTypeFontGenerator fttG;
    private InputMultiplexer multiplexer;
    private static OrthographicCamera cam;

    public MenuScreen(final HeliusGameClass hc) {

        int SIZE;
        if (Gdx.graphics.getHeight() > Gdx.graphics.getWidth()) {
            SIZE = 130 * Gdx.graphics.getHeight() / 1920;//120
        } else {
            SIZE = 130 * Gdx.graphics.getWidth() / 1080;
        }

        this.hc = hc;

        multiplexer = new InputMultiplexer();

        cam = HeliusGameClass.getCam();
        cam.setToOrtho(false);
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);

        textButtonStyle = new TextButton.TextButtonStyle();

        fttG = new FreeTypeFontGenerator(Gdx.files.internal("data/font/vollkornmedium.ttf"));//vollkornmedium
        FreeTypeFontGenerator.FreeTypeFontParameter fttP = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fttP.size = SIZE;
        fttP.color = Color.BLACK;
        fttP.magFilter = Texture.TextureFilter.Nearest;
        fttP.minFilter = Texture.TextureFilter.Nearest;

        font = fttG.generateFont(fttP);

        fttG.dispose();

        textButtonStyle.font = font;

        b_lvl = new TextButton("LeveL " + level, textButtonStyle);
//        b_lvl.setBounds(PLAY_X,PLAY_Y,PLAY_W,STORE_H);
        b_lvl.setPosition(PLAY_X, PLAY_Y);
        textureRegionDrawable_play = new TextureRegionDrawable(AssetLoader.getPlayButtonBlack());
        trd_store = new TextureRegionDrawable(AssetLoader.getStoreTextureBlack());
        trd_settings = new TextureRegionDrawable(AssetLoader.getSettingsTexture());

        b_store = new ImageButton(trd_store);
        b_store.setBounds(STORE_X, STORE_Y, STORE_W, STORE_H);

        b_settings = new ImageButton(trd_settings);
        b_settings.setBounds(SETTINGS_X, SETTINGS_Y, STORE_W, STORE_H);

        button = new ImageButton(textureRegionDrawable_play);
        button.setBounds(PLAY_X, PLAY_Y, PLAY_W, PLAY_H);
        button.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log(MENU_TAG, "handle");
                Gdx.input.vibrate(18);
                isPlayPress = true;
                return false;
            }
        });

        b_lvl.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Gdx.app.log(MENU_TAG, "Tap");
                hc.setScreen(new GameScreen(hc));

                return false;
            }
        });

        stage = new Stage(viewport);
        stage_lvl = new Stage(viewport);

        stage.addActor(button);
        stage.addActor(b_store);
        stage.addActor(b_settings);

        stage_lvl.addActor(b_lvl);

        Gdx.input.setCatchBackKey(true);//###############


        gestureDetector = new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onTap() {

            }

            @Override
            public void onLeft() {
                Gdx.app.log(MENU_TAG, "Swipe_left");
                if (level < MAX_LEVEL) {

                    swipeLeft();
                }
            }

            @Override
            public void onRight() {
                Gdx.app.log(MENU_TAG, "Swipe_right");
                if (level > 1) {

                    swipeRight();
                }
            }

            @Override
            public void onUp() {
                Gdx.app.log(MENU_TAG, "Swipe_up");
            }

            @Override
            public void onDown() {
                Gdx.app.log(MENU_TAG, "Swipe_down");
            }
        });

        multiplexer.addProcessor(stage_lvl);
        multiplexer.addProcessor(gestureDetector);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

        //back_call(0);
    }

    private void set_settings() {

        //back_call(0);
    }

    private void set_level_map() {

        Gdx.input.setInputProcessor(multiplexer);

        b_lvl.setText("LeveL" + level);

        stage_lvl.draw();


    }


    private void swipeRight() {
        level--;

        //level /=10;
    }

    private void swipeLeft() {
        level++;

//        level*=10;
    }

    private void set_menu() {

        Gdx.input.setInputProcessor(stage);

        HeliusGameClass.getSb().begin();

        menu_draw();

        if (Gdx.input.isTouched()) {
            Gdx.app.log(MENU_TAG, "Touched");

            if (isPlayPress) {
                setScreen = 1; //set_level_map
            }

            Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            menu_draw();
        }

        //back_call(-1);

        HeliusGameClass.getSb().end();

    }

/*    private void back_call(final int SET_SCR) {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            if (SET_SCR > -1) {
                setScreen = SET_SCR;
            } else {//TODO exit

                Gdx.app.exit();
            }
        }
    }*/

    private void menu_draw() {

        stage.draw();
    }

    public static Viewport getViewport() {

        return viewport;
    }

    @Override
    public void resize(int width, int height) {

        Gdx.app.log(MENU_TAG, "Resize");
        viewport.update(width, height);

    }

    @Override
    public void pause() {
        Gdx.app.log(MENU_TAG, "Pause");
    }

    @Override
    public void resume() {
        Gdx.app.log(MENU_TAG, "Resume");
    }

    @Override
    public void hide() {
        Gdx.app.log(MENU_TAG, "Hide");

    }

    @Override
    public void dispose() {

        Gdx.app.log(MENU_TAG, "Dispose");
        stage.dispose();

    }
}
