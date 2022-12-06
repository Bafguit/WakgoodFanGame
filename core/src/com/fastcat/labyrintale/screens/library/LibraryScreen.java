package com.fastcat.labyrintale.screens.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.difficulty.BackButton;
import com.fastcat.labyrintale.screens.difficulty.DifficultyButton;
import com.fastcat.labyrintale.uis.GifBg;

public class LibraryScreen extends AbstractScreen {

    private final GifBg back;
    private LibraryButton[] buttons = new LibraryButton[3];
    public BackButton backButton;

    public LibraryScreen() {
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < 3; i++) {
            LibraryButton b = new LibraryButton(LibraryButton.Library.values()[i]);
            b.setPosition(w * 0.175f + w * 0.325f * i - b.sWidth / 2, h * 0.5f - b.sHeight / 2);
            buttons[i] = b;
        }
        backButton = new BackButton();
        back = new GifBg("MAIN_MENU");
    }

    @Override
    public void update() {
        for(LibraryButton b : buttons) {
            b.update();
        }
        backButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        back.render(sb);
        for(LibraryButton b : buttons) {
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
