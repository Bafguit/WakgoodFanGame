package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.rewards.GoldReward;
import com.fastcat.labyrintale.rewards.HealReward;
import com.fastcat.labyrintale.rewards.SkillRewardNormal;
import com.fastcat.labyrintale.rewards.SkillRewardUpgrade;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.reward.RewardScreen;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class RestButton extends AbstractUI {

    public RestScreen screen;
    public RestType type;

    public RestButton(RestScreen s, RestType t) {
        super(FileHandler.CHAR_SKILL_REWARD);
        screen = s;
        type = t;
    }

    @Override
    public void onClick() {
        AbstractLabyrinth.currentFloor.currentWay.done();
        AbstractLabyrinth.currentFloor.currentRoom.done();
        Array<AbstractReward> temp = new Array<>();
        if(type == RestType.HEAL) {
            temp.add(new HealReward(10));
        } else if (type == RestType.UPGRADE) {
            temp.add(new SkillRewardUpgrade());
        } else {
            temp.add(new SkillRewardNormal(2));
        }
        Labyrintale.addTempScreen(new RewardScreen(RewardScreen.RewardScreenType.REST, temp));
    }

    public enum RestType {
        HEAL, UPGRADE, DISCOVER
    }
}
