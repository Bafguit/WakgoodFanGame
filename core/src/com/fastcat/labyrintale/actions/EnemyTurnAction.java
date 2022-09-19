package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class EnemyTurnAction extends AbstractAction {

    public EnemyTurnAction(AbstractEnemy e) {
        super(e, 0.5f);
    }

    @Override
    protected void updateAction() {
        if(isDone) {
            actor.hand[0].useCard();
        }
    }
}
