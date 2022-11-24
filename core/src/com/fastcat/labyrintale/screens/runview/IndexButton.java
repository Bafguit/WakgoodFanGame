package com.fastcat.labyrintale.screens.runview;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.RunHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class IndexButton extends AbstractUI {

    public final RunViewScreen sc;
    public final boolean isLeft;

    public IndexButton(RunViewScreen sc, boolean isLeft) {
        super(FileHandler.getUi().get(isLeft ? "LEFT" : "RIGHT"));
        this.sc = sc;
        this.isLeft = isLeft;
        int size = RunHandler.runs.size;
        overable = size > 0 && isLeft ? sc.index > 0 : sc.index < size - 1;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if(!overable) sb.setColor(Color.DARK_GRAY);
            else if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            if (showImg) sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void updateButton() {
        int size = RunHandler.runs.size;
        overable = size > 0 && isLeft ? sc.index > 0 : sc.index < size - 1;
    }

    @Override
    protected void onClick() {
        sc.setIndex(!isLeft);
    }
}
