package client;

import client.states.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameClient extends Game {
	// global variables
	public static String name = "Moon Engine";
	public static String version = "v0.0.1";
	public static BitmapFont font, font50, menuBtnFont, newsFont, loginFnt;
	
	// main menu objects
	public static FileHandle newsHandle = new FileHandle("assets/data/news.txt");
	public static String news = newsHandle.readString();
	
	// login menu objects
	public static String username = "Username:";
	public static String password = "Password:";
	public static String email = "Email:";
	
	@Override
	public void create() {
		setScreen(new SplashScreen());
		
		font = new BitmapFont(Gdx.files.internal("assets/data/fonts/font.fnt"), Gdx.files.internal("assets/data/fonts/font.png"), false);	
		font50 = new BitmapFont(Gdx.files.internal("assets/data/fonts/font50.fnt"), Gdx.files.internal("assets/data/fonts/font50.png"), false);
		menuBtnFont = new BitmapFont(Gdx.files.internal("assets/data/fonts/menuBtnFont.fnt"), Gdx.files.internal("assets/data/fonts/menuBtnFont.png"), false);
		newsFont = new BitmapFont(Gdx.files.internal("assets/data/fonts/newsFont.fnt"), Gdx.files.internal("assets/data/fonts/newsFont.png"), false);
		loginFnt = new BitmapFont(Gdx.files.internal("assets/data/fonts/loginFnt.fnt"), Gdx.files.internal("assets/data/fonts/loginFnt.png"), false);
	}
	
}
