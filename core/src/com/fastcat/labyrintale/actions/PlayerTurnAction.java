package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.handlers.SettingHandler;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;

public class PlayerTurnAction extends AbstractAction {

    private final AbstractPlayer p;

    public PlayerTurnAction(AbstractPlayer e) {
        super(null, 0.5f);
        p = e;
    }

    @Override
    protected void updateAction() {
        if(isDone) {
            cPanel.battlePanel.setPlayer(p);
        }
    }
}
