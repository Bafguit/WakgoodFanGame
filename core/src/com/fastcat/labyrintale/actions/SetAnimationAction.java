package com.fastcat.labyrintale.actions;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;

public class SetAnimationAction extends AbstractAction {

    private final String key;

    public SetAnimationAction(AbstractEntity actor, String key) {
        super(actor, 0);
        this.key = key;
    }

    @Override
    protected void applySetting() {

    }

    @Override
    protected void updateAction() {
        AnimationState.TrackEntry e = actor.state.setAnimation(0, key, false);
        actor.state.addAnimation(0, "idle", true, 0.0F);
        e.setTimeScale(1.0f);
    }
}
