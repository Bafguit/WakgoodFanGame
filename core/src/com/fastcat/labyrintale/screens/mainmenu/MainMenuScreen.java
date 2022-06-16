package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.uis.CheckBox;
import com.fastcat.labyrintale.uis.slidebar.SlideBar;

import static com.fastcat.labyrintale.handlers.FileHandler.bg;

public class MainMenuScreen extends AbstractScreen {

    private final LogoText logoText;
    private final GameStartButton gameStartButton;
    private final TutorialButton tutorialButton;
    private final OptionButton optionButton;
    private final ExitButton exitButton;

    public SlideBar slide;
    public CheckBox check;

    public MainMenuScreen() {
        setBg(bg.get("BG_MAIN"));
        logoText = new LogoText();
        gameStartButton = new GameStartButton();
        tutorialButton = new TutorialButton();
        optionButton = new OptionButton();
        exitButton = new ExitButton();

        slide = new SlideBar(100, 100, 300, 50);
        check = new CheckBox(100, 300, false);
    }

    @Override
    public void update() {
        logoText.update();
        gameStartButton.update();
        tutorialButton.update();
        optionButton.update();
        exitButton.update();

        slide.update();
        check.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        logoText.render(sb);
        gameStartButton.render(sb);
        tutorialButton.render(sb);
        optionButton.render(sb);
        exitButton.render(sb);

        slide.render(sb);
        check.render(sb);
    }

    @Override
    public void show() {
        logoText.onHide();
        gameStartButton.onHide();
        tutorialButton.onHide();
        optionButton.onHide();
        exitButton.onHide();
        SaveHandler.refresh();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
