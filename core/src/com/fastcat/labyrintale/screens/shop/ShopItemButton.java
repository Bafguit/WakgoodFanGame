package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.rooms.other.ShopRoom;

public class ShopItemButton extends AbstractUI {

    private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
    private ShopRoom.SkillItem sItem;
    private ShopRoom.ItemItem iItem;
    public ShopRoom.ShopItem item;
    public Sprite itemImg;
    public ShopItemType type;

    public ShopItemButton(ShopRoom.ShopItem re) {
        super(FileHandler.getUi().get("BORDER_M"));
        setItem(re);
        fontData = FontHandler.CARD_BIG_DESC;
    }

    public void setItem(ShopRoom.ShopItem i) {
        item = i;
        itemImg = item.img;
        if (item instanceof ShopRoom.SkillItem) {
            type = ShopItemType.SKILL;
            sItem = (ShopRoom.SkillItem) item;
            subWay = SubText.SubWay.DOWN;
        } else if (item instanceof ShopRoom.ItemItem) {
            type = ShopItemType.ITEM;
            iItem = (ShopRoom.ItemItem) item;
        } else {
            type = ShopItemType.ROLL;
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && !item.isDone) {
            boolean can = item.canBuy();
            if (!can) sb.setColor(Color.DARK_GRAY);
            else if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(itemImg, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            FontHandler.renderCenter(
                    sb,
                    can ? FontHandler.SHOP_OK : FontHandler.SHOP_NO,
                    item.price + "G",
                    x,
                    y - sHeight * 0.2f,
                    sWidth,
                    sHeight * 0.2f);
            if (type == ShopItemType.SKILL) {
                sb.draw(img, x, y, sWidth, sHeight);
                if (!sItem.skill.passive) {
                    sb.draw(cost, x - sWidth * 0.2f, y + sWidth * 0.7f, sWidth * 0.5f, sWidth * 0.5f);
                    FontHandler.renderCenter(
                            sb,
                            fontData,
                            Integer.toString(sItem.skill.cost),
                            x - sWidth * 0.05f,
                            y + sWidth * 0.95f,
                            sWidth * 0.2f,
                            sWidth * 0.2f);
                }
            } else if (type == ShopItemType.ITEM) {
                sb.setColor(iItem.item.getRarityColor());
                sb.draw(img, x, y, sWidth, sHeight);
            }
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
    protected Array<SubText> getSubText() {
        if (type == ShopItemType.SKILL) return sItem.skill.key;
        else if (type == ShopItemType.ITEM) return iItem.item.key;
        else return null;
    }

    @Override
    protected void onOver() {}

    @Override
    protected void onClick() {
        if (!item.isDone && item.canBuy()) {
            item.takeItem();
        }
    }

    public enum ShopItemType {
        SKILL,
        ITEM,
        ROLL
    }
}
