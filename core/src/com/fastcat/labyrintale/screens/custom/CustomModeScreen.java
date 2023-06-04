package com.fastcat.labyrintale.screens.custom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.screens.runview.IndexButton;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class CustomModeScreen extends AbstractScreen {

    public BackButton backButton;
    public ToDiffButton left;
    private final ModeButton[] buttons = new ModeButton[3];

    public CustomModeScreen() {
        left = new ToDiffButton();
        left.setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.5f - left.sHeight / 2);
        for (int i = 0; i < 3; i++) {
            ModeButton b = new ModeButton(AbstractLabyrinth.Mode.values()[i + 1]);
            b.setPosition((575 + 705 * i) * scale - b.sWidth / 2, 232 * scale);
            buttons[i] = b;
        }
        backButton = new BackButton();
        setBg(FileHandler.getBg().get("BG_MODE"));
    }

    @Override
    public void update() {
        left.update();
        for (ModeButton b : buttons) {
            b.update();
        }
        backButton.update();
        if (!Labyrintale.fading && InputHandler.cancel) {
            backButton.onClick();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for (ModeButton b : buttons) {
            b.render(sb);
        }
        backButton.render(sb);
        left.render(sb);
    }

    @Override
    public void show() {
        backButton.over = false;
        backButton.show();
        for (ModeButton b : buttons) {
            b.over = false;
            b.show();
        }
        left.over = false;
    }
}
