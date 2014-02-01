package client.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {
	private Vector2 direction = new Vector2();

	private float speed = 60 * 2, animationTime = 0;

	private Animation still, left, right, up, down;
	
	public Player(Animation still, Animation left, Animation right, Animation up, Animation down) {
		super(still.getKeyFrame(0));
		this.still = still;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}

	public void update(float delta) {
		setX(getX() + direction.x * delta);
		setY(getY() + direction.y * delta);

		animationTime += delta;
		setRegion(direction.x < 0 ? left.getKeyFrame(animationTime) : direction.x > 0 ? right.getKeyFrame(animationTime) : direction.y < 0 ? down.getKeyFrame(animationTime) : direction.y > 0 ? up.getKeyFrame(animationTime) : still.getKeyFrame(animationTime));
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		case Keys.W:
			direction.y = speed;
			animationTime = 0;
			break;
		case Keys.A:
			direction.x = -speed;
			animationTime = 0;
			break;
		case Keys.S:
			direction.y = -speed;
			animationTime = 0;
			break;
		case Keys.D:
			direction.x = speed;
			animationTime = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode) {
		case Keys.W:
			direction.y = 0;
			animationTime = 0;
			break;
		case Keys.A:
			direction.x = 0;
			animationTime = 0;
			break;
		case Keys.S:
			direction.y = 0;
			animationTime = 0;
			break;
		case Keys.D:
			direction.x = 0;
			animationTime = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
