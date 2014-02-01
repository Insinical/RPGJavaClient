package client.states;

import client.entity.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Play implements Screen {	
	private SpriteBatch batch;
	private BitmapFont font;
	
	private TiledMap map1;
	private OrthogonalTiledMapRenderer renderer;
	
	private OrthographicCamera camera;
	
	private TextureAtlas playerAtlas;
	private Player player;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		
		renderer.getSpriteBatch().begin();
		player.draw(renderer.getSpriteBatch());
		renderer.getSpriteBatch().end();
		
		batch.begin();
			font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 600 - 10);
			font.draw(batch, "Mouse X: " + Gdx.input.getX(), 10, 600 - 25);
			font.draw(batch, "Mouse Y: " + Gdx.input.getY(), 10, 600 - 40);
			font.draw(batch, "Character X: " + player.getX(), 10, 600 - 55);
			font.draw(batch, "Character Y: " + player.getY(), 10, 600 - 70);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
	}

	@Override
	public void show() {
		map1 = new TmxMapLoader().load("assets/maps/map1.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map1);
		
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("assets/data/fonts/font.fnt"), Gdx.files.internal("assets/data/fonts/font.png"), false);	
		
		camera = new OrthographicCamera();

		playerAtlas = new TextureAtlas("assets/data/player/player.pack");
		Animation still, left, right, up, down;
		still = new Animation(0.3f, playerAtlas.findRegions("still"));
		left = new Animation(0.3f, playerAtlas.findRegions("left"));
		right = new Animation(0.3f, playerAtlas.findRegions("right"));
		up = new Animation(0.3f, playerAtlas.findRegions("up"));
		down = new Animation(0.3f, playerAtlas.findRegions("down"));
		still.setPlayMode(Animation.LOOP);
		left.setPlayMode(Animation.LOOP);
		right.setPlayMode(Animation.LOOP);
		up.setPlayMode(Animation.LOOP);
		down.setPlayMode(Animation.LOOP);
		
		player = new Player(still, left, right, up, down, (TiledMapTileLayer) map1.getLayers().get(1));
		player.setPosition(64, 64);
		
		Gdx.input.setInputProcessor(player);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		map1.dispose();
		renderer.dispose();
	}

}
