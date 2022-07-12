package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class PurifyAction extends AbstractAction {

    public PurifyAction(Array<AbstractEntity> target) {
        super(null, target, 0.5f);
    }

    public PurifyAction(AbstractEntity target) {
        super(null, target, 0.5f);
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            for(AbstractEntity e : target) {
                if(e.isAlive()) {
                    for (AbstractStatus s : actor.status) {
                        if (s != null && s.type == AbstractStatus.StatusType.DEBUFF) {
                            ActionHandler.top(new RemoveStatusAction(s, true));
                        }
                    }
                }
            }
        }
    }
}
