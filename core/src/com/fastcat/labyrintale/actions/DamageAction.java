package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;

public class DamageAction extends AbstractAction {
    public DamageAction(AbstractEntity actor, Array<AbstractEntity> target, AbstractEffect effect, float duration) {
        super(actor, target, effect, duration);
    }

    @Override
    protected void updateAction() {

    }
}
