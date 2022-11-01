package com.fastcat.labyrintale.screens.difficultyscreen;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;

public class DifficultyButton extends AbstractUI {

    public AbstractLabyrinth.Difficulty diff;

    public DifficultyButton(AbstractLabyrinth.Difficulty diff) {
        super(FileHandler.getUi().get("DIFF_" + diff.toString()));
        this.diff = diff;
    }

    @Override
    protected void onClick() {
        charSelectScreen.diff = diff;
        for (int i = 0; i < charSelectScreen.chars.length; i++) {
            charSelectScreen.chars[i].removeChar();
        }
        charSelectScreen.nextButton.disable();
        charSelectScreen.backButton.onHide();
        charSelectScreen.nextButton.onHide();
        charSelectScreen.seedText.text = "";
        fadeOutAndChangeScreen(charSelectScreen, 1.0f);
    }
}
