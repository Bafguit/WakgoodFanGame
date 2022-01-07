package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;

public class MainMenuScreen extends AbstractScreen {

    private final LogoText logoText;
    private final GameStartButton gameStartButton;
    private final TutorialButton tutorialButton;
    private final OptionButton optionButton;
    private final ExitButton exitButton;

    public MainMenuScreen(Labyrintale game) {
        super(game);
        bg = FileHandler.BG_MAIN;
        logoText = new LogoText();
        gameStartButton = new GameStartButton();
        tutorialButton = new TutorialButton();
        optionButton = new OptionButton();
        exitButton = new ExitButton();
    }

    @Override
    public void update() {

        logoText.update();
        gameStartButton.update();
        tutorialButton.update();
        optionButton.update();
        exitButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        logoText.render(sb);
        gameStartButton.render(sb);
        tutorialButton.render(sb);
        optionButton.render(sb);
        exitButton.render(sb);
    }

    @Override
    public void show() {
        logoText.onHide();
        gameStartButton.onHide();
        tutorialButton.onHide();
        optionButton.onHide();
        exitButton.onHide();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
