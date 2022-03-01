package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.rewards.GoldReward;
import com.fastcat.labyrintale.rewards.SkillRewardNormal;
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
            for(AbstractPlayer p : AbstractLabyrinth.players) {
                p.block = 0;
            }
            AbstractLabyrinth.currentFloor.currentWay.done(); //TODO 삭제 요망
            AbstractLabyrinth.currentFloor.currentRoom.done();
            Array<AbstractReward> temp = new Array<>();
            temp.add(new SkillRewardNormal(2));
            temp.add(new GoldReward(20));
            Labyrintale.addTempScreen(new RewardScreen(RewardScreen.RewardScreenType.VICTORY, temp));
        }
    }
}
