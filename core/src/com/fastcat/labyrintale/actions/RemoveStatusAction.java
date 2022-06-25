package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class RemoveStatusAction extends AbstractAction {

    private final String id;

    public RemoveStatusAction(AbstractStatus status, boolean fast) {
        super(status.owner, fast ? 0.25f : DUR_DEFAULT);
        id = status.id;
    }

    public RemoveStatusAction(String id, AbstractEntity actor, boolean fast) {
        super(actor, fast ? 0.25f : DUR_DEFAULT);
        this.id = id;
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            actor.removeStatus(id);
        }
    }
}
