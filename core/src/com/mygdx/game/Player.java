package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;

public class Player extends Actor{

	Sprite sprite;
	Animation<TextureRegion> animation;

	Setup game;
	Rectangle bounds;
	Array<TextureRegion> currentRegion = new Array<TextureRegion>();
	float cordX;
	float cordY;
	int currentFrameID = 0;
	boolean isOverlaping = false;
	
	float time = 0f;
	
	public Player(Animation<TextureRegion> animation, float x, float y) {
		this.animation = animation;
		this.cordX = x;
		this.cordY = y;
		
		game = new Setup();
		currentRegion.addAll(animation.getKeyFrames());
		
		setBounds(cordX, cordY, 16, 16);
//		bounds = new Rectangle((int)sprite.getX(), (int)sprite.getY(), (int)sprite.getWidth(), (int)sprite.getHeight());
		setTouchable(Touchable.enabled);
		
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keyCode) {
				if(keyCode == Input.Keys.W) {
					MoveToAction moveToAction = new MoveToAction();
					moveToAction.setPosition(getX(), getY()+16);
					moveToAction.setDuration(1);
					if(Player.this.getActions().size == 0 && !isOverlaping) {
						Player.this.addAction(moveToAction);
					}
				}
				else if(keyCode == Input.Keys.S) {
					MoveToAction moveToAction = new MoveToAction();
					moveToAction.setPosition(getX(), getY()-16);
					moveToAction.setDuration(1);
					cordY -= 16;
					if(Player.this.getActions().size == 0) {
						Player.this.addAction(moveToAction);
					}
				}
				else if(keyCode == Input.Keys.A) {
					MoveToAction moveToAction = new MoveToAction();
					moveToAction.setPosition(getX()-16, getY());
					moveToAction.setDuration(1);
					cordX -= 16;
					if(Player.this.getActions().size == 0) {
						Player.this.addAction(moveToAction);
					}
				}
				else if(keyCode == Input.Keys.D) {
					MoveToAction moveToAction = new MoveToAction();
					moveToAction.setPosition(getX()+16, getY());
					moveToAction.setDuration(1);
					cordX += 16;
					if(Player.this.getActions().size == 0) {
						Player.this.addAction(moveToAction);
					}
				}
				return true;
			}
		});
	}
	
//	public void spritePos(int x, int y) {
//		sprite.setPosition(x, y);
//	}
//	
	@Override
	public void draw(Batch batch, float parentAlpha) {
//		sprite.setPosition(getX(), getY());
		super.draw(batch, parentAlpha);
		batch.draw(currentRegion.get(currentFrameID), cordX, cordY);
	}
	
	@Override
	protected void positionChanged() {
		cordX =  getX();
		cordY =  getY();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		time += delta;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
