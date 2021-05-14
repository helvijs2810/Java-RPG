package com.mygdx.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Setup extends Game {
	SpriteBatch batch;
	Texture img;
	Stage stage;
	TextureAtlas textureAtlas;
	Animation<TextureRegion> animation;
	float timeElapsed = 0f;
	
	List<Player> playerArray;
	List<Terrain> terrainObjects;
	char[][] gameMap = new char[32][32];
	int x = 0;
	int y = 0;
	
	@Override
	public void create() {
		File world = new File("C:/Users/Strix1/Documents/ExThirdImpact/core/assets/worldMap.txt");
		try {
			Scanner s = new Scanner(world);
			while(s.hasNextLine()) {
				String line = s.nextLine();
				Scanner readLine = new Scanner(line); 
				while(readLine.hasNext()) {
					String container = readLine.next();
					for(char ch: container.toCharArray()) {
						if(y > 31) {
							y = 0;
							x++;
						}
						gameMap[x][y++] = ch;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		playerArray = new ArrayList<Player>(); 
		terrainObjects = new ArrayList<Terrain>();
		
		batch = new SpriteBatch();
		img = new Texture("player.png");
		textureAtlas = new TextureAtlas("spritesheet/myspritesheet.atlas");
		animation = new Animation<TextureRegion>(1f/30f, textureAtlas.getRegions());
		playerArray.add(new Player(animation, 0f, 0f));
		terrainObjects.add(new Terrain(new Texture("rock.png")));
		setScreen(new Level(this));
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		stage.dispose();
		textureAtlas.dispose();
	}
}
