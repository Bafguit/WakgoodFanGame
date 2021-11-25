package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.screens.battle.SkillButton;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

public class EndRoundAction extends AbstractAction {
    public EndRoundAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if(isDone) {
            for (int i = 0; i < 4; i++) {
                battleScreen.preSkills[i].removeChar();
                battleScreen.players[i].player.shuffleHand();
                battleScreen.enemies[i].enemy.shuffleHand();
            }
        }
    }
}
