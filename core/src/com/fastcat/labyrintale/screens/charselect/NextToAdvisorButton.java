package com.fastcat.labyrintale.screens.charselect;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.loading.LoadingScreen;

public class NextToAdvisorButton extends AbstractUI {

    public NextToAdvisorButton() {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
        fontData = BUTTON;
        text = "출발";
        disable();
        isPixmap = true;
    }

    @Override
    protected void onClick() {
        Labyrintale.fadeOutAndChangeScreen(new LoadingScreen(true));
    }
}
