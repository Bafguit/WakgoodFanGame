package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.rooms.other.ShopRoom;


public class ShopItemButton extends AbstractUI {

    private final Sprite border = FileHandler.ui.get("BORDER_M");
    public ShopRoom.ShopItem item;

    public ShopItemButton(ShopRoom.ShopItem re) {
        super(re.img);
        item = re;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(item.isDone) sb.setColor(Color.DARK_GRAY);
            else if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        clickable = !item.isDone;
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        if(!item.isDone && AbstractLabyrinth.gold >= item.price) {
            item.takeItem();
        }
    }
}
