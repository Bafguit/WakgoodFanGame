package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.Labyrintale.mapScreen;
import static com.fastcat.labyrintale.Labyrintale.playerInfoScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_DESC;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoScreen;

public class PlayerInfoButton extends AbstractUI {

    public PlayerInfoButton() {
        super(FileHandler.getUi().get("STAT_PLUS"));
        fontData = CARD_BIG_DESC;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(playerInfoScreen.showing || over ? Color.WHITE : Color.LIGHT_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
            /*if (AbstractLabyrinth.sp > 0) {
            	sb.end();
            	shr.setColor(Color.ORANGE);
            	shr.begin(ShapeRenderer.ShapeType.Filled);
            	shr.circle(x + sWidth * 0.9f, y + sHeight * 0.9f, sHeight * 0.3f);
            	shr.end();
            	sb.begin();
            	renderCenter(
            		sb,
            		fontData,
            		Integer.toString(AbstractLabyrinth.sp),
            		x + sWidth * 0.7f,
            		y + sHeight * 0.9f,
            		sWidth * 0.4f,
            		sHeight * 0.4f);
            }*/
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
