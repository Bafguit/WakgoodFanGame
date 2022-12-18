package com.fastcat.labyrintale.screens.initial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class InitialScreen extends AbstractScreen {

    private AbstractUI.TempUI back;
    private Sprite process;

    public InitialScreen() {
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        float p = Labyrintale.game.resourceHandler.getProgress();
        back.render(sb);
        sb.draw(
                process,
                back.x,
                back.y,
                0,
                0,
                back.sWidth,
                back.sHeight,
                p,
                1,
                0);
        FontHandler.renderCenter(sb, FontHandler.STAT_RAW, p * 100 + "%", back.x, 720 * scale, back.sWidth, back.sHeight);
    }

    @Override
    public void show() {

    }
}
