package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.map.MapScreen;

public class TurnSkipButton extends AbstractUI {

    public TurnSkipButton() {
        super(FileHandler.getUi().get("RIGHT"));
        clickable = false;
        subs.add(new SubText("턴 강제 넘기기", "턴이 자동으로 넘어가지 않을 때만 사용해 주세요."));
        setScale(2);
    }

    @Override
    protected Array<SubText> getSubText() {
        return subs;
    }

    @Override
    protected void updateButton() {
        if(Labyrintale.battleScreen != null) {
            clickable = !Labyrintale.battleScreen.isEnemyTurn && !Labyrintale.battleScreen.isSelecting;
        } else {
            clickable = false;
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(over ? Color.WHITE : Color.LIGHT_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        Labyrintale.battleScreen.endPlayerTurn();
    }
}
