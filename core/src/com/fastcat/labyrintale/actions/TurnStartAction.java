package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.ActionHandler;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;

public class TurnStartAction extends AbstractAction {

    private final AbstractEntity e;

    public TurnStartAction(AbstractEntity e) {
        super(null, 0);
        this.e = e;
    }

    @Override
    protected void updateAction() {
        if (isDone) {
            if (e.isAlive()) {
                if(e.block > 0 && e.blockRemove > 0 && !e.hasStatus("Maintain")) {
                    e.block = Math.max(e.block - e.blockRemove, 0);
                    e.blockRemove = 0;
                }
                if(e.isPlayer) {
                    e.passive.startOfTurn();
                    for (AbstractItem m : e.item) {
                        if (m != null) m.startOfTurn();
                    }
                }
                for(AbstractStatus s : e.status) {
                    s.startOfTurn();
                }
            }
            if (e.isPlayer) ActionHandler.bot(new PlayerTurnAction((AbstractPlayer) e));
            else ActionHandler.bot(new EnemyTurnAction((AbstractEnemy) e));
        }
    }
}
