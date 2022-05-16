package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class DieEffect extends AbstractEffect {

    private final AbstractEntity actor;

    public DieEffect(AbstractEntity e) {
        super(0, 0, 2.0f);
        actor = e;
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        if(actor != null) {
            if (duration == baseDuration) {
                AnimationState.TrackEntry e = actor.state.setAnimation(0, "die", false);
                e.setTimeScale(1.0f);
            } else {
                actor.skeleton.getColor().a -= Gdx.graphics.getDeltaTime() / 2;
            }
            if (isDone) {
                actor.isDead = true;
                actor.isDie = false;
                actor.status = new AbstractStatus[4];
            }
        }
    }
}
