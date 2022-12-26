package com.fastcat.labyrintale.screens.library;

import static com.badlogic.gdx.graphics.Color.LIGHT_GRAY;
import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.achieve.AchieveScreen;
import com.fastcat.labyrintale.screens.dictionary.DictScreen;
import com.fastcat.labyrintale.screens.runview.RunViewScreen;

public class LibraryButton extends AbstractUI {

    private final float aa = Color.LIGHT_GRAY.r;
    public AbstractLabyrinth.Difficulty diff;
    private float a = aa;

    public Library lib;

    public LibraryButton(Library lib) {
        super(FileHandler.getUi().get("LIB_" + lib.toString()));
        this.lib = lib;
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
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if (lib == Library.RUNS) {
            SoundHandler.playSfx("CHANGE");
            Labyrintale.fadeOutAndChangeScreen(new RunViewScreen(), Labyrintale.FadeType.VERTICAL, 0.5f);
        } else if (lib == Library.DICT) {
            SoundHandler.playSfx("CHANGE");
            Labyrintale.fadeOutAndChangeScreen(new DictScreen(), Labyrintale.FadeType.VERTICAL, 0.5f);
        } else if (lib == Library.ACHVS) {
            SoundHandler.playSfx("CHANGE");
            Labyrintale.fadeOutAndChangeScreen(new AchieveScreen(), Labyrintale.FadeType.VERTICAL, 0.5f);
        }
    }

    public enum Library {
        RUNS,
        DICT,
        ACHVS
    }
}
