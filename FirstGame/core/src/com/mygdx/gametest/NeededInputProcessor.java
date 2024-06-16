package com.mygdx.gametest;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;


public class NeededInputProcessor implements InputProcessor {
    
    private Dude dude;

    public NeededInputProcessor(Dude dude){
        this.dude = dude;
    }
    
    @Override
    public boolean keyDown(int keycode) {
	    switch (keycode) {
            case Keys.LEFT:
                dude.setLeftMove(true);
                break;
            case Keys.RIGHT:
                dude.setRightMove(true);
                break;
            case Keys.UP:
                dude.setJumping(true);
                break;
	        }
	    return true;
    }

    @Override
    public boolean keyUp(int keycode) {
	    switch (keycode) {
            case Keys.LEFT:
                dude.setLeftMove(false);
                break;
            case Keys.RIGHT:
                dude.setRightMove(false);
                break;
            case Keys.UP:
                dude.setJumping(false);
                break;
	        }
	    return true;
    }

    public boolean keyTyped (char character) {
        return false;
    }
  
    public boolean touchDown (int x, int y, int pointer, int button) {
        return false;
    }
  
    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }
  
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }
  
    public boolean mouseMoved (int x, int y) {
        return false;
    }
  
    public boolean scrolled (float amountX, float amountY) {
        return false;
    }

    public boolean touchCancelled​(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}