package com.fastcat.labyrintale.screens.difficultyscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.charselect.BackButton;

public class DifficultyScreen extends AbstractScreen {

    private DifficultyButton[] buttons = new DifficultyButton[3];
    public BackButton backButton;

    public DifficultyScreen() {
        setBg(FileHandler.getBg().get("BG_MAIN"));
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < 3; i++) {
            DifficultyButton b = new DifficultyButton(AbstractLabyrinth.Difficulty.values()[i]);
            b.setPosition(w * 0.2f + w * 0.3f * i - b.sWidth / 2, h * 0.45f - b.sHeight / 2);
            buttons[i] = b;
        }
        backButton = new BackButton();
    }

    @Override
    public void update() {
        for(DifficultyButton b : buttons) {
            b.update();
        }
        backButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for(DifficultyButton b : buttons) {
            b.render(sb);
        }
        backButton.render(sb);
    }

    @Override
    public void show() {

    }
}