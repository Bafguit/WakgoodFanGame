package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;

public class GainEnergyAction extends AbstractAction {

    private final int amount;

    public GainEnergyAction(int amount) {
        super(null, 0.1f);
        this.amount = amount;
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            AbstractLabyrinth.energy += amount;
        }
    }
}
