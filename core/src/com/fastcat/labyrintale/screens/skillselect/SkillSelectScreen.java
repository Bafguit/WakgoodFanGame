package com.fastcat.labyrintale.screens.skillselect;

import static com.fastcat.labyrintale.rewards.SkillUpgradeReward.SkillRewardType;
import static com.fastcat.labyrintale.rewards.SkillUpgradeReward.SkillRewardType.UPGRADE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.interfaces.GetRewardDone;
import com.fastcat.labyrintale.interfaces.GetSelectedSkill;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import java.util.Objects;

public class SkillSelectScreen extends AbstractScreen implements GetSelectedSkill {

    public final SkillRewardType type;
    public final SkillSelectGroup[] groups;
    private final BgImg bgImg;
    public GetSelectedSkill gets;
    public GetRewardDone rewardDone;

    public SkillSelectScreen(
            SkillRewardType type, Array<Array<AbstractSkill>> group, GetSelectedSkill gets, GetRewardDone rewardDone) {
        this(type, group);
        this.gets = gets;
        this.rewardDone = rewardDone;
    }

    public SkillSelectScreen(SkillRewardType type, Array<Array<AbstractSkill>> group) {
        cType = ControlPanel.ControlType.BASIC;
        SkillSelectGroup.screen = this;
        this.type = type;
        bgImg = new BgImg();
        groups = new SkillSelectGroup[group.size];
        for (int i = 0; i < group.size; i++) {
            Array<AbstractSkill> a = group.get(i);
            groups[i] = new SkillSelectGroup((AbstractPlayer) a.get(0).owner, a, this.type, a.size);
        }
        float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;
        for (int i = 0; i < groups.length; i++) {
            SkillSelectGroup g = groups[i];
            float ww = w / groups.length, wc = w / groups.length / 2 + ww * i, hc = h * 0.73f;
            g.bg.setPosition(wc - g.bg.sWidth / 2, hc - g.bg.sHeight * 0.5f);
            g.cIcon.setPosition(wc - g.cIcon.sWidth * 0.5f, h * 0.9f - g.cIcon.width * 0.5f);
            if (g.skills.length > 1) {
                g.skills[0].setPosition(wc - g.skills[0].sWidth * 1.1f, hc - g.skills[0].sHeight * 0.5f);
                g.skills[1].setPosition(wc + g.skills[1].sWidth * 0.1f, hc - g.skills[1].sHeight * 0.5f);
            } else g.skills[0].setPosition(wc - g.skills[0].sWidth * 0.5f, hc - g.skills[0].sHeight * 0.5f);
            g.toSkill.setPosition(wc - g.toSkill.sWidth * 0.5f, h * 0.575f - g.toSkill.sHeight * 0.5f);
        }
    }

    @Override
    public void update() {
        for (SkillSelectGroup g : groups) {
            g.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        bgImg.render(sb);
        for (SkillSelectGroup g : groups) {
            g.render(sb);
        }
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    @Override
    public void skillSelected(SkillSelectGroup skill) {
        if (gets != null) gets.skillSelected(skill);
    }

    public static class SkillSelectGroup {
        public static SkillSelectScreen screen;

        public SkillSelectBG bg;
        public AbstractPlayer player;
        public SkillSelectCharIcon cIcon;
        public SkillSelectButton[] skills;
        public SkillSelectButton toSkill;
        public AbstractSkill selected;

        public SkillSelectGroup(AbstractPlayer p, Array<AbstractSkill> s, SkillRewardType type, int l) {
            player = p;
            bg = new SkillSelectBG(this, player);
            cIcon = new SkillSelectCharIcon(this, player);
            skills = new SkillSelectButton[l];
            if (type == UPGRADE) {
                toSkill = new SkillSelectButton(this, s.get(0), true);
                AbstractSkill ss = Objects.requireNonNull(s.get(0).clone());
                ss.upgrade();
                skills[0] = new SkillSelectButton(this, ss);
            } else {
                toSkill = new SkillSelectButton(
                        this, player.deck.get(AbstractLabyrinth.publicRandom.random(player.deck.size - 1)), true);
                skills[0] = new SkillSelectButton(this, s.get(0));
                skills[1] = new SkillSelectButton(this, s.get(1));
            }
            toSkill.isTo = true;
        }

        public void update() {
            bg.update();
            cIcon.update();
            for (SkillSelectButton s : skills) {
                s.update();
            }
            toSkill.update();
        }

        public void render(SpriteBatch sb) {
            bg.render(sb);
            cIcon.render(sb);
            for (SkillSelectButton s : skills) {
                s.render(sb);
            }
            toSkill.render(sb);
        }
    }
}
