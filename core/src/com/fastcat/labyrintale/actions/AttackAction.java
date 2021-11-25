package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class AttackAction extends AbstractAction {

    public int damage;

    public AttackAction(AbstractEntity actor, Array<AbstractEntity> target, int damage, AbstractEffect effect) {
        super(actor, target, effect, 0.5f);
        this.damage = damage;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            boolean allDead = true;
            for (int i = 0; i < target.size; i++) {
                AbstractEntity te = target.get(i);
                if (te.isAlive()) {
                    allDead = false;
                    te.damage(actor, damage);
                }
            }
            if(allDead) isDone = true;
            else if(actor != null) {
                AnimationState.TrackEntry e = actor.state.setAnimation(0, "RoadHitPerfect1", false);
                actor.state.addAnimation(0, "Standby", true, 0.0F);
                e.setTimeScale(1.0f);
            }
        }
    }
}
