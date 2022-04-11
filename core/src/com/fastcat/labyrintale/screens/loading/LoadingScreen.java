package com.fastcat.labyrintale.screens.loading;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.map.MapScreen;

public class LoadingScreen extends AbstractScreen {

    public LoadingText text;
    public boolean isNew = false;

    public LoadingScreen() {
        text = new LoadingText();
        setBg(FileHandler.ui.get("FADE"));
    }

    public LoadingScreen(boolean b) {
        this();
        isNew = b;
    }

    @Override
    public void update() {
        if(isNew && !Labyrintale.fading) {
            Labyrintale.advisorSelectScreen.backButton.onHide();
            Labyrintale.advisorSelectScreen.nextButton.onHide();
            Labyrintale.labyrinth = new AbstractLabyrinth();
            Labyrintale.mapScreen = new MapScreen();
            Labyrintale.advisorSelectScreen.advisor.removeChar();
            for(int i = 0; i < Labyrintale.charSelectScreen.chars.length; i++) {
                Labyrintale.charSelectScreen.chars[i].removeChar();
            }
            Labyrintale.charSelectScreen.nextButton.disable();
            Labyrintale.charSelectScreen.backButton.onHide();
            Labyrintale.charSelectScreen.nextButton.onHide();
            Labyrintale.fadeOutAndChangeScreen(Labyrintale.mapScreen);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        text.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        text.dispose();
    }
}
