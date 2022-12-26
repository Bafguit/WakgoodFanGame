package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.Labyrintale.mapScreen;
import static com.fastcat.labyrintale.Labyrintale.playerInfoScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_DESC;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoScreen;

public class PlayerInfoButton extends AbstractUI {

    public PlayerInfoButton() {
        super(FileHandler.getUi().get("STAT_PLUS"));
        subTexts = new Array<>();
        subTexts.add(new SubText("플레이어 정보", "모든 플레이어의 정보를 열람합니다."));
        isPixmap = true;
    }

    protected Array<SubText> getSubText() {
        return subTexts;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(playerInfoScreen.showing || over ? Color.WHITE : Color.LIGHT_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if (!playerInfoScreen.showing) {
            PlayerInfoScreen.view();
            if (mapScreen.showing) {
                MapScreen.remove();
            }
        } else {
            PlayerInfoScreen.remove();
        }
    }
}
