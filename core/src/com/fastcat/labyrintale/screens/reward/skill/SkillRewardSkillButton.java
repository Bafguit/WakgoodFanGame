package com.fastcat.labyrintale.screens.reward.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.screens.reward.skill.SkillRewardScreen.SkillRewardGroup;

public class SkillRewardSkillButton extends AbstractUI {

    private final Sprite border = FileHandler.ui.get("BORDER");

    private final SkillRewardGroup group;
    public AbstractSkill skill;
    public boolean isTo;

    public SkillRewardSkillButton(SkillRewardGroup g, AbstractSkill s) {
        this(g, s, false);
    }

    public SkillRewardSkillButton(SkillRewardGroup g, AbstractSkill s, boolean isTo) {
        super(s.img);
        group = g;
        skill = s;
        this.isTo = isTo;
        if(!isTo) {
            AbstractSkill t = group.toSkill.skill;
            for(int i = 0; i < t.upgradeCount; i++) {
                skill.upgrade();
            }
        }
    }

    @Override
    protected void updateButton() {
        if(over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(skill);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (over || (isTo && group.bg.over)) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            if(showImg) sb.draw(img, x, y, sWidth, sHeight);
            if(isTo) FontHandler.renderCenter(sb, FontHandler.BORDER.font, "↕", x + sWidth * 0.5f, y + sHeight + Gdx.graphics.getHeight() * 0.035f);
            sb.draw(border, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if(!isTo) {
            int index = 0;
            for(int i = 0; i < group.player.deck.size; i++) {
                if(group.player.deck.get(i).id.equals(group.toSkill.skill.id)) {
                    index = i;
                }
            }
            group.player.deck.removeIndex(index);
            group.player.deck.insert(index, skill);
            Labyrintale.removeTempScreen(SkillRewardGroup.screen);
        }
    }
}
