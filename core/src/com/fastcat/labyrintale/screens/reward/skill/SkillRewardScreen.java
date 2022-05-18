package com.fastcat.labyrintale.screens.reward.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.rewards.SkillReward;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import java.util.Objects;

import static com.fastcat.labyrintale.rewards.SkillReward.*;
import static com.fastcat.labyrintale.rewards.SkillReward.SkillRewardType.UPGRADE;

public class SkillRewardScreen extends AbstractScreen {

    public final SkillRewardType type;
    public final SkillReward reward;
    public final SkillRewardGroup[] groups;

    public SkillRewardScreen(SkillRewardType type, SkillReward r) {
        cType = ControlPanel.ControlType.BASIC;
        SkillRewardGroup.screen = this;
        this.type = type;
        reward = r;
        groups = new SkillRewardGroup[reward.group.size];
        for(int i = 0; i < reward.group.size; i++) {
            Array<AbstractSkill> a = reward.group.get(i);
            groups[i] = new SkillRewardGroup((AbstractPlayer) a.get(0).owner, a, this.type, a.size);
        }
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < groups.length; i++) {
            SkillRewardGroup g = groups[i];
            float ww = w / groups.length, wc = w / groups.length / 2 + ww * i, hc = h * 0.73f;
            g.bg.setPosition(wc - g.bg.sWidth / 2, hc - g.bg.sHeight * 0.5f);
            g.cIcon.setPosition(wc - g.cIcon.sWidth * 0.5f, h * 0.9f - g.cIcon.width * 0.5f);
            if(g.skills.length > 1) {
                g.skills[0].setPosition(wc - g.skills[0].sWidth * 1.1f, hc - g.skills[0].sHeight * 0.5f);
                g.skills[1].setPosition(wc + g.skills[1].sWidth * 0.1f, hc - g.skills[1].sHeight * 0.5f);
            } else g.skills[0].setPosition(wc - g.skills[0].sWidth * 0.5f, hc - g.skills[0].sHeight * 0.5f);
            g.toSkill.setPosition(wc - g.toSkill.sWidth * 0.5f, h * 0.575f - g.toSkill.sHeight * 0.5f);
        }
    }

    @Override
    public void update() {
        for(SkillRewardGroup g : groups) {
            g.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for(SkillRewardGroup g : groups) {
            g.render(sb);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public static class SkillRewardGroup {
        public static AbstractScreen screen;

        public SkillRewardBG bg;
        public AbstractPlayer player;
        public SkillRewardCharIcon cIcon;
        public SkillRewardSkillButton[] skills;
        public SkillRewardSkillButton toSkill;

        public SkillRewardGroup(AbstractPlayer p, Array<AbstractSkill> s, SkillRewardType type, int l) {
            player = p;
            bg = new SkillRewardBG(this, player);
            cIcon = new SkillRewardCharIcon(this, player);
            skills = new SkillRewardSkillButton[l];
            if(type == UPGRADE) {
                toSkill = new SkillRewardSkillButton(this, s.get(0), true);
                AbstractSkill ss = Objects.requireNonNull(s.get(0).clone());
                ss.upgrade();
                skills[0] = new SkillRewardSkillButton(this, ss);
            } else {
                toSkill = new SkillRewardSkillButton(this, player.deck.get(AbstractLabyrinth.publicRandom.random(player.deck.size - 1)), true);
                skills[0] = new SkillRewardSkillButton(this, s.get(0));
                skills[1] = new SkillRewardSkillButton(this, s.get(1));
            }
            toSkill.isTo = true;
        }

        public void update() {
            bg.update();
            cIcon.update();
            for(SkillRewardSkillButton s : skills) {
                s.update();
            }
            toSkill.update();
        }

        public void render(SpriteBatch sb) {
            bg.render(sb);
            cIcon.render(sb);
            for(SkillRewardSkillButton s : skills) {
                s.render(sb);
            }
            toSkill.render(sb);
        }
    }
}
