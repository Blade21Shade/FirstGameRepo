package com.mygdx.gametest;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

public class ClassForGame extends ApplicationAdapter {
	ShapeRenderer renderer;
	Dude dude;
	ArrayList<Platform> platforms = new ArrayList<>();
	
	@Override
	public void create () {
		renderer = new ShapeRenderer();
		dude = new Dude(200, 200, 2, 15);

		NeededInputProcessor inputProcessor = new NeededInputProcessor(dude);
    	Gdx.input.setInputProcessor(inputProcessor);

		int platformWidth = 63;
    	int platformHeight = 20;

		for (int i = 0; i < 4; i++) {
			int y = 0 + platformHeight * i + 10;
			int x = 0 + platformWidth * i + 10;
			platforms.add(new Platform(x, y, platformWidth, platformHeight));
		}

		for (int i = 0; i < 2; i ++) {
			int y = platformHeight * 4 - platformHeight * i + 10;
			int x = platformWidth * 4 + platformWidth * i + 10;
			platforms.add(new Platform(x, y, platformWidth, platformHeight));
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
		
		dude.onPlatform = false; // Set the dude to always not be on a platform
		// This can be corrected later as needed

		// Update dude
		dude.update();

		// Check if the dude is colliding with any of the platforms
		for (Platform platform : platforms) {
			dude.collisionCheck2(platform);
		}

		// Check if the dude is on a platform, if not  
		//if (dude.onPlatform == false) {
		//	dude.falling = true;
		//}

		// Start drawing
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		dude.draw(renderer);
		for (Platform platform : platforms) {
			platform.draw(renderer);
		}

		// Close renderer
		renderer.end();
	}
}
