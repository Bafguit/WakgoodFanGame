package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.rewards.GoldReward;
import com.fastcat.labyrintale.rewards.SkillReward;
import com.fastcat.labyrintale.screens.reward.RewardScreen;

public class VictoryAction extends AbstractAction {

    public VictoryAction() {
        super(null, 2);
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            ActionHandler.clear();
        } else if(isDone) {
            Array<AbstractReward> temp = new Array<>();
            //temp.add(new SkillReward(AbstractLabyrinth.players[AbstractLabyrinth.publicRandom.nextInt(4)].playerClass, SkillReward.SkillRewardType.COMMON, 3));
            temp.add(new GoldReward(20));
            Labyrintale.addTempScreen(new RewardScreen(RewardScreen.RewardScreenType.VICTORY, temp));
        }
    }
}
