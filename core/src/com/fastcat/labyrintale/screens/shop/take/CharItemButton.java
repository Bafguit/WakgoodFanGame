package com.fastcat.labyrintale.screens.shop.take;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class CharItemButton extends AbstractUI {

    public CharOwnerIcon icon;
    public AbstractPlayer player;
    public AbstractItem item;
    public AbstractItem toItem;
    public ShopTakeScreen sc;
    public int index;

    public CharItemButton(ShopTakeScreen screen, int index, AbstractPlayer player, AbstractItem s) {
        super(FileHandler.getUi().get("BORDER_M"));
        toItem = s;
        item = player.item[index];
        this.player = player;
        this.index = index;
        sc = screen;
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
        if (over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(item);
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return item != null ? item.key : null;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            if (showImg) sb.draw(item.img, x, y, sWidth, sHeight);
            sb.setColor(item.getRarityColor());
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            icon.render(sb);
        }
    }

    @Override
    public void onClick() {
        player.gainItem(toItem, index);
        if (sc.getItem != null) sc.itemSelected(toItem);
        sc.isRewardDone(true);
        Labyrintale.removeTempScreen(sc);
    }
}
