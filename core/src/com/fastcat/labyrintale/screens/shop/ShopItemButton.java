package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.rooms.other.ShopRoom;


public class ShopItemButton extends AbstractUI {

    public ShopRoom.ShopItem item;
    public Sprite itemImg;

    public ShopItemButton(ShopRoom.ShopItem re) {
        super(FileHandler.getUi().get("BORDER_M"));
        setItem(re);
    }

    public void setItem(ShopRoom.ShopItem i) {
        item = i;
        itemImg = item.img;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && !item.isDone) {
            boolean can = item.canBuy();
            if (!can) sb.setColor(Color.DARK_GRAY);
            else if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(itemImg, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            FontHandler.renderCenter(sb, can ? FontHandler.SHOP_OK : FontHandler.SHOP_NO, item.price + "G", x, y - sHeight * 0.2f, sWidth, sHeight * 0.2f);
        }
    }

    @Override
    protected void updateButton() {
        clickable = item.canBuy();
        if (!item.isDone && over) {
            item.setPanel();
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        if (!item.isDone && item.canBuy()) {
            item.takeItem();
        }
    }
}
