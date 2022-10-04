package com.fastcat.labyrintale.screens.playerinfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;
import com.fastcat.labyrintale.screens.statselect.StatSelectScreen;

import static com.fastcat.labyrintale.Labyrintale.playerInfoScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class UpgradeStatButton extends AbstractUI {

    private static final ShapeRenderer shr = new ShapeRenderer();

    public UpgradeStatButton() {
        super(FileHandler.getUi().get("STAT_PLUS"));
        setPosition(Gdx.graphics.getWidth() * 0.97f - sWidth, Gdx.graphics.getHeight() * 0.87f);
        fontData = SUB_NAME;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if(enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.end();
            shr.setColor(Color.ORANGE);
            shr.begin(ShapeRenderer.ShapeType.Filled);
            shr.circle(x + sWidth * 0.875f, y + sHeight * 0.875f, sHeight * 0.25f);
            shr.end();
            sb.begin();
            renderCenter(sb, fontData, Integer.toString(AbstractLabyrinth.sp), x + sWidth * 0.625f, y + sHeight * 0.875f, sWidth * 0.5f, sHeight * 0.5f);
        }
    }

    @Override
    protected void onClick() {
        playerInfoScreen.statScreen = new StatSelectScreen();
        Labyrintale.addTempScreen(playerInfoScreen.statScreen);
    }
}
