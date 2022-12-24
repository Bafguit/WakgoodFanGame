package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.Labyrintale.mapScreen;
import static com.fastcat.labyrintale.Labyrintale.playerInfoScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoScreen;

public class MapButton extends AbstractUI {

    public MapButton() {
        super(FileHandler.getUi().get("MAP"));
        subs.add(new SubText("지도", "지도를 펼칩니다."));
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(mapScreen.showing || over ? Color.WHITE : Color.LIGHT_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if (!mapScreen.showing) {
            MapScreen.view();
            if (playerInfoScreen.showing) {
                PlayerInfoScreen.remove();
            }
        } else {
            MapScreen.remove();
        }
    }
}
