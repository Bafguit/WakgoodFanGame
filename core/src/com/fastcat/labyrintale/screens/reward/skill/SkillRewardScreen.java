package com.fastcat.labyrintale.screens.reward.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.rewards.SkillReward;
import com.fastcat.labyrintale.rewards.SkillRewardNormal;

import static com.fastcat.labyrintale.rewards.SkillReward.*;

public class SkillRewardScreen extends AbstractScreen {

    public final SkillRewardType type;
    public final SkillReward reward;
    public final Array<SkillRewardGroup> groups = new Array<>();

    public SkillRewardScreen(SkillRewardType type, SkillReward r) {
        SkillRewardGroup.screen = this;
        this.type = type;
        reward = r;
        for(int i = 0; i < reward.group.size(); i++) {
            Array<AbstractSkill> a = reward.group.get(i);
            groups.add(new SkillRewardGroup((AbstractPlayer) a.get(0).owner, a));
        }
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < groups.size; i++) {
            SkillRewardGroup g = groups.get(i);
            float ww = w / groups.size * i, wc = w / groups.size / 2 + ww, hc = h * 0.7f;
            g.bg.setPosition(ww, hc - g.bg.sHeight * 0.5f);
            g.cIcon.setPosition(wc - g.cIcon.sWidth * 0.5f, h * 0.85f - g.cIcon.width * 0.5f);
            g.skills[0].setPosition(wc - g.skills[0].sWidth * 1.1f, hc - g.skills[0].sHeight * 0.5f);
            g.skills[1].setPosition(wc + g.skills[1].sWidth * 0.1f, hc - g.skills[1].sHeight * 0.5f);
            g.toSkill.setPosition(wc - g.toSkill.sWidth * 0.5f, h * 0.55f - g.toSkill.sHeight * 0.5f);
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
        public SkillRewardSkillButton[] skills = new SkillRewardSkillButton[2];
        public SkillRewardSkillButton toSkill;

        public SkillRewardGroup(AbstractPlayer p, Array<AbstractSkill> s) {
            player = p;
            bg = new SkillRewardBG(this, player);
            cIcon = new SkillRewardCharIcon(this, player);
            toSkill = new SkillRewardSkillButton(this, player.deck.get(AbstractLabyrinth.skillRandom.nextInt(player.deck.size)), true);
            toSkill.isTo = true;
            skills[0] = new SkillRewardSkillButton(this, s.get(0));
            skills[1] = new SkillRewardSkillButton(this, s.get(1));
        }

        public void update() {
            bg.update();
            cIcon.update();
            skills[0].update();
            skills[1].update();
            toSkill.update();
        }

        public void render(SpriteBatch sb) {
            bg.render(sb);
            cIcon.render(sb);
            skills[0].render(sb);
            skills[1].render(sb);
            toSkill.render(sb);
        }
    }
}
