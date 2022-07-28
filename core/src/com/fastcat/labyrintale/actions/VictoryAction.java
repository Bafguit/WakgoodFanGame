package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.rewards.*;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.reward.RewardScreen;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import java.util.LinkedList;

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
        if (duration == baseDuration) {
            SoundHandler.fadeOutMusic("BATTLE_1");
        }
        if (isDone) {
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                p.block = 0;
                p.isNeut = false;
                p.status = new LinkedList<>();
                p.movable = 0;
            }
            if (Labyrintale.battleScreen.type == BattleScreen.BattleType.NORMAL) {
                AbstractLabyrinth.victoryRoom();
            }
            Array<AbstractReward> temp = new Array<>();
            temp.add(new ExpReward());
            int r;
            if (AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.ELITE) {
                if (AbstractLabyrinth.advisor.cls == DOPA) {
                    if (AbstractLabyrinth.hasSlot()) temp.add(new SlotReward());
                }
                temp.add(new SkillReward());
            } else {
                r = AbstractLabyrinth.publicRandom.random(100);
                if (r <= 10) temp.add(new SkillReward());
            }
            r = AbstractLabyrinth.publicRandom.random(100);
            if (AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.ELITE) {
                temp.add(new ItemReward(ItemReward.ItemRewardType.NORMAL));
            }
            if (r <= 40) {
                temp.add(new ItemReward(ItemReward.ItemRewardType.NORMAL));
            }
            int g = 20; //TODO 골드 보상 생성하는 메소드 만들기
            if (AbstractLabyrinth.advisor.cls == SOPHIA) g = MathUtils.floor(g * 1.2f);
            temp.add(new GoldReward(AbstractLabyrinth.restriction.onGainGoldReward(g)));
            Labyrintale.addTempScreen(new RewardScreen(RewardScreen.RewardScreenType.VICTORY, temp));
            Labyrintale.battleScreen.cType = ControlPanel.ControlType.BASIC;
        }
    }
}
