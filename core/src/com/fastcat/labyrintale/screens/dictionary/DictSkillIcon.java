package com.fastcat.labyrintale.screens.dictionary;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.UnlockHandler;
import java.util.HashMap;

public class DictSkillIcon extends AbstractUI {

    private final HashMap<String, Boolean> map = UnlockHandler.achvs.get(UnlockHandler.Unlocks.SKILL);
    private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
    private final Sprite locked = FileHandler.getUi().get("UNKNOWN");
    public DictCharGroup group;
    public AbstractSkill skill;

    public DictSkillIcon(DictCharGroup group, AbstractSkill skill) {
        super(FileHandler.getUi().get("BORDER"));
        this.group = group;
        fontData = FontHandler.BIG_DESC;
        clickable = false;
        overable = false;
        this.skill = skill;
        if(map.get(skill.id) == null) map.put(skill.id, false);
    }

    @Override
    protected void updateButton() {
        if (skill != null) {
            overable = skill.rarity == AbstractSkill.SkillRarity.NORMAL ? map.get(skill.id) : true;
            if (overable && over) {
                group.setSkill(skill);
            }
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && skill != null) {
            sb.setColor(Color.WHITE);
            if (overable) {
                sb.draw(skill.img, x, y, sWidth, sHeight);
                sb.draw(img, x, y, sWidth, sHeight);
                if (!skill.passive) {
                    sb.draw(cost, x - sWidth * 0.05f, y + sWidth * 0.65f, sWidth * 0.4f, sWidth * 0.4f);
                    String t = Integer.toString(skill.cost);
                    FontHandler.renderColorCenter(
                            sb, fontData, t, x + sWidth * 0.05f, y + sWidth * 0.85f, sWidth * 0.2f);
                }
                if (skill.upgradeCount > 0) {
                    FontHandler.renderLineRight(
                            sb,
                            fontData,
                            "+" + skill.upgradeCount,
                            x + sWidth * 0.75f,
                            y + sWidth * 0.2f,
                            sWidth * 0.2f,
                            sWidth * 0.2f);
                }
            } else {
                sb.draw(locked, x, y, sWidth, sHeight);
                sb.draw(img, x, y, sWidth, sHeight);
            }
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return skill != null && overable ? skill.key : null;
    }
}
