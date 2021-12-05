package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;

public class WaitAction extends AbstractAction {
    public WaitAction() {
        this(0.5f);
    }

    public WaitAction(float duration) {
        super(null, duration);
    }

    @Override
    protected void updateAction() {

    }
}
