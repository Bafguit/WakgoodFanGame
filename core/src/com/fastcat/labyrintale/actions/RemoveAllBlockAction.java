package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class RemoveAllBlockAction extends AbstractAction {

    boolean isEnemy;

    public RemoveAllBlockAction(boolean isEnemy) {
        super(null, 0.5f);
        this.isEnemy = isEnemy;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (isEnemy) {
                for (AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                    if (!e.hasStatus("Maintain")) e.block = 0;
                }
            } else {
                for (AbstractPlayer p : AbstractLabyrinth.players) {
                    if (p.playerClass != AbstractPlayer.PlayerClass.WAK) {
                        p.block = p.block / 2;
                    } else if (!p.hasStatus("Maintain")) p.block = 0;
                }
            }
        }
    }
}
