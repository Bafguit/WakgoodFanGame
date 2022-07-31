package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;

public class BeforeEndRoundAction extends AbstractAction {
    public BeforeEndRoundAction() {
        this(0);
    }

    public BeforeEndRoundAction(float duration) {
        super(null, duration);
    }

    @Override
    protected void updateAction() {
        if(isDone) {
            for (AbstractPlayer p : players) {
                if (p.isAlive()) {
                    for (AbstractStatus s : p.status) {
                        s.endOfRound();
                    }
                }
            }
            for (int j = 3; j >= 0; j--) {
                AbstractEnemy e = AbstractLabyrinth.currentFloor.currentRoom.enemies[j];
                if (e.isAlive()) {
                    for (int i = e.status.size() - 1; i >= 0; i--) {
                        e.status.get(i).endOfRound();
                    }
                }
            }
        }
    }
}
