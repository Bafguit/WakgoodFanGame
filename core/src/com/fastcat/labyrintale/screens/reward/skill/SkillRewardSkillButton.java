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
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.screens.reward.skill.SkillRewardScreen.SkillRewardGroup;

public class SkillRewardSkillButton extends AbstractUI {

    private final SkillRewardGroup group;
    public AbstractSkill skill;
    public boolean isTo;

    public SkillRewardSkillButton(SkillRewardGroup g, AbstractSkill s) {
        this(g, s, false);
    }

    public SkillRewardSkillButton(SkillRewardGroup g, AbstractSkill s, boolean isTo) {
        super(FileHandler.ui.get("BORDER_M"));
        group = g;
        skill = s;
        this.isTo = isTo;
        if(!isTo) {
            AbstractSkill t = group.toSkill.skill;
            for(int i = 0; i < t.upgradeCount; i++) {
                skill.upgrade();
            }

            //버린 횟수만큼 업그레이드
            Integer u = GroupHandler.SkillGroup.discardedCount.get(skill.id);
            if(u != null) {
                for (int i = 0; i < u; i++) {
                    skill.upgrade();
                }
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
            if(showImg) sb.draw(skill.img, x, y, sWidth, sHeight);
            if(isTo) FontHandler.renderCenter(sb, FontHandler.BORDER.font, "↕", x + sWidth * 0.5f, y + sHeight + Gdx.graphics.getHeight() * 0.03f);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if(!isTo) {
            int index = 0;
            AbstractSkill ts = group.toSkill.skill;
            for(int i = 0; i < group.player.deck.size; i++) {
                if(group.player.deck.get(i).id.equals(ts.id)) {
                    index = i;
                }
            }
            Integer up = GroupHandler.SkillGroup.discardedCount.get(ts.id);
            if(up != null) {
                GroupHandler.SkillGroup.discardedCount.put(ts.id, up + 1);
            }
            group.player.deck.removeIndex(index);
            group.player.deck.insert(index, skill);
            Labyrintale.removeTempScreen(SkillRewardGroup.screen);
        }
    }
}
