package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.uis.control.SkillButtonPanel;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;

public class EnemyTurnStartAction extends AbstractAction {

    boolean isEnemy;

    public EnemyTurnStartAction() {
        super(null, 0.5f);
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            for(AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                if(e.isAlive()) {
                    for (int i = 0; i < 5; i++) {
                        if (e.status[i] != null) e.status[i].startOfTurn();
                    }
                }
            }
        }
    }
}
