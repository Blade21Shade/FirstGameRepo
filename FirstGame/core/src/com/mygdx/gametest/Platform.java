package com.mygdx.gametest;

import java.util.ArrayList;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
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
    
    public static void generatePlatforms(ArrayList<Platform> platforms) {
        int maxWidth = 100;
        int minWidth = 50;
        int thisWidth = 0;
        int allHeight = 20;

        int numberOfPlatforms = 20;


        int x = 0;
        int y = 0;

        // Theoretical boundaries for the bottom, bottom mid, top mid, and top sections of the box the game is played in
        // by y position.
        // bottom (area) is bottom to bottomMid, bottom mid is bottomMid to mid, top mid is mid to topMid, top is topMid to top
        int bottom = 0;
        int bottomMid = Gdx.graphics.getHeight()/4;
        int mid = bottomMid * 2;
        int topMid = bottomMid * 3;
        int top = Gdx.graphics.getHeight();

        int numOfPlatformsInBottom = 0, numOfPlatformsInBottomMid = 0, numOfPlatformsInTopMid = 0, numOfPlatformsInTop = 0;

        double ranD = 0; // Random number

        // Actual platform creation
        for (int i = 0; i < numberOfPlatforms; i++) {

            // Find width for this platform
            double tempDouble = Math.random() * 100;
            thisWidth = (int)tempDouble;
            if (thisWidth < minWidth) { // MaxWidth is already enforced, so no need to check
                thisWidth = minWidth; // I may redo how I do width stuff but for now this is fine
            }

            // Generate random x position
            x = ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getWidth() + 1 - thisWidth);
            // - thisWidth so every platform stays in bounds

            // Figure out which area we are putting this platform in
            // Also get the y position for that area
            ranD = Math.random();
            if (ranD <= .25 && numOfPlatformsInBottom < numberOfPlatforms/4) {
                y = ThreadLocalRandom.current().nextInt(bottom, bottomMid);
                numOfPlatformsInBottom++;
            } else if (ranD <= .5 && numOfPlatformsInBottomMid < numberOfPlatforms/4) {
                y = ThreadLocalRandom.current().nextInt(bottomMid, mid);
                numOfPlatformsInBottomMid++;
            } else if (ranD <= .75 && numOfPlatformsInTopMid < numberOfPlatforms/4) {
                y = ThreadLocalRandom.current().nextInt(mid, topMid);
                numOfPlatformsInTopMid++;
            } else if (ranD <= 1.00 && numOfPlatformsInTop < numberOfPlatforms/4) {
                y = ThreadLocalRandom.current().nextInt(topMid, top);
                numOfPlatformsInTop++;
            } // An else could be put here for making sure all platforms are put somewhere, but isn't necessary 

            // Add to the platforms arrayList for safe keeping
            platforms.add(new Platform(x, y, thisWidth, allHeight));
        }
    }

    public void draw(ShapeRenderer renderer){
        renderer.rect(x, y, width, height);
    }
}
