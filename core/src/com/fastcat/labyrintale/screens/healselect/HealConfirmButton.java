package com.fastcat.labyrintale.screens.healselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class HealConfirmButton extends AbstractUI {

    private final HealSelectScreen sc;

    public HealConfirmButton(HealSelectScreen sc) {
        super(FileHandler.getUi().get("NEXT"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
        fontData = MAIN_MENU;
        text = "결정";
        this.sc = sc;
        showImg = false;
    }

    @Override
    protected void updateButton() {
        if (!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    protected void onClick() {
        if (sc.selected != null) {
            sc.expSelected(sc.selected.type);
        }
    }
}
