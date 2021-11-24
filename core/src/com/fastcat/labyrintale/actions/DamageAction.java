package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;

public class DamageAction extends AbstractAction {
    public DamageAction(String id, ActionType type, AbstractEffect effect, float duration) {
        super(id, type, effect, duration);
    }

    @Override
    protected void updateAction() {

    }
}
