package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.effects.DieEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;

public class DuksuAction extends AbstractAction {
    public DuksuAction(AbstractEntity e) {
        super(e, 1f);
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            ActionHandler.top(new MoveAction(actor, actor, 3));
            EffectHandler.add(new DieEffect(actor));
        }
    }
}
