package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.players.Wakgood;
import com.fastcat.labyrintale.screens.deckview.DeckViewScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class TutorialButton extends AbstractUI {

    public TutorialButton() {
        super(FileHandler.ui.get("MENU_SELECT"));
        setPosition(Gdx.graphics.getWidth() * 0.7f - sWidth / 2, Gdx.graphics.getHeight() * 0.4f);
        fontData = MAIN_MENU;
        text = "튜토리얼";
        showImg = false;
    }

    @Override
    protected void updateButton() {
        if(!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    protected void onClick() {
        AbstractPlayer t = new Wakgood();
        Labyrintale.addTempScreen(new DeckViewScreen(t, DeckViewScreen.ViewType.NORMAL));
    }
}
