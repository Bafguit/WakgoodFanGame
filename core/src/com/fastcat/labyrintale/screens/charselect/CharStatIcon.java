package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEntity.EntityStat;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedStat;
import com.fastcat.labyrintale.strings.KeyString;
import com.fastcat.labyrintale.uis.StatIcon;
import com.fastcat.labyrintale.uis.StatIcon.StatType;

import static com.fastcat.labyrintale.handlers.FontHandler.renderLineLeft;

public class CharStatIcon extends AbstractUI {

    private final Sprite icon;
    public AbstractEntity entity;
    public StatType type;
    public int amount;
    public boolean isUp = false;

    public CharStatIcon(StatType type) {
        super(FileHandler.getUi().get("BORDER_SS"));
        this.type = type;
        icon = FileHandler.getUi().get("STAT_" + type.toString());
        KeyString.KeyData data = StringHandler.keyString.get(type.toString().toLowerCase());
        subTexts = new Array<>();
        subTexts.add(new SubText(data.NAME, data.DESC));
        fontData = FontHandler.STAT;
        clickable = false;
    }

    public CharStatIcon(AbstractEntity e, StatType type) {
        super(FileHandler.getUi().get("BORDER_SS"));
        this.type = type;
        icon = FileHandler.getUi().get("STAT_" + type.toString());
        KeyString.KeyData data = StringHandler.keyString.get(type.toString().toLowerCase());
        subTexts = new Array<>();
        subTexts.add(new SubText(data.NAME, data.DESC));
        fontData = FontHandler.STAT;
        setEntity(e);
        isUp = true;
        clickable = e.isAlive() && AbstractLabyrinth.sp > 0;
    }

    public void setEntity(AbstractEntity entity) {
        this.entity = entity;
        if (entity != null) {
            if (isUp)
                clickable = entity.isAlive()
                        && AbstractLabyrinth.sp > 0
                        && amount < 80
                        && (type != StatType.CRITICAL || !entity.hasItem("TotoDeck"));
            if (type == StatType.ATTACK) {
                amount = entity.stat.attack;
                text = "공격력";
            } else if (type == StatType.SPELL) {
                amount = entity.stat.spell;
                text = "방어력";
            } else if (type == StatType.SPEED) {
                amount = entity.stat.capSpeed();
                text = "속도";
            } else {
                if (type == StatType.CRITICAL) {
                    amount = entity.hasItem("TotoDeck") ? 20 : EntityStat.cap(entity.stat.critical);
                    text = "치명타 확률";
                } else if (type == StatType.MULTIPLY) {
                    amount = entity.stat.multiply;
                    text = "치명타 피해";
                } else if (type == StatType.MOVERES) {
                    amount = EntityStat.cap(entity.stat.moveRes);
                    text = "이동 저항";
                } else if (type == StatType.DEBURES) {
                    amount = EntityStat.cap(entity.stat.debuRes);
                    text = "디버프 저항";
                } else if (type == StatType.NEUTRES) {
                    amount = EntityStat.neutCap(entity);
                    text = "죽음 저항";
                }
            }
        }
    }

    @Override
    protected void updateButton() {
        if (entity != null) {
            if (isUp)
                clickable = entity.isAlive()
                        && AbstractLabyrinth.sp > 0
                        && amount < 80
                        && (type != StatType.CRITICAL || !entity.hasItem("TotoDeck"));
            if (type == StatType.ATTACK) {
                amount = entity.stat.attack;
                text = "공격력";
            } else if (type == StatType.SPELL) {
                amount = entity.stat.spell;
                text = "방어력";
            } else if (type == StatType.SPEED) {
                amount = entity.stat.capSpeed();
                text = "속도";
            } else {
                if (type == StatType.CRITICAL) {
                    amount = entity.hasItem("TotoDeck") ? 20 : EntityStat.cap(entity.stat.critical);
                    text = "치명타 확률";
                } else if (type == StatType.MULTIPLY) {
                    amount = entity.stat.multiply;
                    text = "치명타 피해";
                } else if (type == StatType.MOVERES) {
                    amount = EntityStat.cap(entity.stat.moveRes);
                    text = "이동 저항";
                } else if (type == StatType.DEBURES) {
                    amount = EntityStat.cap(entity.stat.debuRes);
                    text = "디버프 저항";
                } else if (type == StatType.NEUTRES) {
                    amount = EntityStat.neutCap(entity);
                    text = "죽음 저항";
                }
            }
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return subTexts;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && entity != null) {
            if (isUp) {
                if (!clickable || (type != StatType.MULTIPLY && amount >= 80)) sb.setColor(Color.DARK_GRAY);
                else if (over) sb.setColor(Color.WHITE);
                else sb.setColor(Color.LIGHT_GRAY);
            } else sb.setColor(Color.WHITE);
            sb.draw(icon, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            if (entity != null) {
                String t = "";
                if (type == StatType.ATTACK || type == StatType.SPELL || type == StatType.SPEED) t += amount;
                else t = amount + "%";
                renderLineLeft(sb, fontData, text, x + sWidth * 1.1f, y + sHeight / 2, sWidth * 5, sHeight);
                renderLineLeft(sb, fontData, t, x + sWidth * 5.6f, y + sHeight / 2, sWidth * 3, sHeight);
            }
        }
    }
}
