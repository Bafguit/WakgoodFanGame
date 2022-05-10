package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.screens.reward.skill.SkillRewardScreen;

import static com.fastcat.labyrintale.handlers.GroupHandler.SkillGroup.getRandomSkill;
import static com.fastcat.labyrintale.handlers.GroupHandler.SkillGroup.getRandomUpgradedSkillFromDeck;

public class SkillRewardUpgrade extends SkillReward {

    public SkillRewardUpgrade() {
        Array<AbstractPlayer> ap = new Array<>(AbstractLabyrinth.players);
        for(int i = 0; i < ap.size; i++) {
            AbstractPlayer p = ap.get(i);
            if(p.isAlive()) {
                Array<AbstractSkill> t = new Array<>();
                t.add(getRandomUpgradedSkillFromDeck(p, true));
                group.add(t);
            }
        }
        desc = "무작위 스킬을 강화합니다.";
    }

    @Override
    public void takeReward() {
        Labyrintale.addTempScreen(new SkillRewardScreen(SkillRewardType.UPGRADE, this));
    }
}
