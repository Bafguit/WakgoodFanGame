package com.fastcat.labyrintale.screens.reward.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.rewards.SkillReward;
import com.fastcat.labyrintale.rewards.SkillRewardNormal;

import static com.fastcat.labyrintale.rewards.SkillReward.*;

public class SkillRewardScreen extends AbstractScreen {

    public final SkillRewardType type;
    public final SkillReward reward;
    public final Array<SkillRewardGroup> groups = new Array<SkillRewardGroup>();

    public SkillRewardScreen(SkillRewardType type, SkillReward r) {
        this.type = type;
        reward = r;
        for(AbstractPlayer p : reward.group.keySet()) {
            groups.add(new SkillRewardGroup(p));
        }
        for(int i = 0; i < groups.size; i++) {
            SkillRewardGroup g = groups.get(i);
            g.bg.setPosition(0, Gdx.graphics.getHeight() * ((float)(4 - i) / groups.size));
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

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
        public AbstractPlayer p;
        public SkillRewardBG bg;

        public SkillRewardGroup(AbstractPlayer p) {
            this.p = p;
            bg = new SkillRewardBG(this, this.p);
        }
    }
}
