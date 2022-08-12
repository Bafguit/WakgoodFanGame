package com.fastcat.labyrintale.screens.skillselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.screens.skillselect.SkillSelectScreen.SkillSelectGroup;

public class SkillSelectButton extends AbstractUI {

    public final SkillSelectGroup group;
    public AbstractSkill skill;
    public boolean isTo;

    public SkillSelectButton(SkillSelectGroup g, AbstractSkill s) {
        this(g, s, false);
    }

    public SkillSelectButton(SkillSelectGroup g, AbstractSkill s, boolean isTo) {
        super(FileHandler.getUi().get("BORDER_M"));
        group = g;
        skill = s;
        this.isTo = isTo;
        clickable = !isTo;
    }

    @Override
    protected void updateButton() {
        if (over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(skill);
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (over || SkillSelectGroup.screen.selected == this || (isTo && group.bg.over)) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            if (showImg) sb.draw(skill.img, x, y, sWidth, sHeight);
            if (isTo)
                FontHandler.renderCenter(sb, FontHandler.BORDER, "↕", x + sWidth * 0.5f, y + sHeight + Gdx.graphics.getHeight() * 0.03f);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return skill != null ? skill.key : null;
    }

    @Override
    protected void onClick() {
        if (!isTo) {
            group.selected = skill;
            SkillSelectGroup.screen.selected = this;
        }
    }
}
