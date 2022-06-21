package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.rewards.GoldReward;
import com.fastcat.labyrintale.rewards.SkillRewardNormal;
import com.fastcat.labyrintale.rewards.SkillRewardUpgrade;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.reward.RewardScreen;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass.DOPA;
import static com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass.SOPHIA;

public class VictoryAction extends AbstractAction {

    public VictoryAction() {
        super(null, 2);
    }

    public VictoryAction(float dur) {
        super(null, dur);
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            SoundHandler.fadeOutMusic("BATTLE_1");
        }
        if(isDone) {
            for(AbstractPlayer p : AbstractLabyrinth.players) {
                p.block = 0;
                p.isNeut = false;
                p.status = new AbstractStatus[4];
            }
            if(Labyrintale.battleScreen.type == BattleScreen.BattleType.NORMAL) {
                AbstractLabyrinth.finishRoom();
            }
            Array<AbstractReward> temp = new Array<>();
            temp.add(new SkillRewardNormal(AbstractLabyrinth.selection));
            if(AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.ELITE) {
                if(AbstractLabyrinth.advisor.cls == DOPA) temp.add(new SkillRewardUpgrade());
                //아이템 리워드 추가
            }
            int g = 20; //TODO 골드 보상 생성하는 메소드 만들기
            if(AbstractLabyrinth.advisor.cls == SOPHIA) g = MathUtils.floor(20 * 1.2f);
            temp.add(new GoldReward(g));
            Labyrintale.addTempScreen(new RewardScreen(RewardScreen.RewardScreenType.VICTORY, temp));
            Labyrintale.battleScreen.cType = ControlPanel.ControlType.BASIC;
        }
    }
}
