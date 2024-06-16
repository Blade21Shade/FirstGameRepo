package com.mygdx.gametest;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Platform {
    int x,x2,y,y2,width,height; // x is left, x2 is right, y is bottom, y2 is top

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        x2 = x + width;
        y2 = y + height;
    }
    
    public void draw(ShapeRenderer renderer){
        renderer.rect(x, y, width, height);
    }
}
