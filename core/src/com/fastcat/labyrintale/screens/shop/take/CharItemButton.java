package com.fastcat.labyrintale.screens.shop.take;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.shop.ShopItemCharIcon;

public class CharItemButton extends AbstractUI {

    public CharOwnerIcon icon;
    public ShopTakeScreen screen;
    public AbstractPlayer player;
    public AbstractItem item;
    public AbstractItem toItem;
    public int index;

    public CharItemButton(ShopTakeScreen screen, int index, AbstractPlayer player, AbstractItem s) {
        super(FileHandler.ui.get("BORDER_M"));
        toItem = s;
        item = player.item[index];
        this.player = player;
        this.index = index;
        this.screen = screen;
        icon = new CharOwnerIcon(player);
    }

    @Override
    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
        icon.setPosition(x + sWidth - icon.sWidth, y);
    }

    @Override
    protected void updateButton() {
        if(over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(item);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            if(showImg) sb.draw(item.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            icon.render(sb);
        }
    }

    @Override
    public void onClick() {
        player.gainItem(toItem, index);
        Labyrintale.removeTempScreen(screen);
    }
}
