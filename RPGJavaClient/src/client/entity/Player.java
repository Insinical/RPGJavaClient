/*	Thanks to dermetfan (http://www.youtube.com/dermetfan) for making a tutorial
 *	on creating a player and collision detection. Helped me create the player class.
 */

package client.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {
	private Vector2 direction = new Vector2();

	private float speed = 60 * 2, animationTime = 0;

	private Animation still, left, right, up, down;
	
	private TiledMapTileLayer blocked;
	
	public Player(Animation still, Animation left, Animation right, Animation up, Animation down, TiledMapTileLayer blocked) {
		super(still.getKeyFrame(0));
		this.still = still;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.blocked = blocked;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}

	public void update(float delta) {
		animationTime += delta;
		setRegion(direction.x < 0 ? left.getKeyFrame(animationTime) : direction.x > 0 ? right.getKeyFrame(animationTime) : direction.y < 0 ? down.getKeyFrame(animationTime) : direction.y > 0 ? up.getKeyFrame(animationTime) : still.getKeyFrame(animationTime));
	
		float oldX = getX(), oldY = getY();
		boolean blockedX = false; boolean blockedY = false;
		
		setX(getX() + direction.x * delta);
		
		if(direction.x < 0) {
			blockedX = blockedLeft();
		} else if(direction.x > 0) {
			blockedX = blockedRight();
		}
		
		if(blockedX) {
			setX(oldX);
			direction.x = 0;
		}
		
		setY(getY() + direction.y * delta);
		if(direction.y < 0) {
			blockedY = blockedUp();
		} else if(direction.y > 0) {
			blockedY = blockedDown();
		}
		
		if(blockedY) {
			setY(oldY);
			direction.y = 0;
		}
		
		if(Gdx.input.isKeyPressed(Keys.W)) {
			direction.y = speed;
			animationTime = 0;
		} else if(Gdx.input.isKeyPressed(Keys.A)) {
			direction.x = -speed;
			animationTime = 0;
		} else if(Gdx.input.isKeyPressed(Keys.S)) {
			direction.y = -speed;
			animationTime = 0;
		} else if(Gdx.input.isKeyPressed(Keys.D)) {
			direction.x = speed;
			animationTime = 0;
		} else {
			direction.x = 0;
			direction.y = 0;
			animationTime = 0;
		}
	}
	
	private boolean isCellBlocked(float x, float y) {
		Cell cell = blocked.getCell((int) (x / blocked.getTileWidth()), (int) (y / blocked.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
	}
	
	public boolean blockedUp() {
		for(float step = 0; step < getWidth(); step += blocked.getTileWidth() / 2)
			if(isCellBlocked(getX() + step, getY()))
				return true;
		return false;
	}
	
	public boolean blockedLeft() {
		for(float step = 0; step < getHeight(); step += blocked.getTileHeight() / 2)
			if(isCellBlocked(getX(), getY() + step))
				return true;
		return false;
	}
	
	public boolean blockedRight() {
		for(float step = 0; step < getHeight(); step += blocked.getTileHeight() / 2)
			if(isCellBlocked(getX() + getWidth(), getY() + step))
				return true;
		return false;
	}
	
	public boolean blockedDown() {
		for(float step = 0; step < getWidth(); step += blocked.getTileWidth() / 2)
			if(isCellBlocked(getX() + step, getY()))
				return true;
		return false;
	}
	
	public TiledMapTileLayer getBlockedLayer() {
		return blocked;
	}
	
	public void setBlockedLayer(TiledMapTileLayer blocked) {
		this.blocked = blocked;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
