package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;

public class EnemyTurnStartAction extends AbstractAction {

    boolean isEnemy;

    public EnemyTurnStartAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            for(int j = 3; j >= 0; j--) {
                AbstractEnemy e = AbstractLabyrinth.currentFloor.currentRoom.enemies[j];
                if(e.isAlive()) {
                    for (int i = 3; i >= 0; i--) {
                        if (e.status[i] != null) e.status[i].startOfTurn();
                    }
                }
            }
        }
    }
}
