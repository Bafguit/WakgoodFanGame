package com.fastcat.labyrintale.screens.custom;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.UnlockHandler;

import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;

public class ModeButton extends AbstractUI {

    private final float aa = Color.LIGHT_GRAY.r;
    public AbstractLabyrinth.Mode mode;
    private final Sprite locked = FileHandler.getUi().get("DIFF_LOCKED");
    private float a = aa;

    public ModeButton(AbstractLabyrinth.Mode diff) {
        super(FileHandler.getUi().get("MODE_" + diff.toString()));
        this.mode = diff;
        isPixmap = true;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (clickable && over) {
                a += Labyrintale.tick * 4;
                if (a >= 1) a = 1;
            } else {
                a -= Labyrintale.tick * 4;
                if (a <= aa) a = aa;
            }
            sb.setColor(a, a, a, 1);
            sb.draw(clickable ? img : locked, x, y, sWidth, sHeight);
        }
    }

    @Override
    public void show() {
        a = aa;
    }

    @Override
    protected void onClick() {
        for (int i = 0; i < charSelectScreen.chars.length; i++) {
            charSelectScreen.chars[i].removeChar();
        }
        charSelectScreen.diff = AbstractLabyrinth.Difficulty.NORMAL;
        charSelectScreen.setMode(mode);
        charSelectScreen.seedText.text = "";
        Labyrintale.fadeOutAndChangeScreen(charSelectScreen, 0.75f);
    }
}
