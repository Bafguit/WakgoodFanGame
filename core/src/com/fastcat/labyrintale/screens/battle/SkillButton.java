package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.uis.control.SkillButtonPanel;

import java.util.Objects;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.ActionHandler.isRunning;
import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_DESC;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

public class SkillButton extends AbstractUI {

    private final Sprite border = FileHandler.ui.get("BORDER");
    public AbstractSkill skill;
    public boolean isInfo = false;
    public boolean isOnLock = false;
    public boolean isCS = true;
    public boolean isSkill = true;
    public boolean isSelected = false;
    public boolean canClick = true;
    public boolean advisor = false;

    public SkillButton() {
        super(FileHandler.ui.get("BORDER"));
        fontData = CARD_BIG_DESC;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(isSelected) sb.setColor(Color.DARK_GRAY);
            else if (!over && !isInfo) sb.setColor(Color.LIGHT_GRAY);

            if(skill != null) {
                if (showImg) sb.draw(skill.img, x, y, sWidth, sHeight);
            }
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if(skill != null) {
            if(skill.owner != null && !skill.owner.isAlive()) {
                skill = null;
            } else if(over) {
                cPanel.infoPanel.setInfo(skill);
                battleScreen.looking = getTargets(skill);
            }
        }
    }

    public void removeChar() {
        skill = null;
        showImg = false;
        isOnLock = false;
        isSelected = false;
    }
}
