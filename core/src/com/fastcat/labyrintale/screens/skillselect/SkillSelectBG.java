package com.fastcat.labyrintale.screens.skillselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.skillselect.SkillSelectScreen.SkillSelectGroup;

public class SkillSelectBG extends AbstractUI {

    private final SkillSelectGroup group;
    public AbstractPlayer p;

    public SkillSelectBG(SkillSelectGroup g, AbstractPlayer p) {
        super(FileHandler.getUi().get("WAY_SELECT"));
        group = g;
        this.p = p;
        overable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            // if(reward.isDone) sb.setColor(p.pColorDG);
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {}

    @Override
    protected void onOver() {}

    @Override
    protected void onClick() {}
}
