package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
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
                    if(t.hand[j].cooldown > 0) {
                        t.hand[j].cooldown--;
                    }
                }
                if(t.mLeftTemp.cooldown > 0) t.mLeftTemp.cooldown--;
                if(t.mRightTemp.cooldown > 0) t.mRightTemp.cooldown--;
                battleScreen.enemies[i].enemy.shuffleHand();
            }
            AbstractLabyrinth.energy = AbstractLabyrinth.maxEnergy;
            Array<AbstractEntity> temp = AbstractSkill.getTargets(P_F);
            if(temp.size > 0) {
                AbstractLabyrinth.cPanel.battlePanel.setPlayer((AbstractPlayer) temp.get(0));
            }
        }
    }
}
