package com.fastcat.labyrintale.screens.library;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.UnlockHandler;
import com.fastcat.labyrintale.screens.achieve.AchieveScreen;
import com.fastcat.labyrintale.screens.dictionary.DictScreen;
import com.fastcat.labyrintale.screens.runview.RunViewScreen;

import static com.badlogic.gdx.graphics.Color.LIGHT_GRAY;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;

public class LibraryButton extends AbstractUI {

    public Library lib;

    public LibraryButton(Library lib) {
        super(FileHandler.getUi().get("LIB_" + lib.toString()));
        this.lib = lib;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (over) sb.setColor(WHITE);
            else sb.setColor(LIGHT_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if(lib == Library.RUNS) Labyrintale.fadeOutAndChangeScreen(new RunViewScreen());
        else if(lib == Library.DICT) Labyrintale.addTempScreen(new DictScreen());
        else if(lib == Library.ACHVS) Labyrintale.addTempScreen(new AchieveScreen());
    }

    public enum Library {
        RUNS, DICT, ACHVS
    }
}
