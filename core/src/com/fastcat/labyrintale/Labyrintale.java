package com.fastcat.labyrintale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.screens.advisorselect.AdvisorSelectScreen;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.charselect.CharSelectScreen;
import com.fastcat.labyrintale.screens.deckview.DeckViewScreen;
import com.fastcat.labyrintale.screens.mainmenu.MainMenuScreen;
import com.fastcat.labyrintale.screens.map.MapScreen;

public class Labyrintale extends Game {

	public static final int DEFAULT_WIDTH = 1600;
	public static final int DEFAULT_HEIGHT = 900;

	public static Labyrintale game;

	public static PolygonSpriteBatch psb;
	public static SkeletonRenderer sr;

	public static OrthographicCamera camera;

	public static AbstractLabyrinth labyrinth;

	public Array<Screen> tempScreen = new Array<>();
	public Screen preScreen = null;
	public static MainMenuScreen mainMenuScreen;
	public static CharSelectScreen charSelectScreen;
	public static AdvisorSelectScreen advisorSelectScreen;
	public static MapScreen mapScreen;
	public static BattleScreen battleScreen;
	public static DeckViewScreen deckViewScreen;
	public static boolean fading = true;
	public static boolean fadeIn = true;

	private static AbstractScreen nextScreen = null;
	private static Sprite fadeTex;
	private static float alphaCount = 1.0f;
	private static float alphaDex = 1.0f;
	private static int uidCount = 0;

	private InputHandler inputHandler;
	private FontHandler fontHandler;
	private StringHandler stringHandler;
	private SettingHandler settingHandler;
	private FileHandler fileHandler;
	public ActionHandler actionHandler;
	public GroupHandler groupHandler;
	public EffectHandler effectHandler;

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
		fileHandler = new FileHandler();
		fontHandler = new FontHandler();
		settingHandler = new SettingHandler();
		actionHandler = new ActionHandler();
		effectHandler = new EffectHandler();
		groupHandler = new GroupHandler();
		game = this;
		mainMenuScreen = new MainMenuScreen();
		charSelectScreen = new CharSelectScreen();
		advisorSelectScreen = new AdvisorSelectScreen();
		//labyrinth = new AbstractLabyrinth();
		fadeTex = FileHandler.FADE;
		fadeTex.setPosition(0, 0);

		setScreen(mainMenuScreen);
	}

	public void update() {
		camera.update();
		inputHandler.update();
		fontHandler.update();
		actionHandler.update();
		if(AbstractLabyrinth.cPanel != null) AbstractLabyrinth.cPanel.update();
		if(tempScreen.size > 0) {
			Screen s = tempScreen.get(tempScreen.size - 1);
			if(s instanceof AbstractScreen) {
				((AbstractScreen) s).update();
			}
		} else if(screen instanceof AbstractScreen) {
			((AbstractScreen) screen).update();
		}
	}

	@Override
	public void render () {
		/** Update */
		update();

		/** Render */
		ScreenUtils.clear(0, 0, 0, 0.3f);
		sb.setProjectionMatrix(camera.combined);
		sb.enableBlending();
		sb.begin();

		/** Render Methods */
		//actionHandler.render(sb);
		super.render();
		if(tempScreen.size > 0) {
			for(Screen s : tempScreen) {
				if(s != null) s.render(Gdx.graphics.getDeltaTime());
			}
		}
		if(AbstractLabyrinth.cPanel != null) AbstractLabyrinth.cPanel.render(sb);
		/** ============== */
		fade();

		sb.end();
	}

	private void fade() {
		if(fading) {
			if(!fadeIn) {
				alphaCount += Gdx.graphics.getDeltaTime() / alphaDex;
				if(alphaCount > 1.0f) {
					if(nextScreen != null) {
						alphaCount = 1.0f;
						setScreen(nextScreen);
						nextScreen = null;
						fadeIn = true;
					} else fading = false;
				}
			} else {
				alphaCount -= Gdx.graphics.getDeltaTime() / alphaDex;
				if(alphaCount < 0.0f) {
					alphaCount = 0.0f;
					fading = false;
				}
			}
			fadeTex.setAlpha(alphaCount);
			fadeTex.draw(sb);
		}
	}

	public static void fadeOutAndChangeScreen(AbstractScreen screen) {
		fadeOutAndChangeScreen(screen, 1.0f);
	}

	public static void fadeOutAndChangeScreen(AbstractScreen screen, float sec) {
		nextScreen = screen;
		alphaDex = sec;
		fading = true;
		fadeIn = false;
	}

	public static int getUid() {
		return ++uidCount;
	}

	public static AbstractScreen getCurScreen() {
		if(game.tempScreen.size > 0) return (AbstractScreen) game.tempScreen.get(game.tempScreen.size - 1);
		else return (AbstractScreen) game.screen;
	}

	@Override
	public void setScreen (Screen screen) {
		if (this.screen != null) {
			this.screen.hide();
			this.screen.dispose();
			if (this.screen instanceof AbstractScreen) {
				((AbstractScreen) this.screen).effectHandler.removeAll();
			}
		}

		if(tempScreen.size > 0) {
			for(Screen s : tempScreen) {
				s.hide();
				if (s instanceof AbstractScreen) {
					((AbstractScreen) s).effectHandler.removeAll();
				}
			}
			tempScreen.clear();
		}

		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	public static void addTempScreen (Screen screen) {
		game.tempScreen.add(screen);
	}

	public static void removeTempScreen (Screen screen) {
		for(int i = 0; i < game.tempScreen.size; i++) {
			Screen s = game.tempScreen.get(i);
			if(s == screen) {
				s.hide();
				s.dispose();
				if (s instanceof AbstractScreen) {
					((AbstractScreen) s).effectHandler.removeAll();
				}
				game.tempScreen.removeIndex(i);
				break;
			}
		}
	}

	public static void setTempScreen(Screen screen) {
		if (game.screen != null) {
			game.preScreen = game.screen;
			if (game.screen instanceof AbstractScreen) {
				((AbstractScreen) game.screen).effectHandler.removeAll();
			}
		}

		game.screen = screen;
		if (game.screen != null) {
			game.screen.show();
			game.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	public static void closeTempScreen() {
		if (game.screen != null) {
			game.screen.hide();
			if (game.screen instanceof AbstractScreen) {
				((AbstractScreen) game.screen).effectHandler.removeAll();
			}
		}

		game.screen = game.preScreen;
		if (game.screen != null) {
			game.screen.show();
			game.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}
	
	@Override
	public void dispose () {
		sb.dispose();
		fileHandler.dispose();
	}
}
