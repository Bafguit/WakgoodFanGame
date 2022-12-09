package com.fastcat.labyrintale.actions;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;

import com.fastcat.labyrintale.abstracts.*;

public class EndTurnAction extends AbstractAction {

    private final boolean isPlayer;

    public EndTurnAction(boolean isPlayer) {
        super(null, 0);
        this.isPlayer = isPlayer;
    }

    @Override
    protected void updateAction() {
        if (isDone) {
            if (isPlayer) {
                for (AbstractPlayer p : players) {
                    if (p.isAlive()) {
                        for (AbstractStatus s : p.status) {
                            if (s != null) s.endOfTurn();
                        }
                        p.passive.endOfTurn();
                        for (AbstractItem s : p.item) {
                            if (s != null) s.endOfTurn();
                        }
                    }
                }
            } else {
                for (int j = 3; j >= 0; j--) {
                    AbstractEnemy e = AbstractLabyrinth.currentFloor.currentRoom.enemies[j];
                    if (e.isAlive()) {
                        for (int i = e.status.size() - 1; i >= 0; i--) {
                            e.status.get(i).endOfTurn();
                        }
                    }
                }
            }
        }
    }
}
