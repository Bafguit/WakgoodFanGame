package com.fastcat.labyrintale.screens.difficulty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.uis.GifBg;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class DifficultyScreen extends AbstractScreen {

    private DifficultyButton[] buttons = new DifficultyButton[3];
    public BackButton backButton;

    public DifficultyScreen() {
        for (int i = 0; i < 3; i++) {
            DifficultyButton b = new DifficultyButton(AbstractLabyrinth.Difficulty.values()[i]);
            b.setPosition((680 + 600 * i) * scale - b.sWidth / 2, 424 * scale);
            buttons[i] = b;
        }
        backButton = new BackButton();
        setBg(FileHandler.getBg().get("BG_DIFF"));
    }

    @Override
    public void update() {
        for (DifficultyButton b : buttons) {
            b.update();
        }
        backButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for (DifficultyButton b : buttons) {
            b.render(sb);
        }
        backButton.render(sb);
    }

    @Override
    public void show() {
        backButton.over = false;
        backButton.show();
        for (DifficultyButton b : buttons) {
            b.over = false;
            b.show();
        }
    }
}
