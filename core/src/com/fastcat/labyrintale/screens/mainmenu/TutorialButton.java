package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.screens.loading.LoadingScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class TutorialButton extends AbstractUI {

    public TutorialButton() {
        super(FileHandler.ui.get("MENU_SELECT"));
        setPosition(Gdx.graphics.getWidth() * 0.7f - sWidth / 2, Gdx.graphics.getHeight() * 0.4f);
        fontData = MAIN_MENU;
        text = "불러오기";
        showImg = false;
    }

    @Override
    protected void updateButton() {
        clickable = SaveHandler.hasSave;
        if(!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    protected void onClick() {
        Labyrintale.fadeOutAndChangeScreen(new LoadingScreen(false));
    }
}
