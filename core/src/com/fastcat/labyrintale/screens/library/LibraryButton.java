package com.fastcat.labyrintale.screens.library;

import static com.badlogic.gdx.graphics.Color.LIGHT_GRAY;
import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.runview.RunViewScreen;

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
        if (lib == Library.RUNS) {
            SoundHandler.playSfx("CHANGE");
            Labyrintale.fadeOutAndChangeScreen(new RunViewScreen(), Labyrintale.FadeType.VERTICAL, 0.3f);
        } else if (lib == Library.DICT) Labyrintale.addTempScreen(Labyrintale.dictionary);
        else if (lib == Library.ACHVS) Labyrintale.addTempScreen(Labyrintale.achievement);
    }

    public enum Library {
        RUNS,
        DICT,
        ACHVS
    }
}
