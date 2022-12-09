package com.fastcat.labyrintale.screens.runview;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class RunViewPlayerIcon extends AbstractUI {

    public AbstractPlayer p;

    public RunViewPlayerIcon() {
        super(FileHandler.getUi().get("BORDER_V2"));
    }

    public void setPlayer(AbstractPlayer p) {
        this.p = p;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && p != null) {
            sb.setColor(p.isAlive() ? p.animColor : Color.DARK_GRAY);
            sb.draw(p.imgPanel, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void updateButton() {}
}
