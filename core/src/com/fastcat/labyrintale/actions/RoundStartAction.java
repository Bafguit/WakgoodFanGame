package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;

public class RoundStartAction extends AbstractAction {

    public RoundStartAction() {
        super(null, 0);
    }

    @Override
    protected void updateAction() {
        if (isDone) {
            AbstractLabyrinth.energy += AbstractLabyrinth.charge;
            for(AbstractEntity e : Labyrintale.battleScreen.getTurns()) {
                e.blockRemove = e.block;
                if(e.isPlayer) {
                    e.passive.startOfRound();
                    for (AbstractItem m : e.item) {
                        if (m != null) m.startOfRound();
                    }
                }
                for (AbstractStatus s : e.status) {
                    s.startOfRound();
                }
            }
        }
    }
}
