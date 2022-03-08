package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.P_F;

public class EndRoundAction extends AbstractAction {
    public EndRoundAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if(isDone) {
            for (int i = 0; i < 4; i++) {
                AbstractPlayer t = battleScreen.players[i].player;
                for(int j = 0; j < 4; j++) {
                    t.hand[j].used = false;
                }
                battleScreen.players[i].player.shuffleHand();
                battleScreen.enemies[i].enemy.shuffleHand();
            }
            Array<AbstractEntity> temp = AbstractSkill.getTargets(P_F);
            if(temp.size > 0) {
                AbstractLabyrinth.cPanel.battlePanel.setPlayer((AbstractPlayer) temp.get(0));
            }
        }
    }
}
