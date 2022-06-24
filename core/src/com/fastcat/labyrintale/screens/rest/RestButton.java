package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.rewards.*;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.playerselect.GetSelectedPlayer;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;
import com.fastcat.labyrintale.screens.reward.RewardScreen;
import com.fastcat.labyrintale.screens.reward.skill.SkillRewardScreen;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class RestButton extends AbstractUI implements GetSelectedPlayer {

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
        if(type == RestType.HEAL) {
            for(AbstractPlayer p : AbstractLabyrinth.players) {
                if(p.isAlive()) p.heal(HEAL_AMOUNT);
            }
        } else if (type == RestType.UPGRADE) {
            Labyrintale.addTempScreen(new SkillRewardScreen(SkillReward.SkillRewardType.UPGRADE, new SkillRewardUpgrade()));
        } else if(type == RestType.REVIVE) {
            Array<AbstractPlayer> temp = new Array<>();
            for(AbstractPlayer p : AbstractLabyrinth.players) {
                if(!p.isAlive()) temp.add(p);
            }
            Labyrintale.addTempScreen(new PlayerSelectScreen(temp.items, this));
        }
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        player.revive();
    }

    public enum RestType {
        HEAL, UPGRADE, DISCOVER, REVIVE
    }
}
