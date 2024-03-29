package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.dead.DeadScreen;
import com.fastcat.labyrintale.screens.result.ResultScreen;

public class DefeatAction extends AbstractAction {

    public DefeatAction() {
        super(null, 2);
    }

    @Override
    protected void applySetting() {}

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            SoundHandler.fadeOutAll();
            ActionHandler.clear();
            Labyrintale.fadeOutAndChangeScreen(new ResultScreen(DeadScreen.ScreenType.DEAD));
            SaveHandler.finish(false);
        }
    }
}
