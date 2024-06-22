package com.mygdx.gametest;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
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

		Platform.generatePlatforms(platforms);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen

		// Update dude
		dude.update();

		// Check if the dude is colliding with any of the platforms
		for (Platform platform : platforms) {
			dude.collisionCheck2(platform);
		}

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
