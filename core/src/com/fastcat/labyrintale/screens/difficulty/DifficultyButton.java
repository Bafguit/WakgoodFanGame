package com.fastcat.labyrintale.screens.difficulty;

import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.handlers.UnlockHandler;

public class DifficultyButton extends AbstractUI {

    private Sprite locked = FileHandler.getUi().get("DIFF_LOCKED");
    public AbstractLabyrinth.Difficulty diff;
    private final float aa = Color.LIGHT_GRAY.r;
    private float a = aa;

    public DifficultyButton(AbstractLabyrinth.Difficulty diff) {
        super(FileHandler.getUi().get("DIFF_" + diff.toString()));
        this.diff = diff;
        clickable = UnlockHandler.achvs.get(UnlockHandler.Unlocks.DIFF).get(diff.toString());
        isPixmap = true;
    }

    @Override
    protected void updateButton() {
        clickable = UnlockHandler.achvs.get(UnlockHandler.Unlocks.DIFF).get(diff.toString());
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
        charSelectScreen.diff = diff;
        for (int i = 0; i < charSelectScreen.chars.length; i++) {
            charSelectScreen.chars[i].removeChar();
        }
        charSelectScreen.nextButton.disable();
        charSelectScreen.seedText.text = "";
        SoundHandler.playSfx("CHANGE");
        fadeOutAndChangeScreen(charSelectScreen, Labyrintale.FadeType.VERTICAL, 0.3f);
    }
}
