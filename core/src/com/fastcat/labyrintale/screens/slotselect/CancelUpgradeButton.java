package com.fastcat.labyrintale.screens.slotselect;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class CancelUpgradeButton extends AbstractUI {

    public SlotSelectScreen sc;

    public CancelUpgradeButton(SlotSelectScreen sc) {
        super(FileHandler.getUi().get("BUTTON"));
        this.sc = sc;
        setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.4f);
        fontData = BUTTON;
        text = "취소";
    }

    @Override
    protected void onClick() {
        sc.rewardDone.isRewardDone(false);
        Labyrintale.removeTempScreen(sc);
    }
}
