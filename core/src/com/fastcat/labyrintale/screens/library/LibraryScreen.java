package com.fastcat.labyrintale.screens.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.screens.difficulty.BackButton;

public class LibraryScreen extends AbstractScreen {

    private final LibraryButton[] buttons = new LibraryButton[3];
    public BackButton backButton;

    public LibraryScreen() {
        setBg(FileHandler.getBg().get("BG_LIB"));
        float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;
        for (int i = 0; i < 3; i++) {
            LibraryButton b = new LibraryButton(LibraryButton.Library.values()[i]);
            b.setPosition(w * 0.25f + w * 0.25f * i - b.sWidth / 2, h * 0.5f - b.sHeight / 2);
            buttons[i] = b;
        }
        backButton = new BackButton();
    }

    @Override
    public void update() {
        for (LibraryButton b : buttons) {
            b.update();
        }
        backButton.update();
        if (!Labyrintale.fading && InputHandler.cancel) {
            backButton.onClick();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for (LibraryButton b : buttons) {
            b.render(sb);
        }
        backButton.render(sb);
    }

    @Override
    public void show() {
        backButton.over = false;
        for (LibraryButton b : buttons) {
            b.over = false;
        }
    }
}
