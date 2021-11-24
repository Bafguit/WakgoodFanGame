package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.GroupHandler;

public class MainMenuScreen extends AbstractScreen {

    private TextureAtlas atlas;
    private Skeleton skeleton;
    private AnimationState state;
    private AnimationStateData stateData;

    private LogoText logoText;
    private GameStartButton gameStartButton;
    private TutorialButton tutorialButton;
    private OptionButton optionButton;
    private ExitButton exitButton;

    public MainMenuScreen(Labyrintale game) {
        super(game);
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
        game.groupHandler = new GroupHandler();
    }

    @Override
    public void hide() {
        logoText.onHide();
        gameStartButton.onHide();
        tutorialButton.onHide();
        optionButton.onHide();
        exitButton.onHide();
    }

    @Override
    public void dispose() {
    }
}
