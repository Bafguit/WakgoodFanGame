package com.fastcat.labyrintale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.ScreenUtils;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.players.TestPlayer;
import com.fastcat.labyrintale.screens.advisorselect.AdvisorSelectScreen;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.charselect.CharSelectScreen;
import com.fastcat.labyrintale.screens.mainmenu.MainMenuScreen;
import com.fastcat.labyrintale.screens.map.MapScreen;

public class Labyrintale extends Game {

	public static final int DEFAULT_WIDTH = 1920;
	public static final int DEFAULT_HEIGHT = 1080;

	public static Labyrintale game;

	public static PolygonSpriteBatch psb;
	public static SkeletonRenderer sr;

	public static OrthographicCamera camera;

	public static AbstractLabyrinth labyrinth;

	public static MainMenuScreen mainMenuScreen;
	public static CharSelectScreen charSelectScreen;
	public static AdvisorSelectScreen advisorSelectScreen;
	public static MapScreen mapScreen;
	public static BattleScreen battleScreen;

	private static int uidCount = -1;

	private InputHandler inputHandler;
	private FontHandler fontHandler;
	private StringHandler stringHandler;
	private SettingHandler settingHandler;

	public SpriteBatch sb;

	
	@Override
	public void create () {
		Gdx.graphics.setResizable(false);
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		//Gdx.graphics.setWindowedMode(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sb = new SpriteBatch();
		psb = new PolygonSpriteBatch();
		sr = new SkeletonRenderer();
		sr.setPremultipliedAlpha(true);
		inputHandler = new InputHandler();
		fontHandler = new FontHandler();
		stringHandler = new StringHandler();
		settingHandler = new SettingHandler();
		game = this;
		mainMenuScreen = new MainMenuScreen(this);
		charSelectScreen = new CharSelectScreen(this);
		advisorSelectScreen = new AdvisorSelectScreen();
		battleScreen = new BattleScreen();
		labyrinth = new AbstractLabyrinth();

		setScreen(mainMenuScreen);
	}

	public void update() {
		camera.update();
		inputHandler.update();
		fontHandler.update();
		if(screen instanceof AbstractScreen) {
			((AbstractScreen) screen).update();
		}
	}

	@Override
	public void render () {
		/** Update */
		update();

		/** Render */
		ScreenUtils.clear(0, 0, 0, 1);
		sb.setProjectionMatrix(camera.combined);
		sb.begin();

		/** Render Methods */
		super.render();
		/** ============== */

		sb.end();
	}

	public static int getUid() {
		return ++uidCount;
	}
	
	@Override
	public void dispose () {
		sb.dispose();
	}
}
