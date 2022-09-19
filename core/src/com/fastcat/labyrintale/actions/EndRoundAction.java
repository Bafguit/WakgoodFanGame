package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.PLAYER_FIRST;

public class EndRoundAction extends AbstractAction {
    public EndRoundAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if (isDone) {
            for (int i = 0; i < 4; i++) {
                AbstractPlayer t = battleScreen.players[i].player;
                for (int j = 0; j < 3; j++) {
                    if (t.hand[j].cooldown > 0) {
                        t.hand[j].cooldown--;
                    }
                }
                if (t.mLeftTemp.cooldown > 0) t.mLeftTemp.cooldown--;
                if (t.mRightTemp.cooldown > 0) t.mRightTemp.cooldown--;
                AbstractEnemy e = battleScreen.enemies[i].enemy;
                if(e.isAlive()) {
                    e.atEndOfRound();
                    e.shuffleHand();
                }
            }
            if (AbstractLabyrinth.advisor.skill.cooldown > 0) AbstractLabyrinth.advisor.skill.cooldown--;
            if (AbstractLabyrinth.cPanel.battlePanel.curPlayer == null || !AbstractLabyrinth.cPanel.battlePanel.curPlayer.isAlive()) {
                Array<AbstractEntity> temp = AbstractSkill.getTargets(PLAYER_FIRST);
                if (temp.size > 0) {
                    AbstractLabyrinth.cPanel.battlePanel.setPlayer((AbstractPlayer) temp.get(0));
                }
            }
        }
    }
}
