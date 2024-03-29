package com.fastcat.labyrintale.screens.result;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class ResultAdvisor extends AbstractUI {

    public TempUI paper = new TempUI(FileHandler.getUi().get("BORDER_ADV"));
    public AbstractItem item;

    public ResultAdvisor() {
        super(FileHandler.getUi().get("BORDER_M"));
        clickable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(Color.WHITE);
            sb.draw(
                    paper.img,
                    x + sWidth / 2 - paper.sWidth / 2,
                    y - 68 * InputHandler.scale,
                    paper.sWidth,
                    paper.sHeight);
            if (item != null) {
                sb.draw(item.img, x, y, sWidth, sHeight);
                FontHandler.renderCenter(sb, FontHandler.GOMEM, item.name, x, y - 22 * InputHandler.scale, sWidth, sHeight);
            }
        }
    }
}
