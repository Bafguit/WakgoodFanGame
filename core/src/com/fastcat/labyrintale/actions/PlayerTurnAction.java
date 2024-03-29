package com.fastcat.labyrintale.actions;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class PlayerTurnAction extends AbstractAction {

    private final AbstractPlayer p;

    public PlayerTurnAction(AbstractPlayer e) {
        super(null, 0.5f);
        p = e;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            cPanel.battlePanel.setEntity(p);
        }
    }
}
