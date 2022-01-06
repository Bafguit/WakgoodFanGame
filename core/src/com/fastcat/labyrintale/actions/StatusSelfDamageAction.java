package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.CardTarget.NONE;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;

public class StatusSelfDamageAction extends AbstractAction {

    public AbstractStatus status;

    public StatusSelfDamageAction(AbstractStatus s) {
        super(s.owner, NONE, 1.0f);
        status = s;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            status.flash(actor);
            actor.takeDamage(actor, status.amount);
        }
    }
}
