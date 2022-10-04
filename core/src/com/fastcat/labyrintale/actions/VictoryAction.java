package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.rewards.*;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.reward.RewardScreen;

import java.util.LinkedList;

import static com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass.DOPA;
import static com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass.SOPHIA;
import static com.fastcat.labyrintale.handlers.GroupHandler.SkillGroup.getRandomSkill;

public class VictoryAction extends AbstractAction {

    public VictoryAction() {
        super(null, 1.0f);
    }

    public VictoryAction(float dur) {
        super(null, dur);
    }

    @Override
    protected void applySetting() {

    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            SoundHandler.fadeOutMusic("BATTLE_1");
            SoundHandler.fadeOutMusic("BATTLE_BOSS");
        }
        if (isDone) {
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                p.block = 0;
                p.blockRemove = 0;
                p.isNeut = false;
                p.status = new LinkedList<>();
                p.movable = 0;
            }
            if (Labyrintale.battleScreen.type == BattleScreen.BattleType.NORMAL) {
                AbstractLabyrinth.victoryRoom();
            }
            Array<AbstractReward> temp = new Array<>();
            //temp.add(new StatReward());
            temp.add(new HealReward());
            int r;
            AbstractPlayer tp = AbstractLabyrinth.players[AbstractLabyrinth.publicRandom.random(0, 3)];
            if (AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.ELITE) {
                AbstractPlayer tp2 = AbstractLabyrinth.players[AbstractLabyrinth.publicRandom.random(0, 3)];
                if (AbstractLabyrinth.advisor.cls == DOPA) {
                    if (AbstractLabyrinth.hasSlot()) temp.add(new SlotReward());
                }
                if(tp.id.equals(tp2.id)) {
                    Array<AbstractSkill> ss = getRandomSkill(tp, 2);
                    temp.add(new SkillReward(ss.get(0)));
                    temp.add(new SkillReward(ss.get(1)));
                } else {
                    temp.add(new SkillReward(getRandomSkill(tp)));
                    temp.add(new SkillReward(getRandomSkill(tp2)));
                }
            } else {
                temp.add(new SkillReward(getRandomSkill(tp)));
            }
            if (AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.ELITE) {
                temp.add(new ItemReward(ItemReward.ItemRewardType.NORMAL));
            }
            int g;
            if(AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.BOSS) g = 100;
            else if(AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.ELITE) g = 60;
            else g = 30;
            g += AbstractLabyrinth.publicRandom.random(-5, 5);
            if (AbstractLabyrinth.advisor.cls == SOPHIA) g = MathUtils.floor(g * 1.2f);
            temp.add(new GoldReward(AbstractLabyrinth.restriction.onGainGoldReward(g)));
            int e;
            if(AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.BOSS) e = 300;
            else if(AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.ELITE) e = 200;
            else e = 100;
            e += AbstractLabyrinth.currentFloor.floorNum * 2;
            AbstractLabyrinth.gainExp(e);
            Labyrintale.fadeOutAndAddScreen(new RewardScreen(RewardScreen.RewardScreenType.VICTORY, temp));
        }
    }
}
