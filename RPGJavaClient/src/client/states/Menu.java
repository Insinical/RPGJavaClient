package client.states;

import client.GameClient;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Menu implements Screen {
	private TextureAtlas atlas;
	private Skin skin;
	private Stage stage;
	private Table table, newsTable;
	private TextButton play, credits, exit;
	private Label title, news;
	private Sprite menubg, menunews;
	private SpriteBatch batch;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
			menubg.draw(batch);
			menunews.draw(batch);
			
			GameClient.font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			GameClient.font.draw(batch, GameClient.name + " " + GameClient.version, 10, 600 - 10);
			GameClient.font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 600 - 25);
			
			title.draw(batch, 1.0f);
			title.setPosition((Gdx.graphics.getWidth() / 2) - (title.getWidth() / 2), 450);
		batch.end();
		
		stage.draw();
		//Table.drawDebug(stage);
		stage.act();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas("assets/ui/button.pack");
		skin = new Skin(atlas);
		
        table = new Table();
        table.setBounds(100, 75, 600, 50);
        
        newsTable = new Table();
        newsTable.setBounds(110, 150, 580, 280);
        
		Texture newsTex = new Texture("assets/data/img/ui/menu_news.png");
		menunews = new Sprite(newsTex);
		menunews.setPosition(100, 140);
		
		Texture menuTex = new Texture("assets/data/img/ui/menu_bg.png");
		menubg = new Sprite(menuTex);
		menubg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.up = skin.getDrawable("button.normal9");
		tbs.down = skin.getDrawable("button.pressed9");
		tbs.pressedOffsetX = 1;
		tbs.pressedOffsetY = -1;
		tbs.font = GameClient.menuBtnFont;
		
		LabelStyle titleStyle = new LabelStyle(GameClient.font50, Color.WHITE);
		LabelStyle newsStyle = new LabelStyle(GameClient.newsFont, Color.WHITE);
		
		title = new Label(GameClient.name, titleStyle);
		news = new Label(GameClient.news, newsStyle);
		news.setWrap(true);
		news.setAlignment(0, 2);
		
		play = new TextButton("Play", tbs);
		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Play());
			}
		});
		
		credits = new TextButton("Credits", tbs);
		credits.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Play());
			}
		});
		
		exit = new TextButton("Exit", tbs);
		exit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		
		table.add(play).width(180).spaceRight(30);
		table.add(credits).width(180).spaceRight(30);
		table.add(exit).width(180);
		table.debug();
		
		newsTable.add(news).width(580);
		newsTable.debug();
		stage.addActor(newsTable);
		stage.addActor(table);
		
		batch = new SpriteBatch();
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
		stage.dispose();
	}

}
