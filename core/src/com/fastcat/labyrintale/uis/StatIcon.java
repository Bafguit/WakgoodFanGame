package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEntity.EntityStat;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedStat;
import com.fastcat.labyrintale.strings.KeyString;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.FontHandler.renderLineLeft;

public class StatIcon extends AbstractUI {

    private final Sprite icon;
    public AbstractEntity entity;
    public StatType type;
    public float amount;
    public boolean isUp = false;

    private GetSelectedStat gets;

    public StatIcon(StatType type) {
        super(FileHandler.getUi().get("BORDER_SS"));
        this.type = type;
        icon = FileHandler.getUi().get("STAT_" + type.toString());
        KeyString.KeyData data = StringHandler.keyString.get(type.toString().toLowerCase());
        subTexts = new Array<>();
        subTexts.add(new SubText(data.NAME, data.DESC));
        fontData = FontHandler.SUB_NAME;
        clickable = false;
    }

    public StatIcon(AbstractEntity e, StatType type, GetSelectedStat get) {
        super(FileHandler.getUi().get("BORDER_SS"));
        this.type = type;
        icon = FileHandler.getUi().get("STAT_" + type.toString());
        KeyString.KeyData data = StringHandler.keyString.get(type.toString().toLowerCase());
        subTexts = new Array<>();
        subTexts.add(new SubText(data.NAME, data.DESC));
        fontData = FontHandler.SUB_NAME;
        entity = e;
        isUp = true;
        clickable = e.isAlive();
        gets = get;
    }

    @Override
    protected void updateButton() {
        if(type == StatType.ATTACK) amount = entity.stat.attack;
        else if(type == StatType.SPELL) amount = entity.stat.spell;
        else if(type == StatType.MULTIPLY) amount = entity.stat.multiply;
        else {
            if (type == StatType.CRITICAL) amount = EntityStat.cap(entity.stat.critical);
            else if (type == StatType.IGNBLOCK) amount = EntityStat.cap(entity.stat.ignBlock);
            else if (type == StatType.MOVERES) amount = EntityStat.cap(entity.stat.moveRes);
            else if (type == StatType.DEBURES) amount = EntityStat.cap(entity.stat.debuRes);
            else if (type == StatType.NEUTRES) amount = EntityStat.cap(entity.stat.neutRes);
            if(isUp) clickable = entity.isAlive() && amount < 80;
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return subTexts;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if(enabled) {
            if(isUp) {
                if(!clickable || (type != StatType.MULTIPLY && amount >= 80)) sb.setColor(Color.DARK_GRAY);
                else if(over) sb.setColor(Color.WHITE);
                else sb.setColor(Color.LIGHT_GRAY);
            }
            else sb.setColor(Color.WHITE);
            sb.draw(icon, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            if(entity != null) {
                String t = "";
                if(type == StatType.ATTACK || type == StatType.SPELL) t += amount;
                else {
                    if (type == StatType.MULTIPLY) t += "+" + amount + "%";
                    else t = EntityStat.cap(amount) + "%";
                }
                renderLineLeft(sb, fontData, t, x + sWidth, y + sHeight / 2, sWidth * 3, sHeight);
            }
        }
    }

    @Override
    public void onClick() {
        if (type == StatType.MULTIPLY) entity.stat.multiply += 5;
        else if(amount < 80) {
            if (type == StatType.CRITICAL) entity.stat.critical += 5;
            else if (type == StatType.IGNBLOCK) entity.stat.ignBlock += 5;
            else if (type == StatType.MOVERES) entity.stat.moveRes += 5;
            else if (type == StatType.DEBURES) entity.stat.debuRes += 5;
            else if (type == StatType.NEUTRES) entity.stat.neutRes += 5;
        }
        gets.statSelected(entity, type);
    }

    public enum StatType {
        ATTACK, SPELL, CRITICAL, MULTIPLY, IGNBLOCK, MOVERES, DEBURES, NEUTRES
    }
}
