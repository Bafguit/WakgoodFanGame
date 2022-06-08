package com.fastcat.labyrintale.screens.reward.skill;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.reward.skill.SkillRewardScreen.SkillRewardGroup;

import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class SkillRewardCharIcon extends AbstractUI {

    private final Sprite border;

    private final SkillRewardGroup group;
    public AbstractPlayer p;

    public SkillRewardCharIcon(SkillRewardGroup g, AbstractPlayer p) {
        super(FileHandler.ui.get("BORDER_M"));
        group = g;
        this.p = p;
        border = charImg.get(p.playerClass);
        clickable = false;
    }

    @Override
    protected void updateButton() {

    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (group.bg.over) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            if(showImg) sb.draw(border, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {

    }

    public void removeChar() {

    }
}
