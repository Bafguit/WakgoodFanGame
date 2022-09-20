package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.handlers.SettingHandler;

public class NextTurnAction extends AbstractAction {

    public NextTurnAction() {
        super(null, SettingHandler.setting.fastMode ? 0.5f : 1);
    }

    @Override
    protected void updateAction() {
        if(isDone) {
            Labyrintale.battleScreen.nextTurn();
        }
    }
}
