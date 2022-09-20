package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class EnemyTurnAction extends AbstractAction {

    private final AbstractEnemy e;

    public EnemyTurnAction(AbstractEnemy e) {
        super(null, 0.5f);
        this.e = e;
    }

    @Override
    protected void updateAction() {
        if(isDone) {
            if(e.isAlive()) e.hand[0].useCard();
            ActionHandler.bot(new NextTurnAction());
        }
    }
}
