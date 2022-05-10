package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.screens.reward.skill.SkillRewardScreen;

import static com.fastcat.labyrintale.handlers.GroupHandler.SkillGroup.getRandomSkill;

public class SkillRewardNormal extends SkillReward {

    public SkillRewardNormal(int cardAmount) {
        Array<AbstractPlayer> ap = new Array<>(AbstractLabyrinth.players);
        for(int i = 0; i < ap.size; i++) {
            AbstractPlayer p = ap.get(i);
            if(p.isAlive())
                group.add(getRandomSkill(p, cardAmount));
        }
        desc = "스킬 보상을 획득합니다.";
    }

    @Override
    public void takeReward() {
        Labyrintale.addTempScreen(new SkillRewardScreen(SkillRewardType.COMMON, this));
    }
}
