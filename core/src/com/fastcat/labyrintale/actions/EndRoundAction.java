package com.fastcat.labyrintale.actions;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.PLAYER_FIRST;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;

public class EndRoundAction extends AbstractAction {
    public EndRoundAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if (isDone) {
            for (int i = 0; i < 4; i++) {
                AbstractEntity e = battleScreen.enemies[i].entity;
                if (e.isAlive()) {
                    e.atEndOfTurn();
                    e.shuffleHand();
                }
            }
            if (AbstractLabyrinth.cPanel.battlePanel.curPlayer == null
                    || !AbstractLabyrinth.cPanel.battlePanel.curPlayer.isAlive()) {
                Array<AbstractEntity> temp = AbstractSkill.getTargets(PLAYER_FIRST);
                if (temp.size > 0) {
                    AbstractLabyrinth.cPanel.battlePanel.setEntity(temp.get(0));
                }
            }
        }
    }
}
