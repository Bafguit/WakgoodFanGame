package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;

public class TurnEndAction extends AbstractAction {

    boolean isEnemy;

    public TurnEndAction(AbstractEntity e) {
        super(e, 0);
    }

    @Override
    protected void updateAction() {
        if (isDone) {
            if (actor.isAlive()) {
                for (AbstractStatus s : actor.status) {
                    if (s != null) s.endOfTurn();
                }
                if (actor.isPlayer) {
                    actor.passive.endOfTurn();
                    for (AbstractItem s : actor.item) {
                        if (s != null) s.endOfTurn();
                    }
                } else {
                    actor.atEndOfTurn();
                    actor.shuffleHand();
                }
            }
        }
    }
}
