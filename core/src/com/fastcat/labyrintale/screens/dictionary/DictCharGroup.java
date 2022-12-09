package com.fastcat.labyrintale.screens.dictionary;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.uis.CheckBox;
import com.fastcat.labyrintale.uis.StatIcon;
import com.fastcat.labyrintale.uis.control.InfoPanel;
import java.util.Objects;

public class DictCharGroup extends DictGroup {

    public AbstractUI parent = new AbstractUI.TempUI(FileHandler.getUi().get("BORDER"));
    public AbstractUI.TempUI heart;
    public FontHandler.FontData cName = CLOSE;
    public FontHandler.FontData iName = REST_DESC;
    public FontHandler.FontData iDesc = WAY;
    public DictCharData data;
    public DictItemIcon passive;
    public StatIcon[] stats = new StatIcon[6];
    public DictSkillIcon[] deck = new DictSkillIcon[3];
    public DictSkillIcon[] skills = new DictSkillIcon[7];
    public CheckBox up;
    public String name = "", desc = "";
    public float nx, hx, ny, nw, ix, iy, dy, dw, dh, px, py;

    public DictCharGroup(AbstractPlayer player) {
        parent.setPosition(0, 0);
        data = new DictCharData(player);
        nx = 975 * scale;
        hx = 1340 * scale;
        ny = 780 * scale;
        nw = 240 * scale;
        ix = 1524 * scale;
        iy = 496 * scale;
        dy = 464 * scale;
        dw = 600 * scale;
        dh = 164 * scale;
        px = 658 * scale;
        py = 340 * scale;
        heart = new AbstractUI.TempUI(FileHandler.getUi().get("BORDER_S"));
        heart.img = FileHandler.getUi().get("HEART");
        heart.setPosition(1273 * scale, ny - 30 * scale);
        passive = new DictItemIcon(this, data.player.passive);
        passive.setPosition(975 * scale, 606 * scale);
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            float cw = 1180 * scale, cp = 120 * scale, ch = (694 - 40 * i) * scale;
            for (int j = 0; j < 2; j++) {
                StatIcon c = new StatIcon(StatIcon.StatType.values()[cnt + 2]);
                c.fontData = STAT_RAW;
                c.entity = data.player;
                c.setPosition(cw + cp * j, ch);
                stats[cnt] = c;
                cnt++;
            }
        }
        for (int i = 0; i < 4; i++) {
            DictSkillIcon c = new DictSkillIcon(this, data.normal.get(i));
            c.setPosition((1524 + 162 * i) * scale, 694 * scale);
            skills[i] = c;
        }
        for (int i = 0; i < 3; i++) {
            DictSkillIcon c = new DictSkillIcon(this, data.player.deck.get(i));
            c.setPosition((975 + 162 * i) * scale, 450 * scale);
            deck[i] = c;

            DictSkillIcon c2 = new DictSkillIcon(this, data.normal.get(i));
            c2.setPosition((1524 + 162 * i) * scale, 538 * scale);
            skills[i + 4] = c2;
        }
        up = new CheckBox(false);
        up.setScale(0.8375f);
        up.setPosition(2043 * scale, 543 * scale);
    }

    public void update() {
        type = InfoPanel.InfoType.COLOR;
        skill = null;
        item = null;
        up.update();
        passive.update();
        data.player.infoSpine.skeleton.setPosition(parent.x + px, parent.y + py);
        for (int i = 0; i < 6; i++) {
            stats[i].update();
        }
        if (up.checked) {
            for (int i = 0; i < 3; i++) {
                DictSkillIcon c = deck[i];
                c.skill = data.uDeck.get(i);
                c.update();
            }
            for (int i = 0; i < 7; i++) {
                DictSkillIcon c = skills[i];
                c.skill = data.upgrade.get(i);
                c.update();
            }
        } else {
            for (int i = 0; i < 3; i++) {
                DictSkillIcon c = deck[i];
                c.skill = data.nDeck.get(i);
                c.update();
            }
            for (int i = 0; i < 7; i++) {
                DictSkillIcon c = skills[i];
                c.skill = data.normal.get(i);
                c.update();
            }
        }
        heart.update();
    }

    public void render(SpriteBatch sb) {
        up.render(sb);
        passive.render(sb);
        for (int i = 0; i < 6; i++) {
            stats[i].render(sb);
        }
        for (int i = 0; i < 3; i++) {
            deck[i].render(sb);
        }
        for (int i = 0; i < 7; i++) {
            skills[i].render(sb);
        }
        data.player.infoSpine.render(sb);
        heart.render(sb);
        FontHandler.renderCenter(
                sb, iName, "강화 보기", parent.x + up.x + up.sWidth / 2, parent.y + up.y + up.sHeight * 1.5f);
        FontHandler.renderLineLeft(sb, cName, data.player.name, parent.x + nx, parent.y + ny, nw, nw);
        FontHandler.renderLineLeft(
                sb, cName, Integer.toString(data.player.maxHealth), parent.x + hx, parent.y + ny, nw, nw);
        if (type == InfoPanel.InfoType.SKILL && skill != null) {
            FontHandler.renderLineLeft(sb, iName, skill.name, parent.x + ix, parent.y + iy, dw, dh);
            FontHandler.renderCardLeft(sb, skill, iDesc, skill.desc, parent.x + ix, parent.y + dy, dw, dh);
        } else if (type == InfoPanel.InfoType.ITEM && item != null) {
            FontHandler.renderLineLeft(sb, iName, item.name, parent.x + ix, parent.y + iy, dw, dh);
            FontHandler.renderColorLeft(sb, iDesc, item.desc, parent.x + ix, parent.y + dy, dw);
        }
    }

    public void setParent(AbstractUI ui) {
        parent = ui;
        heart.setParent(ui);
        passive.setParent(ui);
        for (int i = 0; i < 6; i++) {
            stats[i].setParent(ui);
        }
        for (int i = 0; i < 3; i++) {
            deck[i].setParent(ui);
        }
        for (int i = 0; i < 7; i++) {
            skills[i].setParent(ui);
        }
        up.setParent(ui);
    }

    public static class DictCharData {
        public AbstractPlayer player;
        public Array<AbstractSkill> nDeck;
        public Array<AbstractSkill> uDeck;
        public Array<AbstractSkill> normal;
        public Array<AbstractSkill> upgrade;

        public DictCharData(AbstractPlayer p) {
            player = p;
            player.infoSpine.skeleton.setPosition(10000, 10000);
            nDeck = player.deck;
            uDeck = new Array<>();
            for (AbstractSkill s : nDeck) {
                uDeck.add(Objects.requireNonNull(s.clone()).upgrade());
            }
            normal = new Array<>();
            for (AbstractSkill s : GroupHandler.SkillGroup.playerSort.get(p.playerClass)) {
                if (s.rarity == AbstractSkill.SkillRarity.NORMAL) {
                    normal.add(s.clone());
                }
            }
            upgrade = new Array<>();
            for (AbstractSkill s : normal) {
                upgrade.add(Objects.requireNonNull(s.clone()).upgrade());
            }
        }
    }
}
