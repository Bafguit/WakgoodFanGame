package com.fastcat.labyrintale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.charinfo.CharInfoScreen;
import com.fastcat.labyrintale.screens.charselect.CharSelectScreen;
import com.fastcat.labyrintale.screens.difficultyscreen.DifficultyScreen;
import com.fastcat.labyrintale.screens.event.EventScreen;
import com.fastcat.labyrintale.screens.mainmenu.MainMenuScreen;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoScreen;
import com.fastcat.labyrintale.screens.rest.RestScreen;
import com.fastcat.labyrintale.screens.setting.SettingScreen;
import com.fastcat.labyrintale.screens.shop.ShopScreen;
import com.fastcat.labyrintale.screens.way.WayScreen;
import io.github.singlerr.Main;
import lombok.Getter;

public class Labyrintale extends Game {

  public static Labyrintale game;

  public static PolygonSpriteBatch psb;
  public static SkeletonRenderer sr;

  public static OrthographicCamera camera;
  public static FitViewport viewport;

  public static AbstractLabyrinth labyrinth;
  public static MainMenuScreen mainMenuScreen;
  public static CharSelectScreen charSelectScreen;
  public static PlayerInfoScreen playerInfoScreen;
  public static MapScreen mapScreen;
  public static WayScreen wayScreen;
  public static DifficultyScreen diffScreen;
  public static BattleScreen battleScreen;
  public static CharInfoScreen charInfoScreen;
  public static RestScreen restScreen;
  public static EventScreen eventScreen;
  public static ShopScreen shopScreen;
  public static SettingScreen settingScreen;
  public static boolean fading = false;
  public static boolean fadeIn = false;
  public static boolean tempFade = false;
  public static float tick;
  private static AbstractScreen nextScreen = null;
  private static Sprite fadeTex;
  private static float alphaCount = 0;
  private static float alphaDex = 2;
  public static AbstractUI subText;
  public Array<AbstractScreen> tempScreen = new Array<>();
  public SpriteBatch sb;

  @Getter private static ScreenShake screenShake;

  public static void fadeOutAndChangeScreen(AbstractScreen screen) {
    fadeOutAndChangeScreen(screen, 1.0f);
  }

  public static void fadeOutAndChangeScreen(AbstractScreen screen, float sec) {
    nextScreen = screen;
    alphaDex = sec;
    fading = true;
    fadeIn = false;
    tempFade = false;
  }

  public static void fadeOutAndAddScreen(AbstractScreen screen) {
    fadeOutAndAddScreen(screen, 1.0f);
  }

  public static void fadeOutAndAddScreen(AbstractScreen screen, float sec) {
    nextScreen = screen;
    alphaDex = sec;
    fading = true;
    fadeIn = false;
    tempFade = true;
  }

  public static AbstractScreen getCurScreen() {
    if (game.tempScreen.size > 0) return game.tempScreen.get(game.tempScreen.size - 1);
    else return (AbstractScreen) game.screen;
  }

  public static AbstractScreen getBaseScreen() {
    return (AbstractScreen) game.screen;
  }

  public static void addTempScreen(AbstractScreen screen) {
    removeTempScreen(screen);
    screen.show();
    game.tempScreen.add(screen);
  }

  public static void removeTempScreen(AbstractScreen screen) {
    for (int i = 0; i < game.tempScreen.size; i++) {
      AbstractScreen s = game.tempScreen.get(i);
      if (s == screen) {
        s.hide();
        s.atEndOfTempScreen();
        s.getEffectHandler().removeAll();
        game.tempScreen.removeIndex(i);
        break;
      }
    }
  }

  @Override
  public void create() {
    Gdx.graphics.setResizable(false);
    Gdx.graphics.setTitle("Wakest Dungeon");
    Pixmap pix = new Pixmap(Gdx.files.internal("img/ui/cursor.png"));
    pix.setFilter(Pixmap.Filter.BiLinear);
    Gdx.graphics.setCursor(Gdx.graphics.newCursor(pix, 0, 0));
    SettingHandler.initialize();

    Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode(InputHandler.monitor);

    if (SettingHandler.setting.screenMode == 0) { // 창모드
      Gdx.graphics.setWindowedMode(SettingHandler.setting.width, SettingHandler.setting.height);
    } else if (SettingHandler.setting.screenMode == 1) { // 전체화면
      Gdx.graphics.setFullscreenMode(displayMode);
    } else { // 전체창
      Gdx.graphics.setUndecorated(true);
      Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
    }

    screenShake = ScreenShake.newInstance();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, SettingHandler.setting.width, SettingHandler.setting.height);
    viewport = new FitViewport(SettingHandler.setting.width, SettingHandler.setting.height, camera);
    sb = new SpriteBatch();
    psb = new PolygonSpriteBatch();
    sr = new SkeletonRenderer();
    sr.setPremultipliedAlpha(false);
    InputHandler.getInstance();
    FileHandler.getInstance();
    FontHandler.getInstance();
    SoundHandler.getInstance();
    ActionHandler.getInstance();
    GroupHandler.getInstance();

    game = this;
    mainMenuScreen = new MainMenuScreen();
    charSelectScreen = new CharSelectScreen();
    settingScreen = new SettingScreen();
    diffScreen = new DifficultyScreen();
    // labyrinth = new AbstractLabyrinth();
    fadeTex = FileHandler.getUi().get("FADE");
    fadeTex.setPosition(0, 0);

    mainMenuScreen.onCreate();
    fadeOutAndChangeScreen(mainMenuScreen);
    // setScreen(new LogoScreen());
    /** Generate csv files If you don't want this task, comment below */
    Main.main(new String[] {});
  }

  public void update() {
    tick = Gdx.graphics.getDeltaTime();
    camera.update();
    screenShake.update(viewport);
    InputHandler.getInstance().update();
    InputHandler.getInstance().update();
    InputHandler.getInstance().update();
    FontHandler.getInstance().update();
    subText = null;
    if (labyrinth != null) {
      labyrinth.update();
    }
    if (tempScreen.size > 0) {
      AbstractScreen s = tempScreen.get(tempScreen.size - 1);
      if (s != null) {
        s.update();
        s.getEffectHandler().update();
      }
    } else if (screen instanceof AbstractScreen) {
      if (AbstractLabyrinth.cPanel != null) {
        ActionHandler.getInstance().update();
      }
      AbstractScreen s = (AbstractScreen) screen;
      s.update();
      s.getEffectHandler().update();
    }
    SoundHandler.getInstance().update();
  }

  @Override
  public void render() {
    /** Update */
    update();

    /** Render */
    ScreenUtils.clear(0, 0, 0, 0.3f);
    sb.setProjectionMatrix(camera.combined);
    sb.enableBlending();
    sb.begin();

    /** Render Methods */
    // actionHandler.render(sb);
    super.render();
    if (tempScreen.size > 0) {
      for (Screen s : tempScreen) {
        if (s != null) s.render(Labyrintale.tick);
      }
    }
    if (AbstractLabyrinth.cPanel != null) AbstractLabyrinth.cPanel.render(sb);
    if (subText != null) subText.renderSub(sb);
    /** ============== */
    fade();

    sb.end();
  }

  private void fade() {
    if (fading) {
      if (!fadeIn) {
        alphaCount += Labyrintale.tick / alphaDex;
        if (alphaCount > 1.0f) {
          if (nextScreen != null) {
            alphaCount = 1.0f;
            if (!tempFade) setScreen(nextScreen);
            else addTempScreen(nextScreen);
            nextScreen = null;
            fadeIn = true;
          } else fading = false;
        }
      } else {
        alphaCount -= Labyrintale.tick / alphaDex;
        if (alphaCount < 0.0f) {
          alphaCount = 0.0f;
          fading = false;
        }
      }
      // fadeTex.setAlpha(alphaCount);
      fadeTex.draw(sb, alphaCount);
    }
  }

  @Override
  public void setScreen(Screen screen) {
    if (this.screen != null) {
      this.screen.hide();
      this.screen.dispose();
      if (this.screen instanceof AbstractScreen) {
        ((AbstractScreen) this.screen).getEffectHandler().removeAll();
      }
    }

    if (tempScreen.size > 0) {
      for (AbstractScreen s : tempScreen) {
        s.hide();
        s.atEndOfTempScreen();
        s.getEffectHandler().removeAll();
      }
      tempScreen.clear();
    }

    this.screen = screen;
    if (this.screen != null) this.screen.show();
  }

  public static void returnToWay() {
    wayScreen = new WayScreen();
    fadeOutAndChangeScreen(wayScreen, 1.5f);
  }

  @Override
  public void dispose() {
    sb.dispose();
    FileHandler.getInstance().dispose();
    FontHandler.getInstance().dispose();
    SoundHandler.getInstance().dispose();
    for (AbstractScreen s : tempScreen) {
      s.dispose();
    }
    if (mainMenuScreen != null) mainMenuScreen.dispose();
    if (charSelectScreen != null) charSelectScreen.dispose();
    if (mapScreen != null) mapScreen.dispose();
    if (battleScreen != null) battleScreen.dispose();
    if (charInfoScreen != null) charInfoScreen.dispose();
    if (restScreen != null) restScreen.dispose();
    if (eventScreen != null) eventScreen.dispose();
    if (shopScreen != null) shopScreen.dispose();
  }
}
