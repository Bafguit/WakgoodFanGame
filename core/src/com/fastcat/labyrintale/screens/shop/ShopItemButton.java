package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.rooms.other.ShopRoom;
import com.fastcat.labyrintale.screens.shop.take.CharOwnerIcon;

public class ShopItemButton extends AbstractUI {

    private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
    private final TempUI shadow = new TempUI(FileHandler.getUi().get("SHADOW_ITEM"));
    private final Sprite roll = FileHandler.getUi().get("SHADOW_ROLL");
    public ShopRoom.ShopItem item;
    public CharOwnerIcon ownerIcon;
    public TempUI gold;
    public Sprite itemImg;
    public ShopItemType type;
    private ShopRoom.SkillItem sItem;
    private ShopRoom.ItemItem iItem;

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
            ownerIcon = new CharOwnerIcon((AbstractPlayer) sItem.skill.owner);
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
            sb.draw(
                    type == ShopItemType.ROLL ? roll : shadow.img,
                    x + sWidth / 2 - shadow.sWidth / 2,
                    y + sHeight / 2 - shadow.sHeight / 2,
                    shadow.sWidth,
                    shadow.sHeight);
            if (!can) sb.setColor(Color.DARK_GRAY);
            else if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(itemImg, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            if (gold != null) gold.render(sb);
            String t = item.price + "G";
            if (!can) t = "&r<" + t + ">";
            FontHandler.renderColorLeft(sb, FontHandler.SHOP_OK, t, x + sWidth * 0.415f, y - sHeight * 0.07f, sWidth);
            if (type == ShopItemType.SKILL) {
                sb.draw(img, x, y, sWidth, sHeight);
                ownerIcon.setPosition(x + sWidth - ownerIcon.sWidth, y);
                ownerIcon.render(sb);
                if (!sItem.skill.passive) {
                    sb.draw(cost, x - sWidth * 0.05f, y + sWidth * 0.65f, sWidth * 0.4f, sWidth * 0.4f);
                    FontHandler.renderCenter(
                            sb,
                            fontData,
                            Integer.toString(sItem.skill.cost),
                            x + sWidth * 0.05f,
                            y + sWidth * 0.85f,
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
