package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.rewards.GoldReward;
import com.fastcat.labyrintale.rewards.SkillRewardNormal;
import com.fastcat.labyrintale.screens.reward.RewardScreen;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class VictoryAction extends AbstractAction {

    public VictoryAction() {
        super(null, 2);
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            SoundHandler.fadeOutMusic("BATTLE_1");
        } else if(isDone) {
            for(AbstractPlayer p : AbstractLabyrinth.players) {
                p.block = 0;
                p.status = new AbstractStatus[5];
            }
            AbstractLabyrinth.finishRoom();
            Array<AbstractReward> temp = new Array<>();
            temp.add(new SkillRewardNormal(AbstractLabyrinth.selection));
            temp.add(new GoldReward(20));
            Labyrintale.addTempScreen(new RewardScreen(RewardScreen.RewardScreenType.VICTORY, temp));
            Labyrintale.battleScreen.cType = ControlPanel.ControlType.BASIC;
        }
    }
}
