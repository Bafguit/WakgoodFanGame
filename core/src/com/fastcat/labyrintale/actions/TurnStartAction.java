package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;

public class TurnStartAction extends AbstractAction {

    boolean isEnemy;

    public TurnStartAction(AbstractEntity e) {
        super(e, 0);
    }

    @Override
    protected void updateAction() {
        if (isDone) {
            if (actor.isAlive()) {
                if(actor.isPlayer) {
                    for (AbstractItem m : actor.item) {
                        if (m != null) m.startOfTurn();
                    }
                }
                for(AbstractStatus s : actor.status) {
                    s.startOfTurn();
                }
            }
        }
    }
}
