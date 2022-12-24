package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.screens.tutorial.TutorialScreen;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.Labyrintale.diffScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class TutorialButton extends AbstractUI {

    public TutorialScreen.TutorialType type;

    public TutorialButton(Sprite sprite, float x, float y) {
        super(sprite, x, y);
    }

    public TutorialButton(Sprite sprite, TutorialScreen.TutorialType type) {
        super(sprite);
        this.type = type;
    }

    public void setType(TutorialScreen.TutorialType type) {
        this.type = type;
    }

    @Override
    protected void updateButton() {
        overable = type != null;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        overable = type != null;
        if (overable) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if(type != null) {
            Labyrintale.openTutorial(type);
            over = false;
        }
    }
}
