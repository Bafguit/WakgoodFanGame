package com.fastcat.labyrintale.screens.mainmenu;

import static com.fastcat.labyrintale.handlers.FontHandler.FontData;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.loading.LoadingScreen;

public class LoadButton extends AbstractUI {

    private final Color fColor = Color.GRAY.cpy();
    private final float aa = fColor.r;
    private float a = fColor.r;

    public LoadButton() {
        super(FileHandler.getUi().get("NEXT"));
        setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.385f);
        fontData = new FontData(MEDIUM, 53, false, false);
        text = "불러오기";
        showImg = false;
        overable = SaveHandler.hasSave;
    }

    @Override
    protected void updateButton() {
        overable = SaveHandler.hasSave;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (overable && over) {
                a += Labyrintale.tick * 4;
                if (a >= 1) a = 1;
            } else {
                a -= Labyrintale.tick * 4;
                if (a <= aa) a = aa;
            }

            fColor.set(a, a, a, 1);
            fontData.color = fColor;

            renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
        }
    }

    @Override
    public void show() {
        a = aa;
    }

    @Override
    protected void onClick() {
        SoundHandler.playSfx("CHANGE_DOOR");
        Labyrintale.fadeOutAndChangeScreen(new LoadingScreen(false), Labyrintale.FadeType.VERTICAL);
    }

    @Override
    public void dispose() {
        fontData.dispose();
    }
}
