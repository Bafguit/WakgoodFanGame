package com.fastcat.labyrintale.screens.expselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class ExpConfirmButton extends AbstractUI {

    private final ExpSelectScreen sc;

    public ExpConfirmButton(ExpSelectScreen sc) {
        super(FileHandler.ui.get("NEXT"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
        fontData = MAIN_MENU;
        text = "결정";
        this.sc = sc;
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
        if(sc.selected != null) {
            sc.expSelected(sc.selected.type);
        }
    }
}
