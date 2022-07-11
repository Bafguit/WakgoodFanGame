package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class PurifyAction extends AbstractAction {

    public PurifyAction(AbstractEntity target) {
        super(target, 0.5f);
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration && actor != null) {
            for (AbstractStatus s : actor.status) {
                if (s != null && s.type == AbstractStatus.StatusType.DEBUFF) {
                    ActionHandler.top(new RemoveStatusAction(s, true));
                }
            }
        }
    }
}
