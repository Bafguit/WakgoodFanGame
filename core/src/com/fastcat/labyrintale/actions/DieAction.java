package com.fastcat.labyrintale.actions;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.sun.java.swing.action.AboutAction;

public class DieAction extends AbstractAction {

    public DieAction(AbstractEntity e) {
        super(e, null, null, 2.0f);
    }

    @Override
    protected void updateAction() {
        if(actor != null) {
            if (duration == baseDuration) {
                AnimationState.TrackEntry e = actor.state.setAnimation(0, "Die", false);
                e.setTimeScale(1.0f);
            }
            if (isDone) {
                actor.isDead = true;
                actor.isDie = false;
            }
        }
    }
}
