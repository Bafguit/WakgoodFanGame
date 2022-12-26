package com.fastcat.labyrintale.screens.dictionary;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;

public class DictItemRarityButton extends AbstractUI {

    public DictItemGroup group;
    public DictScreen sc;
    public AbstractItem.ItemRarity rarity;
    public FontHandler.FontData iName = SETTING;
    public FontHandler.FontData iDesc = EVENT_CHOICE;
    public DictItemTabIcon[] items;
    public float nx, ny, nw, dy;

    public DictItemRarityButton(DictScreen sc, AbstractItem.ItemRarity rare) {
        super(FileHandler.getUi().get("BUTTON"));
        setScale(0.8f);
        fontData = TAB;
        rarity = rare;
        nx = 1380 * scale;
        ny = 930 * scale;
        nw = 577 * scale;
        dy = 893 * scale;
        group = new DictItemGroup();
        if (rarity == AbstractItem.ItemRarity.BRONZE) {
            text = "일반";
        } else if (rarity == AbstractItem.ItemRarity.SILVER) {
            text = "특별";
        } else if (rarity == AbstractItem.ItemRarity.GOLD) {
            text = "희귀";
        } else if (rarity == AbstractItem.ItemRarity.BOSS) {
            text = "보스";
        } else if (rarity == AbstractItem.ItemRarity.SHOP) {
            text = "상점";
        } else if (rarity == AbstractItem.ItemRarity.SPECIAL) {
            text = "이벤트";
        }
        Array<AbstractItem> temp = GroupHandler.ItemGroup.raritySort.get(rarity);
        items = new DictItemTabIcon[temp.size];
        int x = 0, y = 0;
        for (int i = 0; i < temp.size; i++) {
            DictItemTabIcon c = new DictItemTabIcon(group, temp.get(i));
            c.setPosition((580 + 200 * x) * scale, (800 - 186 * y) * scale);
            items[i] = c;
            x++;
            if (x == 4) {
                x = 0;
                y++;
            }
        }
        this.sc = sc;
        clickable = sc.iSelected != this;
        isPixmap = true;
    }

    @Override
    protected void updateButton() {
        clickable = sc.iSelected != this;
        if (!clickable) {
            group.update();
            for (DictItemTabIcon c : items) {
                c.update();
            }
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (!clickable || over) sb.setColor(Color.WHITE);
            else sb.setColor(Color.GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
        }

        if (!clickable) {
            for (DictItemTabIcon c : items) {
                c.render(sb);
            }
            if (group.item != null) {
                FontHandler.renderLineLeft(sb, iName, group.item.name, nx, ny, nw, nw);
                FontHandler.renderColorLeft(sb, iDesc, group.item.desc, nx, dy, nw);
            }
        }
    }

    @Override
    public void setParent(AbstractUI ui) {
        super.setParent(ui);
        for (DictItemTabIcon c : items) {
            c.setParent(ui);
        }
    }

    @Override
    protected void onClick() {
        sc.iSelected = this;
    }

    public static class DictItemGroup extends DictGroup {
        public void update() {
            item = null;
        }
    }
}
