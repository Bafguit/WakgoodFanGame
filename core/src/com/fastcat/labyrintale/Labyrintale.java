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
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.charinfo.CharInfoScreen;
import com.fastcat.labyrintale.screens.charselect.CharSelectScreen;
import com.fastcat.labyrintale.screens.event.EventScreen;
import com.fastcat.labyrintale.screens.logo.LogoScreen;
import com.fastcat.labyrintale.screens.mainmenu.MainMenuScreen;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.screens.rest.RestScreen;
import com.fastcat.labyrintale.screens.setting.SettingScreen;
import com.fastcat.labyrintale.screens.shop.ShopScreen;
import lombok.Getter;

public class Labyrintale extends Game {

    public static Labyrintale game;

    public static PolygonSpriteBatch psb;
    public static SkeletonRenderer sr;

    public static OrthographicCamera camera;
    public static FitViewport viewport;
    public static VideoPlayer videoPlayer;

    public static AbstractLabyrinth labyrinth;
    public static MainMenuScreen mainMenuScreen;
    public static CharSelectScreen charSelectScreen;
    public static MapScreen mapScreen;
    public static BattleScreen battleScreen;
    public static CharInfoScreen charInfoScreen;
    public static RestScreen restScreen;
    public static EventScreen eventScreen;
    public static ShopScreen shopScreen;
    public static SettingScreen settingScreen;
    public static boolean fading = false;
    public static boolean fadeIn = false;
    public static float tick;
    private static AbstractScreen nextScreen = null;
    private static Sprite fadeTex;
    private static float alphaCount = 0;
    private static float alphaDex = 2;
    public Array<AbstractScreen> tempScreen = new Array<>();
    public SpriteBatch sb;

    @Getter
    private static ScreenShake screenShake;

    public static void fadeOutAndChangeScreen(AbstractScreen screen) {
        fadeOutAndChangeScreen(screen, 1.0f);
    }

    public static void fadeOutAndChangeScreen(AbstractScreen screen, float sec) {
        nextScreen = screen;
        alphaDex = sec;
        fading = true;
        fadeIn = false;
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

        if (SettingHandler.setting.screenMode == 0) { //창모드
            Gdx.graphics.setWindowedMode(SettingHandler.setting.width, SettingHandler.setting.height);
        } else if (SettingHandler.setting.screenMode == 1) { //전체화면
            Gdx.graphics.setFullscreenMode(displayMode);
        } else { //전체창
            Gdx.graphics.setUndecorated(true);
            Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
        }
        screenShake = ScreenShake.newInstance();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SettingHandler.setting.width, SettingHandler.setting.height);
        viewport = new FitViewport(SettingHandler.setting.width, SettingHandler.setting.height, camera);
        videoPlayer = VideoPlayerCreator.createVideoPlayer();
        sb = new SpriteBatch();
        psb = new PolygonSpriteBatch();
        sr = new SkeletonRenderer();
        sr.setPremultipliedAlpha(false);
        game = this;
        mainMenuScreen = new MainMenuScreen();
        charSelectScreen = new CharSelectScreen();
        settingScreen = new SettingScreen();
        //labyrinth = new AbstractLabyrinth();
        fadeTex = FileHandler.getUi().get("FADE");
        fadeTex.setPosition(0, 0);

        setScreen(new LogoScreen());
    }

    public void update() {
        tick = Gdx.graphics.getDeltaTime();
        camera.update();
        screenShake.update(viewport);
        InputHandler.getInstance().update();
        InputHandler.getInstance().update();
        InputHandler.getInstance().update();
        FontHandler.getInstance().update();
        ActionHandler.getInstance().update();
        if (AbstractLabyrinth.cPanel != null) AbstractLabyrinth.cPanel.update();
        if (tempScreen.size > 0) {
            AbstractScreen s = tempScreen.get(tempScreen.size - 1);
            if (s != null) {
                s.update();
            }
        } else if (screen instanceof AbstractScreen) {
            ((AbstractScreen) screen).update();
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
        //actionHandler.render(sb);
        super.render();
        if (tempScreen.size > 0) {
            for (Screen s : tempScreen) {
                if (s != null) s.render(Labyrintale.tick);
            }
        }
        if (AbstractLabyrinth.cPanel != null) AbstractLabyrinth.cPanel.render(sb);
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
                        setScreen(nextScreen);
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
            //fadeTex.setAlpha(alphaCount);
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
        if (videoPlayer != null) videoPlayer.dispose();
    }
}
