package com.fastcat.labyrintale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.fastcat.labyrintale.screens.achieve.AchieveScreen;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.charinfo.CharInfoScreen;
import com.fastcat.labyrintale.screens.charselect.CharSelectScreen;
import com.fastcat.labyrintale.screens.dictionary.DictScreen;
import com.fastcat.labyrintale.screens.difficulty.DifficultyScreen;
import com.fastcat.labyrintale.screens.event.EventScreen;
import com.fastcat.labyrintale.screens.library.LibraryScreen;
import com.fastcat.labyrintale.screens.logo.LogoScreen;
import com.fastcat.labyrintale.screens.mainmenu.MainMenuScreen;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoScreen;
import com.fastcat.labyrintale.screens.rest.RestScreen;
import com.fastcat.labyrintale.screens.setting.SettingScreen;
import com.fastcat.labyrintale.screens.shop.ShopScreen;
import com.fastcat.labyrintale.screens.tutorial.TutorialScreen;
import com.fastcat.labyrintale.screens.way.WayScreen;
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
    public static LibraryScreen libScreen;
    public static BattleScreen battleScreen;
    public static CharInfoScreen charInfoScreen;
    public static RestScreen restScreen;
    public static EventScreen eventScreen;
    public static ShopScreen shopScreen;
    public static SettingScreen settingScreen;
    public static TutorialScreen tutorialScreen;
    public static DictScreen dictionary;
    public static AchieveScreen achievement;
    public static boolean fading = false;
    public static boolean fadeIn = false;
    public static boolean tempFade = false;
    public static boolean tutorial = false;
    public static boolean setting = false;
    public static float tick;
    private static AbstractScreen nextScreen = null;
    private static AbstractUI.TempUI change_h;
    private static AbstractUI.TempUI change_v;
    private static AbstractUI.TempUI change_h_r;
    private static AbstractUI.TempUI change_v_r;
    private static FadeType fadeType = FadeType.NONE;
    private static Sprite fadeTex;
    private static float alphaCount = 0;
    private static float alphaDex = 2;
    private static float duration = 0;
    public static AbstractUI subText;
    public Array<AbstractScreen> tempScreen = new Array<>();
    public SpriteBatch sb;

    @Getter
    private static ScreenShake screenShake;

    public static void fadeOutAndChangeScreen(AbstractScreen screen) {
        fadeOutAndChangeScreen(screen, 1.25f);
    }

    public static void fadeOutAndChangeScreen(AbstractScreen screen, float sec) {
        fadeOutAndChangeScreen(screen, FadeType.FADE, sec);
    }

    public static void fadeOutAndChangeScreen(AbstractScreen screen, FadeType type) {
        fadeOutAndChangeScreen(screen, type, 1.25f);
    }

    public static void fadeOutAndChangeScreen(AbstractScreen screen, FadeType type, float sec) {
        nextScreen = screen;
        alphaDex = sec;
        fading = true;
        fadeIn = false;
        tempFade = false;
        fadeType = type;
        duration = 0;
        if(type == FadeType.HORIZONTAL) {
            change_h.setPosition(Gdx.graphics.getWidth(), 0);
            change_h_r.setPosition(0, 0);
        } else if(type == FadeType.VERTICAL) {
            change_v.setPosition(0, Gdx.graphics.getHeight());
            change_v_r.setPosition(0, 0);
        }
    }

    public static void fadeOutAndAddScreen(AbstractScreen screen) {
        fadeOutAndAddScreen(screen, FadeType.FADE);
    }

    public static void fadeOutAndAddScreen(AbstractScreen screen, float sec) {
        fadeOutAndAddScreen(screen, FadeType.FADE, sec);
    }

    public static void fadeOutAndAddScreen(AbstractScreen screen, FadeType type) {
        fadeOutAndAddScreen(screen, type, 1.25f);
    }

    public static void fadeOutAndAddScreen(AbstractScreen screen, FadeType type, float sec) {
        nextScreen = screen;
        alphaDex = sec;
        fading = true;
        fadeIn = false;
        tempFade = true;
        fadeType = type;
        duration = 0;
        if(type == FadeType.HORIZONTAL) {
            change_h.setPosition(Gdx.graphics.getWidth(), 0);
            change_h_r.setPosition(0, 0);
        } else if(type == FadeType.VERTICAL) {
            change_v.setPosition(0, Gdx.graphics.getHeight());
            change_v_r.setPosition(0, 0);
        }
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
        //Gdx.graphics.setResizable(false);
        //Gdx.graphics.setTitle("Wakest Dungeon - " + BuildInfo.BUILD_VERSION);
/*
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();

        if (SettingHandler.setting.screenMode == 0) {
            Gdx.graphics.setWindowedMode(SettingHandler.setting.width, SettingHandler.setting.height);
        } else if (SettingHandler.setting.screenMode == 1) {
            Gdx.graphics.setFullscreenMode(displayMode);
        } else {
            Gdx.graphics.setUndecorated(true);
            Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
        }
        */
        Pixmap pix = new Pixmap(Gdx.files.internal("img/ui/cursor_b.png"));
        pix.setFilter(Pixmap.Filter.BiLinear);
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pix, 0, 0));
        pix.dispose();

        screenShake = ScreenShake.newInstance();
        camera = new OrthographicCamera();
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        camera.setToOrtho(false, w, h);
        viewport = new FitViewport(w, h);
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
        UnlockHandler.load();
        AchieveHandler.load();

        game = this;
        mainMenuScreen = new MainMenuScreen();
        charSelectScreen = new CharSelectScreen();
        settingScreen = new SettingScreen();
        diffScreen = new DifficultyScreen();
        libScreen = new LibraryScreen();
        tutorialScreen = new TutorialScreen();
        dictionary = new DictScreen();
        achievement = new AchieveScreen();
        // labyrinth = new AbstractLabyrinth();
        fadeTex = FileHandler.getUi().get("FADE");
        fadeTex.setPosition(0, 0);

        change_h = new AbstractUI.TempUI(FileHandler.getUi().get("CHANGE_H"));
        change_h_r = new AbstractUI.TempUI(FileHandler.getUi().get("CHANGE_H"));
        change_v = new AbstractUI.TempUI(FileHandler.getUi().get("CHANGE_V"));
        change_v_r = new AbstractUI.TempUI(FileHandler.getUi().get("CHANGE_V"));

        change_h_r.img.setFlip(true, false);
        change_v_r.img.setFlip(false, true);

        mainMenuScreen.onCreate();
        setScreen(new LogoScreen());
    }

    public void update() {
        tick = Gdx.graphics.getDeltaTime();
        camera.update();
        screenShake.update(viewport);
        InputHandler.getInstance().update();
        FontHandler.getInstance().update();
        subText = null;
        if (!setting && !tutorial && labyrinth != null) {
            labyrinth.update();
        }
        if (setting || settingScreen.anim) {
            settingScreen.update();
        } else if (tutorial) {
            tutorialScreen.update();
        } else if (tempScreen.size > 0) {
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
        psb.setProjectionMatrix(camera.combined);
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
        if (tutorial) tutorialScreen.render(sb);
        if (setting || settingScreen.anim) settingScreen.render(sb);
        /** ============== */
        if(fadeType == FadeType.FADE) {
            fade();
        } else if(fadeType == FadeType.HORIZONTAL) {
            change_H();
        } else if(fadeType == FadeType.VERTICAL) {
            change_V();
        }

        /*
        sb.setColor(Color.WHITE);
        sb.draw(cursor.img, InputHandler.mx, InputHandler.my - cursor.height / 2, cursor.width / 2, cursor.height / 2);
*/
        sb.end();
    }

    private void fade() {
        if (fading) {
            if (!fadeIn) {
                alphaCount += Labyrintale.tick / alphaDex;
                if (alphaCount > 1.0f) {
                    if (nextScreen != null) {
                        alphaCount = 1.0f;
                        if (setting) closeSetting();
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
                    fadeType = FadeType.NONE;
                }
            }
            fadeTex.draw(sb, alphaCount);
        }
    }

    private void change_H() {
        if (fading) {
            float add = Labyrintale.tick / alphaDex, ac = Labyrintale.tick / 0.3f, ad = 0.3f / alphaDex;
            float xx = Gdx.graphics.getWidth() - change_h.sWidth;
            AbstractScreen s = getCurScreen();
            if (!fadeIn) {
                if(s == null || s.type != AbstractScreen.ScreenType.LOAD) {
                    if (change_h.x > xx) {
                        change_h.x -= change_h.sWidth * ac;
                        if (change_h.x <= xx) change_h.x = xx;
                    }
                }
                duration += add;
                if (duration >= 1) {
                    if (nextScreen != null) {
                        duration = 1;
                        if (setting) closeSetting();
                        if (!tempFade) setScreen(nextScreen);
                        else addTempScreen(nextScreen);
                        nextScreen = null;
                        fadeIn = true;
                    } else fading = false;
                }
                change_h.render(sb);
            } else {
                if(s == null || s.type != AbstractScreen.ScreenType.LOAD) {
                    float w = change_h_r.sWidth;
                    if (duration <= ad && change_h_r.x > -w) {
                        change_h_r.x -= w * ac;
                        if (change_h_r.x <= -w) change_h_r.x = -w;
                    }
                }
                duration -= add;
                if (duration <= 0) {
                    duration = 0;
                    fading = false;
                    fadeType = FadeType.NONE;
                }
                change_h_r.render(sb);
            }
        }
    }

    private void change_V() {
        if (fading) {
            float add = Labyrintale.tick / alphaDex, ac = Labyrintale.tick / 0.3f, ad = 0.3f / alphaDex;
            AbstractScreen s = getCurScreen();
            if (!fadeIn) {
                if(s == null || s.type != AbstractScreen.ScreenType.LOAD) {
                    float yy = Gdx.graphics.getHeight() - change_v.sHeight;
                    if (change_v.y > yy) {
                        change_v.y -= change_v.sHeight * ac;
                        if (change_v.y <= yy) change_v.y = yy;
                    }
                }
                duration += add;
                if (duration >= 1) {
                    if (nextScreen != null) {
                        duration = 1;
                        if (setting) closeSetting();
                        if (!tempFade) setScreen(nextScreen);
                        else addTempScreen(nextScreen);
                        nextScreen = null;
                        fadeIn = true;
                    } else fading = false;
                }
                change_v.render(sb);
            } else {
                if(s == null || s.type != AbstractScreen.ScreenType.LOAD) {
                    float yy = -change_v_r.sHeight;
                    if (duration <= ad && change_v_r.y > yy) {
                        change_v_r.y -= change_v_r.sHeight * ac;
                        if (change_v_r.y <= yy) change_v_r.y = yy;
                    }
                }
                duration -= add;
                if (duration <= 0) {
                    duration = 0;
                    fading = false;
                    fadeType = FadeType.NONE;
                }
                change_v_r.render(sb);
            }
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

        Labyrintale.closeTutorial();
        setting = false;
        settingScreen.hide();
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
        fadeOutAndChangeScreen(wayScreen, FadeType.VERTICAL);
    }

    public static void openTutorial(TutorialScreen.TutorialType type) {
        tutorialScreen.setType(type);
        tutorial = true;
    }

    public static void closeTutorial() {
        tutorial = false;
    }

    public static void openSetting() {
        setting = true;
        settingScreen.anim = true;
    }

    public static void closeSetting() {
        SettingHandler.save();
        setting = false;
        settingScreen.anim = true;
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

    public enum FadeType {
        FADE, HORIZONTAL, VERTICAL, NONE
    }
}
