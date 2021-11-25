package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;

public class HealAction extends AbstractAction {

    public int heal;

    public HealAction(AbstractEntity actor, AbstractSkill.CardTarget target, int heal, AbstractEffect effect) {
        super(actor, target, effect, 0.5f);
        this.heal = heal;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            Array<AbstractEntity> t = getTargets(target);
            if(t.size > 0) {
                for (int i = 0; i < t.size; i++) {
                    AbstractEntity te = t.get(i);
                    te.heal(heal);
                }
                if (actor != null) {
                    AnimationState.TrackEntry e = actor.state.setAnimation(0, "DoubleHit1", false);
                    actor.state.addAnimation(0, "Standby", true, 0.0F);
                    e.setTimeScale(1.0f);
                }
            }
        }
    }
}
