package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.ActionHandler.isRunning;
import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class SkillButtonPanel extends AbstractUI {

    public SkillButtonType type;
    public AbstractSkill skill;
    public boolean isUsed = false;
    public boolean available = true;

    public SkillButtonPanel(SkillButtonType type) {
        super(BORDER_M);
        this.type = type;
    }

    @Override
    protected void updateButton() {
        if(type == SkillButtonType.PLAYER) {
            isUsed = skill.used;
        }
        if(over) {
            if(skill != null) {
                cPanel.infoPanel.setInfo(skill);
            }
        }
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(isUsed || (type != SkillButtonType.VIEW && skill != null && !skill.canUse())) sb.setColor(Color.DARK_GRAY);
            else if (over) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            if(skill != null) sb.draw(skill.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if(type != SkillButtonType.VIEW && !battleScreen.isEnemyTurn && !isRunning) {
            if(!isUsed && skill.canUse()) {
                skill.useCard();
            }
        }
    }

    public void resetImg() {
        if(type == SkillButtonType.VIEW) skill = null;
    }

    public enum SkillButtonType {
        PLAYER, ADVISOR, VIEW
    }
}
