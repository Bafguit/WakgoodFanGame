package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;

public class EnemyTurnStartAction extends AbstractAction {

    boolean isEnemy;

    public EnemyTurnStartAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            for (int j = 3; j >= 0; j--) {
                AbstractEnemy e = AbstractLabyrinth.currentFloor.currentRoom.enemies[j];
                if (e.isAlive()) {
                    for (int i = e.status.size() - 1; i >= 0; i--) {
                        e.status.get(i).startOfTurn();
                    }
                }
            }
        }
    }
}
