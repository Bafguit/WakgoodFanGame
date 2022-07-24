package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.screens.dead.DeadScreen;

public class EndLabyrinthAction extends AbstractAction {

    public EndLabyrinthAction() {
        super(null, 2);
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            ActionHandler.clear();
            Labyrintale.fadeOutAndChangeScreen(new DeadScreen(DeadScreen.ScreenType.DEAD), 2.0f);
            SaveHandler.finish(false);
        }
    }
}
