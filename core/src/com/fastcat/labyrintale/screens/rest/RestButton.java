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

    public static final int HEAL_AMOUNT = 10;

    public RestScreen screen;
    public RestType type;

    public RestButton(RestScreen s, RestType t) {
        super(FileHandler.ui.get("WAY_SELECT"));
        screen = s;
        type = t;
    }

    @Override
    public void onClick() {
        AbstractLabyrinth.finishRoom();
        Array<AbstractReward> temp = new Array<>();
        if(type == RestType.HEAL) {
            temp.add(new HealReward(HEAL_AMOUNT));
        } else if (type == RestType.UPGRADE) {
            temp.add(new SkillRewardUpgrade());
        } else {
            temp.add(new SkillRewardNormal(AbstractLabyrinth.selection));
        }
        Labyrintale.addTempScreen(new RewardScreen(RewardScreen.RewardScreenType.REST, temp));
    }

    public enum RestType {
        HEAL, UPGRADE, DISCOVER
    }
}
