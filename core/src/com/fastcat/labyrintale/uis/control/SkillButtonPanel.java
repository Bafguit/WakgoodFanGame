package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;

public class SkillButtonPanel extends AbstractUI {

    public SkillButtonType type;
    public AbstractSkill skill;
    public boolean isUsed = false;

    public SkillButtonPanel(SkillButtonType type) {
        super(type == SkillButtonType.MOVE ? FileHandler.getUi().get("BORDER") : FileHandler.getUi().get("BORDER_M"));
        this.type = type;
        fontData = FontHandler.COOLDOWN;
    }

    @Override
    protected void updateButton() {
        clickable = !skill.passive && skill.canUse() && type != SkillButtonType.VIEW && !battleScreen.isSelecting && !ActionHandler.isRunning() && Labyrintale.getCurScreen() == battleScreen;
        if (type == SkillButtonType.PLAYER || type == SkillButtonType.MOVE) {
            isUsed = !skill.canUse();
        }
        if (over) {
            if (skill != null) {
                cPanel.infoPanel.setInfo(skill);
            }
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (isUsed || (type != SkillButtonType.VIEW && skill != null && !skill.canUse()) || (battleScreen.isSelecting && cPanel.battlePanel.selected != skill)) {
                sb.setColor(Color.DARK_GRAY);
            } else if (over && clickable) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            if (skill != null) sb.draw(skill.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            if (skill.cooldown > 0 && !skill.usedOnce && !skill.usedOnly)
                FontHandler.renderCenter(sb, fontData, Integer.toString(skill.cooldown), x, y + sHeight / 2, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if (!isUsed && skill.canUse()) {
            skill.useCard();
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return skill != null ? skill.key : null;
    }

    public void resetImg() {
        if (type == SkillButtonType.VIEW) skill = null;
    }

    public enum SkillButtonType {
        PLAYER, MOVE, ADVISOR, PASSIVE, VIEW
    }
}
