package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.*;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;

public class AttackAction extends AbstractAction {

    public int damage;

    public AttackAction(AbstractEntity actor, AbstractSkill.CardTarget target, int damage, AbstractEffect effect) {
        super(actor, target, effect, 0.5f);
        this.damage = damage;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            Array<AbstractEntity> t = getTargets(target);
            if(t.size > 0) {
                for (int i = 0; i < t.size; i++) {
                    AbstractEntity te = t.get(i);
                    te.damage(actor, damage);
                }
                if(actor != null) {
                    AnimationState.TrackEntry e = actor.state.setAnimation(0, "RoadHitPerfect1", false);
                    actor.state.addAnimation(0, "Standby", true, 0.0F);
                    e.setTimeScale(1.0f);
                }
            } else isDone = true;
        }
    }
}