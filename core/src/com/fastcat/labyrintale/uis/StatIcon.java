package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.KeyString;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.FontHandler.renderLineLeft;

public class StatIcon extends AbstractUI {

    private final Sprite icon;
    public AbstractEntity entity;
    public StatType type;

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

    @Override
    protected Array<SubText> getSubText() {
        return subTexts;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            sb.draw(icon, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            if(entity != null) {
                String t = "";
                if(type == StatType.ATTACK) t += entity.stat.attack;
                else if(type == StatType.SPELL) t += entity.stat.spell;
                else if(type == StatType.CRITICAL) t = entity.stat.critical + "%";
                else if(type == StatType.MULTIPLY) t = "+" + entity.stat.multiply + "%";
                else if(type == StatType.IGNBLOCK) t = entity.stat.ignBlock + "%";
                else if(type == StatType.MOVERES) t = entity.stat.moveRes + "%";
                else if(type == StatType.DEBURES) t = entity.stat.debuRes + "%";
                else if(type == StatType.NEUTRES) t = entity.stat.neutRes + "%";
                renderLineLeft(sb, fontData, t, x + sWidth, y + sHeight / 2, sWidth * 3, sHeight);
            }
        }
    }

    public enum StatType {
        ATTACK, SPELL, CRITICAL, MULTIPLY, IGNBLOCK, MOVERES, DEBURES, NEUTRES
    }
}
