package com.fastcat.labyrintale.screens.charselect;

import static com.fastcat.labyrintale.handlers.FontHandler.SEED;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class SeedText extends AbstractUI {

    private float counter;
    private boolean up;

    public SeedText() {
        super(
                FileHandler.getUi().get("MENU_SELECT"),
                Gdx.graphics.getWidth() * 0.05f,
                72 * InputHandler.scale,
                200,
                60);
        fontData = SEED;
        text = "시드 설정";
        counter = 0;
        up = true;
    }

    @Override
    protected void updateButton() {
        if (InputHandler.textInputMode) {
            String t = InputHandler.getTypedText(text);
            text = t.substring(0, Math.min(t.length(), 8));
            float d = Labyrintale.tick;
            if (up) {
                counter += d;
                if (counter >= 0.5f) {
                    counter = 0.5f;
                    up = false;
                }
            } else {
                if (counter < d) {
                    counter = 0;
                    up = true;
                } else counter -= d;
            }
        } else counter = 0;

        AbstractLabyrinth.seed = text;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            String s;
            if (text.length() == 0) {
                if (InputHandler.textInputMode) {
                    s = up ? "|" : "";
                } else s = "시드 설정";
            } else s = text;
            renderCenter(sb, fontData, s, x, y + sHeight * 0.5f, sWidth, sHeight);
        }
    }

    @Override
    public void onClick() {
        if (!InputHandler.textInputMode) {
            text = "";
            InputHandler.setTextInputMode(true);
        }
    }
}
