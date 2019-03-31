package com.ch.helius.com.ch.helius;

import com.badlogic.gdx.InputProcessor;
import com.ch.helius.com.ch.helius.game_objects.HeliusPers;

public class InputHandler implements InputProcessor {//TODO touch

    private HeliusPers heliusPers;
    private int x;
    private int y;

    public InputHandler(HeliusPers hp){
        this.heliusPers = hp;

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        this.x = screenX;
        this.y = screenY;


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        //heliusPers.isUp( true );
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        int dx = screenX - x;
        int dy = screenY - y;
        if ( Math.abs( dx ) > Math.abs( dy ) && dx > 0 ){
            //right
            heliusPers.onShift( -2 );

        } else if (Math.abs( dx ) > Math.abs( dy ) && dx < 0 ){

            //left
            heliusPers.onShift( 2 );

        } else if(Math.abs( dx ) < Math.abs( dy ) && dy > 0 ){
            //down
            heliusPers.onShift( -1 );

        } else if(Math.abs( dx ) < Math.abs( dy ) && dy < 0 ){
            //up
            heliusPers.onShift( 1 );

        }
        //heliusPers.isUp(false);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
