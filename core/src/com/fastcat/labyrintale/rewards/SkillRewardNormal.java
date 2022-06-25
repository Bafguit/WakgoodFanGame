package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.screens.skillselect.GetSelectedSkill;
import com.fastcat.labyrintale.screens.skillselect.SkillSelectScreen;

import static com.fastcat.labyrintale.handlers.GroupHandler.SkillGroup.getRandomSkill;

public class SkillRewardNormal extends SkillReward {

    public SkillRewardNormal(int cardAmount, GetSelectedSkill gets) {
        this.gets = gets;
        Array<AbstractPlayer> ap = new Array<>(AbstractLabyrinth.players);
        for(int i = 0; i < ap.size; i++) {
            AbstractPlayer p = ap.get(i);
            if(p.isAlive())
                group.add(getRandomSkill(p, cardAmount));
        }
        desc = "스킬 보상을 획득합니다.";
    }

    public SkillRewardNormal(int cardAmount) {
        this(cardAmount, null);
    }

    @Override
    public void takeReward() {
        Labyrintale.addTempScreen(new SkillSelectScreen(SkillRewardType.NORMAL, group, this));
    }
}
