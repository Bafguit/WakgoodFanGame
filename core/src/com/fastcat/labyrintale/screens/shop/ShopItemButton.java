package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.rooms.other.Shop;

import static com.fastcat.labyrintale.handlers.FileHandler.BORDER;

public class ShopItemButton extends AbstractUI {

    private Sprite border = BORDER;
    public Shop.ShopItem item;

    public ShopItemButton(Shop.ShopItem re) {
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

    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        if(AbstractLabyrinth.gold >= item.price && !item.isDone) {
            item.takeItem();
            AbstractLabyrinth.gold -= item.price;
        }
    }
}
