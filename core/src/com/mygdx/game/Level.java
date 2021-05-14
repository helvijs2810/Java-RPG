package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level implements Screen{

	Setup game;
	ShapeRenderer shapeRenderer;

	OrthographicCamera camera;
	Viewport viewport;
	
	int posX = 150;
	int posY = 150;
	
	static final int WORLD_WIDTH = 144;
	static final int WORLD_HEIGHT = 144; 
	
	public Level(Setup game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		shapeRenderer = new ShapeRenderer();
		
		camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		
		game.stage = new Stage(viewport);

		for(int i = game.playerArray.size() - 1; i >= 0; i--) {
			Player player = game.playerArray.get(i);
			for(int j = game.terrainObjects.size() - 1; j >= 0; j--) {
				Terrain rock = game.terrainObjects.get(j);
				rock.sprite.setPosition(16, 16);
				game.stage.addActor(rock);
			}
			game.stage.addActor(player);
			game.stage.setKeyboardFocus(player);
		}
		
		game.stage.getViewport().apply();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        for(int i = game.playerArray.size() - 1; i >= 0; i--) {
        	Player player = game.playerArray.get(i);
    		game.stage.getCamera().position.set(player.getX(), player.getY(), 0);
    		game.stage.getBatch().setProjectionMatrix(game.stage.getCamera().combined);
    		game.stage.getCamera().update();
        }
		
//		shapeRenderer.setProjectionMatrix(camera.combined);
//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.setColor(0, 1, 0, 1);
//		shapeRenderer.rect(0, 0, 16, 16);
//		shapeRenderer.end();

//        System.out.print(game.gameMap[16][31]);
        
	    game.stage.act(Gdx.graphics.getDeltaTime());

		game.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		game.stage.getViewport().update(width, height);
        game.stage.getCamera().viewportWidth = WORLD_WIDTH;
        game.stage.getCamera().viewportHeight = WORLD_HEIGHT * height / width;
        for(int i = game.playerArray.size() - 1; i >= 0; i--) {
        	Player player = game.playerArray.get(i);
            game.stage.getCamera().position.set(player.getX(), player.getY(), 0);
        }
        game.stage.getCamera().update();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		game.stage.dispose();
		shapeRenderer.dispose();
	}
}
