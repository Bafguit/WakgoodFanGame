package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class DamageAction extends AbstractAction {
    public DamageAction(AbstractEntity actor, AbstractSkill.CardTarget target, AbstractEffect effect, float duration) {
        super(actor, target, duration);
    }

    @Override
    protected void updateAction() {

    }
}
