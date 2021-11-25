package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;

public class HealAction extends AbstractAction {

    public int heal;

    public HealAction(AbstractEntity actor, Array<AbstractEntity> target, int heal, AbstractEffect effect) {
        super(actor, target, effect, 0.5f);
        this.heal = heal;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            boolean allDead = true;
            for (int i = 0; i < target.size; i++) {
                AbstractEntity te = target.get(i);
                if (te.isAlive()) {
                    allDead = false;
                    te.heal(heal);
                }
            }
            if(allDead) isDone = true;
            else if(actor != null) {
                AnimationState.TrackEntry e = actor.state.setAnimation(0, "DoubleHit1", false);
                actor.state.addAnimation(0, "Standby", true, 0.0F);
                e.setTimeScale(1.0f);
            }
        }
    }
}
