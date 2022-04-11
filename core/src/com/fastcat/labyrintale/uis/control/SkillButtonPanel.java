package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.*;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.screens.battle.SkillButton;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.ActionHandler.bot;
import static com.fastcat.labyrintale.handlers.ActionHandler.isRunning;
import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class SkillButtonPanel extends AbstractUI {

    public SkillButtonType type;
    public AbstractSkill skill;
    public boolean isUsed = false;

    public SkillButtonPanel(SkillButtonType type) {
        super(FileHandler.ui.get("BORDER_M"));
        this.type = type;
        fontData = FontHandler.COOLDOWN;
    }

    @Override
    protected void updateButton() {
        if(type == SkillButtonType.PLAYER) {
            isUsed = !skill.canUse();
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
            sb.setColor(Color.WHITE);
            if(skill.cooldown > 0) FontHandler.renderCenter(sb, fontData, Integer.toString(skill.cooldown), x, y + sHeight / 2, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if(type != SkillButtonType.VIEW && !battleScreen.isEnemyTurn && !isRunning) {
            if(!isUsed && skill.canUse()) {
                skill.useCard();

                if(AbstractLabyrinth.energy == 0 && noMoreSkill()) {
                    bot(new EndPlayerTurnAction());
                }
            }
        }
    }

    public static boolean noMoreSkill() {
        if(AbstractLabyrinth.advisor.skill.canUse()) return false;
        for(AbstractPlayer p : players) {
            for(AbstractSkill s : p.hand) {
                if(s.canUse()) return false;
            }
        }
        return true;
    }

    public void resetImg() {
        if(type == SkillButtonType.VIEW) skill = null;
    }

    public enum SkillButtonType {
        PLAYER, ADVISOR, PASSIVE, VIEW
    }
}
