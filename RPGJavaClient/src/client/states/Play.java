package client.states;

import client.entity.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Play extends ScreenAdapter {
	private Skin uiSkin;
	private Stage guiStage;
	private Table gui_vitals, gui_messages, gui_menu;
	private TextButton inventory, character, spells, quests, skills, options, help, quit;
	private Label optGfx, optSound;
	private CheckBox vSyncEnable, musicEnable, effectsEnable;
	
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
		
		guiStage.draw();
		Table.drawDebug(guiStage);
		guiStage.act();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
	}

	@Override
	public void show() {
		guiStage = new Stage();
		
		Gdx.input.setInputProcessor(guiStage);
		
		uiSkin = new Skin(Gdx.files.internal("assets/ui/ui.json"), new TextureAtlas("assets/ui/atlas.pack"));
		
		map1 = new TmxMapLoader().load("assets/maps/map1.tmx");
		
		gui_vitals = new Table();
		gui_vitals.setBounds((Gdx.graphics.getWidth() / 2) - 200, 560, 400, 30);
		
		// menu
		gui_menu = new Table();
		gui_menu.setBounds((Gdx.graphics.getWidth() - 300 - 10), 10, 300, 150);
		
		// inventory menu
		inventory = new TextButton("Inventory", uiSkin, "font18");
		
		final Window invWindow = new Window("Inventory", uiSkin);
		invWindow.padTop(18);
		invWindow.setBounds(10, 175, 220, 280);
		invWindow.setVisible(false);
		guiStage.addActor(invWindow);
		inventory.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(invWindow.isVisible() == false) {
					invWindow.setVisible(true);
				} else {
					invWindow.setVisible(false);
				}
			}
		});
		
		// character menu
		character = new TextButton("Character", uiSkin, "font18");
		
		final Window charWindow = new Window("Character", uiSkin);
		charWindow.padTop(18);
		charWindow.setBounds(10,  175, 220, 280);
		charWindow.setVisible(false);
		guiStage.addActor(charWindow);
		character.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(charWindow.isVisible() == false) {
					charWindow.setVisible(true);
				} else {
					charWindow.setVisible(false);
				}
			}
		});
		
		// spells menu
		spells = new TextButton("Spells", uiSkin, "font18");
		
		final Window speWindow = new Window("Spells", uiSkin);
		speWindow.padTop(18);
		speWindow.setBounds(10, 175, 220, 280);
		speWindow.setVisible(false);
		guiStage.addActor(speWindow);
		spells.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(speWindow.isVisible() == false) {
					speWindow.setVisible(true);
				} else {
					speWindow.setVisible(false);
				}
			}
		});
		
		// quests menu
		quests = new TextButton("Quests", uiSkin, "font18");
		
		final Window queWindow = new Window("Quests", uiSkin);
		queWindow.padTop(18);
		queWindow.setBounds(10, 175, 220, 280);
		queWindow.setVisible(false);
		guiStage.addActor(queWindow);
		quests.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(queWindow.isVisible() == false) {
					queWindow.setVisible(true);
				} else {
					queWindow.setVisible(false);
				}
			}
		});
		
		// skills menu
		skills = new TextButton("Skills", uiSkin, "font18");
		
		final Window skiWindow = new Window("Skills", uiSkin);
		skiWindow.padTop(18);
		skiWindow.setBounds(10, 175, 220, 280);;
		skiWindow.setVisible(false);
		guiStage.addActor(skiWindow);
		skills.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(skiWindow.isVisible() == false) {
					skiWindow.setVisible(true);
				} else {
					skiWindow.setVisible(false);
				}
			}
		});
		
		// options menu
		options = new TextButton("Options", uiSkin, "font18");
		
		optGfx = new Label("Graphics", uiSkin, "font18");
		optSound = new Label("Sound", uiSkin, "font18");
		vSyncEnable = new CheckBox("Enable vSync", uiSkin);
		musicEnable = new CheckBox("Play Music", uiSkin);
		effectsEnable = new CheckBox("Play Sound Effects", uiSkin);
		
		final Window optWindow = new Window("Options", uiSkin);
		optWindow.padTop(18);
		optWindow.setBounds(10, 175, 220, 280);
		optWindow.setVisible(false);
		guiStage.addActor(optWindow);
		options.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(optWindow.isVisible() == false) {
					optWindow.setVisible(true);
				} else {
					optWindow.setVisible(false);
				}
			}
		});
		
		help = new TextButton("Help", uiSkin, "font18");
		quit = new TextButton("Quit", uiSkin, "font18");
		
		optWindow.add(optGfx);
		optWindow.row();
		optWindow.add(vSyncEnable).expandX().left().padLeft(15);
		optWindow.row();
		optWindow.add(optSound);
		optWindow.row();
		optWindow.add(musicEnable).expandX().left().padLeft(15);
		optWindow.row();
		optWindow.add(effectsEnable).expandX().left().padLeft(15);
		
		// in-game menu
		gui_menu.add(inventory).padBottom(5).width(125);
		gui_menu.add(character).padBottom(5).width(125);
		gui_menu.row();
		gui_menu.add(spells).padBottom(5).width(125);
		gui_menu.add(quests).padBottom(5).width(125);
		gui_menu.row();
		gui_menu.add(skills).padBottom(5).width(125);
		gui_menu.add(options).padBottom(5).width(125);
		gui_menu.row();
		gui_menu.add(help).fill().colspan(2).padBottom(5).width(250);
		
		// game-messages
		gui_messages = new Table();
		gui_messages.setBounds(10, 10, 400, 150);
		
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
		
		gui_vitals.debug();
		gui_menu.debug();
		gui_messages.debug();
		guiStage.addActor(gui_vitals);
		guiStage.addActor(gui_menu);
		guiStage.addActor(gui_messages);
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
