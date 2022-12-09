package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;

public class SetSkinAction extends AbstractAction {

    private final String key;

    public SetSkinAction(AbstractEntity actor, String key, float duration) {
        super(actor, duration);
        this.key = key;
    }

    public SetSkinAction(AbstractEntity actor, String key) {
        super(actor, 0);
        this.key = key;
    }

    @Override
    protected void applySetting() {}

    @Override
    protected void updateAction() {
        if (isDone) {
            actor.skeleton.setSkin(key);
        }
    }
}
