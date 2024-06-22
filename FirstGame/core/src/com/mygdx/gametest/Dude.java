package com.mygdx.gametest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.lang.Math;


// Dude is made of a rect and circle, can move around based on user input
public class Dude {
    int x, y, x2, y2; // x is left, x2 is right, y is bottom, y2 is top (includes circle for head)
    static int i = 0;
    int movingLRspeed, jumpSpeed; // Left/Right speed and Jumping (Upward) speed
    boolean moveLeft, moveRight; 
    int gravity = 5; // A downward drag when necessary
    
    int rectHeight = 30, rectWidth = 20, circRad = 5;

    boolean onPlatform = false, jumping = false, falling = true; // Start true as the guy starts in the air
    // inAir will control whether the user can jump currently, and should probably be used alongside checkGravity in some way, but that may not be needed


    public Dude(int x, int y, int movingLRspeed, int jumpSpeed) {
        this.x = x;
        this.y = y;
        this.movingLRspeed = movingLRspeed;
        this.jumpSpeed = jumpSpeed;
        x2 = x + rectWidth;
        y2 = y + rectHeight + circRad;
    }

    public void update() {

        onPlatform = false; // This is done for falling purposes, otherwise, as written, the code can't make the dude fall when
                            // he walks off the side of a platform without jumping

        if (moveLeft) {
            x -= movingLRspeed;
            x2 -= movingLRspeed;
        }

        if (moveRight) {
            x += movingLRspeed;
            x2 += movingLRspeed;
        }

        
        if (jumping) {
            onPlatform = false;
            jumping();
        }
        
        if (falling) {
            falling();
        }
        
        // These are set now because the way I've implemented my collision checking onPlatform is never set to false
        // Setting it to false preemptively allows it to be corrected later if necessary, but work correctly otherwise
        onPlatform = false;
		falling = true;
        
        // Boundary checks for x and y positions

        if (x <= 0) {
            x = 0;
            x2 = x + rectWidth;
        } else if (x2 >= Gdx.graphics.getWidth()) {
            x2 = Gdx.graphics.getWidth();
            x = x2 - rectWidth;
        }
        // Y check
        if (y <= 0) {
            y = 0;
            y2 = y + rectHeight + circRad;
            falling = false;
            jumping = false;
            i = 0;
            onPlatform = true; // The ground is a platform
        } else if (y2 >= Gdx.graphics.getHeight()) {
            y2 = Gdx.graphics.getHeight();
            y = y2 - rectHeight - circRad;
        }

        // Final correction, just in case something didn't go through before
        x2 = x + rectWidth;
        y2 = y + rectHeight + circRad; 
    }


    public void draw(ShapeRenderer renderer) {
        renderer.rect(x, y, rectWidth, rectHeight); // 20 is width, 30 is height, placeholders once again
        renderer.circle(x + rectWidth/2, y + rectHeight + circRad, circRad); // x and y positions should be offset of x and y which determine 
    }

    public void collisionCheck(Platform platform) {
        // Top and bottom check
        if (x2 > platform.x && x < platform.x2) { // Dude is Within platform boundaries
            if (y <= platform.y2 && y >= platform.y2 - 4) { // Top check
                y = platform.y2;
                y2 = y + rectHeight;
                
                onPlatform = true;

                falling = false;
                jumping = false;
                i = 0;
                
            } else if (y2 > platform.y && y < platform.y ) { // Bottom check
                y2 = platform.y - circRad * 2;
                y = y2 - rectHeight;
                
                falling = true;
                jumping = false;
                i = 0;
            }
        } 

        // Difference may still need to be implemented !!!
        // That is, the differences between the platform and dude's x and ys are found, and the least of those is used to place the dude

        // Left and right check
        // First check to make sure the dude isn't completely above or below the platform
        // If he isn't, and therefore he is beside it height wise to some extent, check to see if he is encroaching on the platform
        // If so, move him so he isn't

        if (!(y >= platform.y2 && y2 >= platform.y2) && !(y <= platform.y && y2 <= platform.y)) {
            if (x2 >= platform.x && x < platform.x) { // Left check
                x = platform.x - rectWidth;
            } else if (x <= platform.x2 && x2 > platform.x2) { // Right check
                x = platform.x2;
            }
        }

        x2 = x + rectWidth;
        y2 = y + rectHeight;
    }

    public void collisionCheck2(Platform platform) {

        // See if dude is within this platform's boundaries, if not do nothing
        if (x2 > platform.x && x < platform.x2 && y2 >= platform.y && y <= platform.y2) {
            // These will represent the differences between the dude and the platform
            // Read each as "Difference of respective part of dude from X of platform"
            // The smallest difference is then used to place the dude
            int left, right, top, bottom;

            top = Math.abs(y - platform.y2); //    1
            bottom = Math.abs(y2 - platform.y); // 2
            left = Math.abs(x2 - platform.x); //   3
            right = Math.abs(x - platform.x2); //  4
            
            int smallestDif = 1; // Used in a case statement to place dude, changes based on smallest value, numbers above correlate
            int smallestDifValue = top; // Used for mathematical checking

            // Find the smallest difference
            
            if (bottom < smallestDifValue) {
                smallestDif = 2;
                smallestDifValue = bottom;
            }
            
            if (left < smallestDifValue) {
                smallestDif = 3;
                smallestDifValue = left;
            }

            if (right < smallestDifValue) {
                smallestDif = 4;
            }
            
            // Place dude based off smallest difference
            switch (smallestDif) {
                case 1: // Smallest difference is the top of the platform from the bottom of the dude
                    // Top                    
                    y = platform.y2;
                    
                    onPlatform = true;
    
                    falling = false;
                    jumping = false;
                    i = 0;
                break;
                case 2: // Smallest difference is the bottom of the platform from the top of the dude
                    // Bottom
                    y2 = platform.y - circRad * 2;
                    
                    falling = true;
                    jumping = false;
                    i = 0;
                break;
                case 3: // Smallest difference is the right of the dude and left of the platform
                    // Left
                    x = platform.x - rectWidth;
                break;
                case 4: // Smallest difference is the left of the dude and right of the platform
                    // Right
                    x = platform.x2;
                break;
            } 
            // Always set these again
            x2 = x + rectWidth;
            y2 = y + rectHeight;
        }
    }

    // Jumping function
    private void jumping() {
        if (i < 10) {
            y += jumpSpeed;
            y2 += jumpSpeed;
            i++;
        } else {
            falling = true;
        }
    }

    // Falling function
    private void falling() {
        if (falling) {
            y -= gravity;
            y2 -= gravity;
        }
    }

    public void setLeftMove(boolean t) {
	    if(moveRight && t) moveRight = false;
	    moveLeft = t;
    }

    public void setRightMove(boolean t) {
	    if(moveLeft && t) moveLeft = false;
	    moveRight = t;
    }

    public void setJumping(boolean t) {
        if (onPlatform) {
            jumping = t;
        }
    }
}
