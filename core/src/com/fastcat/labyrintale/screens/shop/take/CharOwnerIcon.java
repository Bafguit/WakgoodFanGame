package com.fastcat.labyrintale.screens.shop.take;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.rooms.other.ShopRoom;


public class CharOwnerIcon extends AbstractUI {

    public AbstractPlayer player;

    public CharOwnerIcon(AbstractPlayer re) {
        super(FileHandler.ui.get("BORDER_S"));
        player = re;
        clickable = false;
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            sb.draw(player.imgTiny, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void updateButton() {

    }
}
