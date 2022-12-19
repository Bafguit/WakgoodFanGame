package com.fastcat.labyrintale.screens.shop.take;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class CharOwnerIcon extends AbstractUI {

    public AbstractPlayer.PlayerClass pClass;
    private Sprite spr;

    public CharOwnerIcon(AbstractPlayer re) {
        super(FileHandler.getUi().get("BORDER_S"));
        setPlayer(re.playerClass);
        clickable = false;
        overable = false;
    }

    public void setPlayer(AbstractPlayer.PlayerClass cls) {
        pClass = cls;
        spr = AbstractPlayer.getPlayerPortrait(pClass);
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && pClass != null) {
            sb.setColor(Color.WHITE);
            sb.draw(spr, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void updateButton() {}
}
