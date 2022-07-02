package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.rewards.*;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;
import com.fastcat.labyrintale.interfaces.GetSelectedSkill;
import com.fastcat.labyrintale.screens.skillselect.SkillSelectScreen;

import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;

public class RestButton extends AbstractUI implements GetSelectedPlayer, GetSelectedSkill {

    public static final int HEAL_AMOUNT = 10;

    public RestScreen sc;
    public RestType type;

    public RestButton(RestScreen s, RestType t) {
        super(FileHandler.ui.get("WAY_SELECT"));
        sc = s;
        type = t;
    }

    @Override
    public void onClick() {
        if(type == RestType.HEAL) {
            Array<AbstractPlayer> temp = new Array<>();
            for(AbstractPlayer p : AbstractLabyrinth.players) {
                if(p.isAlive()) temp.add(p);
            }
            AbstractPlayer[] tp = new AbstractPlayer[temp.size];
            for(int i = 0; i < temp.size; i++) {
                tp[i] = temp.get(i);
            }
            Labyrintale.addTempScreen(new PlayerSelectScreen(tp, this));
        } else if (type == RestType.UPGRADE) {
            Labyrintale.addTempScreen(new SkillSelectScreen(SkillUpgradeReward.SkillRewardType.UPGRADE, new SkillRewardUpgrade(this)));
        } else if(type == RestType.REVIVE) {
            Array<AbstractPlayer> temp = new Array<>();
            for(AbstractPlayer p : AbstractLabyrinth.players) {
                if(!p.isAlive()) temp.add(p);
            }
            AbstractPlayer[] tp = new AbstractPlayer[temp.size];
            for(int i = 0; i < temp.size; i++) {
                tp[i] = temp.get(i);
            }
            Labyrintale.addTempScreen(new PlayerSelectScreen(tp, this));
        }
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        if(type == RestType.HEAL) {
            player.heal(HEAL_AMOUNT);
        } else if(type == RestType.REVIVE) {
            player.revive();
        }
        sc.finishRest();
    }

    @Override
    public void skillSelected(SkillSelectScreen.SkillSelectGroup group) {
        sc.finishRest();
    }

    public enum RestType {
        HEAL, UPGRADE, DISCOVER, REVIVE
    }
}
