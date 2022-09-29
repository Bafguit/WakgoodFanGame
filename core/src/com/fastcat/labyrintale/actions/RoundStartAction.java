package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class RoundStartAction extends AbstractAction {

    public RoundStartAction() {
        super(null, 0);
    }

    @Override
    protected void updateAction() {
        if (isDone) {
            for(AbstractEntity e : Labyrintale.battleScreen.getTurns()) {
                if(e.isPlayer) {
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
