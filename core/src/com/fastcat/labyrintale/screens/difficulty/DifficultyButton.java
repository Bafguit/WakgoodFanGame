package com.fastcat.labyrintale.screens.difficulty;

import static com.badlogic.gdx.graphics.Color.LIGHT_GRAY;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.UnlockHandler;

public class DifficultyButton extends AbstractUI {

    private Sprite locked = FileHandler.getUi().get("DIFF_LOCKED");
    public AbstractLabyrinth.Difficulty diff;

    public DifficultyButton(AbstractLabyrinth.Difficulty diff) {
        super(FileHandler.getUi().get("DIFF_" + diff.toString()));
        this.diff = diff;
        clickable = UnlockHandler.achvs.get(UnlockHandler.Unlocks.DIFF).get(diff.toString());
    }

    @Override
    protected void updateButton() {
        clickable = UnlockHandler.achvs.get(UnlockHandler.Unlocks.DIFF).get(diff.toString());
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (clickable && over) sb.setColor(WHITE);
            else sb.setColor(LIGHT_GRAY);
            sb.draw(clickable ? img : locked, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        charSelectScreen.diff = diff;
        for (int i = 0; i < charSelectScreen.chars.length; i++) {
            charSelectScreen.chars[i].removeChar();
        }
        charSelectScreen.nextButton.disable();
        charSelectScreen.seedText.text = "";
        fadeOutAndChangeScreen(charSelectScreen);
    }
}
