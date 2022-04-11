package com.fastcat.labyrintale.screens.reward.skill;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.reward.skill.SkillRewardScreen.SkillRewardGroup;


public class SkillRewardBG extends AbstractUI {

    private final SkillRewardGroup group;
    public AbstractPlayer p;

    public SkillRewardBG(SkillRewardGroup g, AbstractPlayer p) {
        super(FileHandler.ui.get("CHAR_SKILL_REWARD"));
        group = g;
        this.p = p;
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            //if(reward.isDone) sb.setColor(p.pColorDG);
            if (!over) sb.setColor(p.pColorLG);
            else sb.setColor(p.pColorW);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {

    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {

    }
}
