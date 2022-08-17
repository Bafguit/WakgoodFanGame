package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;

public class SkillButton extends AbstractUI {

    private final Sprite border = FileHandler.getUi().get("BORDER");
    public AbstractSkill skill;
    public boolean isInfo = false;
    public boolean isOnLock = false;
    public boolean isCS = true;
    public boolean isSkill = true;
    public boolean isSelected = false;
    public boolean canClick = true;
    public boolean advisor = false;

    public SkillButton() {
        super(FileHandler.getUi().get("BORDER"));
        clickable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (isSelected) sb.setColor(Color.DARK_GRAY);
            else if (!over && !isInfo) sb.setColor(Color.LIGHT_GRAY);

            if (skill != null) {
                if (showImg) sb.draw(skill.img, x, y, sWidth, sHeight);
            }
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if (skill != null) {
            if (skill.owner != null && !skill.owner.isAlive()) {
                skill = null;
            } else if (overable && over) {
                cPanel.infoPanel.setInfo(skill);
                battleScreen.looking = getTargets(skill);
            }
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return skill != null ? skill.key : null;
    }

    public void removeChar() {
        skill = null;
        showImg = false;
        isOnLock = false;
        isSelected = false;
    }
}
