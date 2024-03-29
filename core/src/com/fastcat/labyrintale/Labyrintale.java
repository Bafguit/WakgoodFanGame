package com.fastcat.labyrintale;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.charinfo.CharInfoScreen;
import com.fastcat.labyrintale.screens.charselect.CharSelectScreen;
import com.fastcat.labyrintale.screens.custom.CustomModeScreen;
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
import com.fastcat.labyrintale.uis.GifBg;
import com.fastcat.labyrintale.utils.AsynchronousGifLoader;
import com.fastcat.labyrintale.utils.FillViewport;
import com.fastcat.labyrintale.utils.Gif;
import com.google.common.util.concurrent.FutureCallback;
import lombok.Getter;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class Labyrintale extends Game {

    public static final String BUILD_VERSION = "1.2.2";

    public static Labyrintale game;

    private static LifeCycle phase;
    public static PolygonSpriteBatch psb;
    public static SkeletonRenderer sr;

    public static OrthographicCamera camera;
    public static Viewport viewport;

    public static AbstractLabyrinth labyrinth;
    public static MainMenuScreen mainMenuScreen;
    public static CharSelectScreen charSelectScreen;
    public static PlayerInfoScreen playerInfoScreen;
    public static MapScreen mapScreen;
    public static WayScreen wayScreen;
    public static DifficultyScreen diffScreen;
    public static CustomModeScreen modeScreen;
    public static LibraryScreen libScreen;
    public static BattleScreen battleScreen;
    public static CharInfoScreen charInfoScreen;
    public static RestScreen restScreen;
    public static EventScreen eventScreen;
    public static ShopScreen shopScreen;
    public static SettingScreen settingScreen;
    public static TutorialScreen tutorialScreen;
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
    private static GifBg battle;
    private static FadeType fadeType = FadeType.NONE;
    private static Sprite fadeTex;
    private static float alphaCount = 0;
    private static float alphaDex = 2;
    private static float duration = 0;
    public static AbstractUI subText;
    private static ScreenShake screenShake;
    public Array<AbstractScreen> tempScreen = new Array<>();
    public SpriteBatch sb;
    private boolean loaded = false;

    private boolean asyncComplete = false;

    private boolean start = false;

    private AbstractUI.TempUI back;
    private Sprite process;

    @Getter
    private AssetManager assetManager;

    public ResourceHandler resourceHandler;

    private Queue<Runnable> queuedTasks = new Queue<>();

    public static ScreenShake getScreenShake() {
        if (screenShake == null) screenShake = ScreenShake.newInstance();
        return screenShake;
    }

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
        if (type == FadeType.HORIZONTAL) {
            change_h.setPosition(Gdx.graphics.getWidth(), 0);
            change_h_r.setPosition(0, 0);
        } else if (type == FadeType.VERTICAL) {
            change_v.setPosition(0, Gdx.graphics.getHeight());
            change_v_r.setPosition(0, 0);
        } else if (type == FadeType.BATTLE) {
            if(InputHandler.isDesktop) {
                battle = new GifBg("BATTLE");
                battle.speed = 1;
            } else fadeType = FadeType.FADE;
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
        if (type == FadeType.HORIZONTAL) {
            change_h.setPosition(Gdx.graphics.getWidth(), 0);
            change_h_r.setPosition(0, 0);
        } else if (type == FadeType.VERTICAL) {
            change_v.setPosition(0, Gdx.graphics.getHeight());
            change_v_r.setPosition(0, 0);
        } else if (type == FadeType.BATTLE) {
            if(InputHandler.isDesktop) {
                battle = new GifBg("BATTLE");
                battle.speed = 1;
            } else fadeType = FadeType.FADE;
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
    public void pause() {


    }

    @Override
    public void create() {
        game = this;

        SettingHandler.initialize();

        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();

            Gdx.graphics.setForegroundFPS(displayMode.refreshRate);

            if (SettingHandler.setting.screenMode == 0) {
                Gdx.graphics.setWindowedMode(SettingHandler.setting.width, SettingHandler.setting.height);
            } else if (SettingHandler.setting.screenMode == 1) {
                Gdx.graphics.setFullscreenMode(displayMode);
            } else {
                Gdx.graphics.setUndecorated(true);

                final int height, width;
                int w = displayMode.width, h = displayMode.height;
                float a = ((float) h) / ((float) w);

                if(a <= 0.5625f) {
                    height = h;
                    width = (int) (2560 * ((float) h / 1440.0f));
                } else {
                    width = w;
                    height = (int) (1440 * ((float) w / 2560.0f));
                }
                Gdx.graphics.setWindowedMode(width, height);
            }
        }

        InputHandler.getInstance();
        SettingHandler.save();

        phase = LifeCycle.STARTED;
        assetManager = new AssetManager();
        assetManager.setLoader(Gif.class, new AsynchronousGifLoader(new InternalFileHandleResolver()));
        resourceHandler = new ResourceHandler(assetManager);
        if (InputHandler.isDesktop) {
            Gdx.graphics.setResizable(System.getProperty("os.name").contains("mac"));
            Gdx.graphics.setTitle("Wakest Dungeon");
            Pixmap pix = new Pixmap(Gdx.files.internal("img/ui/cursor_b.png"));
            pix.setFilter(Pixmap.Filter.BiLinear);
            Gdx.graphics.setCursor(Gdx.graphics.newCursor(pix, 0, 0));
        }

        getScreenShake();

        camera = new OrthographicCamera();
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        camera.setToOrtho(false, w, h);
        viewport = new FillViewport(w, h);
        sb = new SpriteBatch();
        psb = new PolygonSpriteBatch();
        sr = new SkeletonRenderer();

        sr.setPremultipliedAlpha(false);
        FileHandler.getInstance();
        FontHandler.getInstance();
        init();
    }

    private void init() {

        AsyncHandler.scheduleAsyncTask(
                () -> {
                    FileHandler.getInstance().loadFiles();
                    FileHandler.getInstance().loadResources(resourceHandler);
                    return new Object();
                },
                new FutureCallback<Object>() {
                    @Override
                    public void onSuccess(Object result) {
                        phase = LifeCycle.LOADING;
                        System.out.println("phase : " + phase);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        throw new RuntimeException(t);
                    }
                });
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

    /**
     * Must be called after all assets are loaded
     */
    private void load() {

        SoundHandler.getInstance();
        ActionHandler.getInstance();
        GroupHandler.getInstance();
        UnlockHandler.load();
        AchieveHandler.load();
        CustomHandler.initialize();
        mainMenuScreen = new MainMenuScreen();
        charSelectScreen = new CharSelectScreen();
        settingScreen = new SettingScreen();
        diffScreen = new DifficultyScreen();
        modeScreen = new CustomModeScreen();
        libScreen = new LibraryScreen();
        tutorialScreen = new TutorialScreen();
        // labyrinth = new AbstractLabyrinth();
        fadeTex = FileHandler.getUi().get("FADE");
        fadeTex.setPosition(0, 0);


        change_h = new AbstractUI.TempUI(FileHandler.getUi().get("CHANGE_H"));
        change_h_r = new AbstractUI.TempUI(FileHandler.getUi().get("CHANGE_H"));
        change_v = new AbstractUI.TempUI(FileHandler.getUi().get("CHANGE_V"));
        change_v_r = new AbstractUI.TempUI(FileHandler.getUi().get("CHANGE_V"));
        battle = new GifBg("BATTLE");

        change_h_r.hFlip = true;
        change_v_r.vFlip = true;

        mainMenuScreen.onCreate();

        fadeOutAndChangeScreen(new LogoScreen(), 0.3f);
    }

    public void submitTask(Runnable t) {
        queuedTasks.addLast(t);
    }

    @Override
    public void render() {
        if (phase == LifeCycle.STARTED) {
            return;
        }

        if (phase == LifeCycle.LOADING) {
            if (resourceHandler.process()) phase = LifeCycle.FINISHING;
        }
        if (phase == LifeCycle.FINISHING) {
            load();
            phase = LifeCycle.ENDED;
        }


        if (phase == LifeCycle.ENDED) {
            /** Update */
            update();
        }

        /** Render */
        ScreenUtils.clear(0, 0, 0, 1);
        psb.setProjectionMatrix(camera.combined);
        sb.setProjectionMatrix(camera.combined);
        sb.enableBlending();
        sb.begin();

        /** Render Methods */
        // actionHandler.render(sb);
        super.render();

        if (phase == LifeCycle.ENDED) {
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
            if (fadeType == FadeType.FADE) {
                fade();
            } else if (fadeType == FadeType.HORIZONTAL) {
                change_H();
            } else if (fadeType == FadeType.VERTICAL) {
                change_V();
            } else if (fadeType == FadeType.BATTLE) {
                fadeBattle();
            }

        /*
        		sb.setColor(Color.WHITE);
        		sb.draw(cursor.img, InputHandler.mx, InputHandler.my - cursor.height / 2, cursor.width / 2, cursor.height / 2);
        */
        } else {
            float p = resourceHandler.getProgress();
            FontHandler.renderCenter(sb, FontHandler.SETTING, "리소스 불러오는 중\n" + p * 100 + "%",
                    1080 * scale, 720 * scale, 400 * scale, 200);
        }
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

    private void fadeBattle() {
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
                        SoundHandler.playSfx("BATTLE");
                    } else fading = false;
                }
                fadeTex.draw(sb, alphaCount);
            } else {
                alphaCount -= Labyrintale.tick / 1.62f;
                if (alphaCount < 0.0f) {
                    alphaCount = 0.0f;
                    fading = false;
                    fadeType = FadeType.NONE;
                }
                battle.render(sb);
            }
        }
    }

    private void change_H() {
        if (fading) {
            float add = Labyrintale.tick / alphaDex, ac = Labyrintale.tick / 0.3f, ad = 0.3f / alphaDex;
            float xx = Gdx.graphics.getWidth() - change_h.sWidth;
            AbstractScreen s = getCurScreen();
            if (!fadeIn) {
                if (s == null || s.type != AbstractScreen.ScreenType.LOAD) {
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
                if (s == null || s.type != AbstractScreen.ScreenType.LOAD) {
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
                if (s == null || s.type != AbstractScreen.ScreenType.LOAD) {
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
                if (s == null || s.type != AbstractScreen.ScreenType.LOAD) {
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
        fadeOutAndChangeScreen(wayScreen);
    }

    public static void openTutorial(TutorialScreen.TutorialType type) {
        tutorialScreen.setType(type);
        tutorialScreen.close.clickable = false;
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
        FADE,
        HORIZONTAL,
        VERTICAL,
        BATTLE,
        NONE
    }
}
