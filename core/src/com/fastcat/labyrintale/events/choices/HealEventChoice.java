package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEvent;

public class HealEventChoice extends AbstractEvent.EventChoice {

    private final AbstractEntity[] target;
    private final int amount;

    public HealEventChoice(String t, AbstractEntity[] target, int amount, AbstractEvent.EventCondition condition) {
        super(t, condition);
        this.target = target;
        this.amount = amount;
    }

    @Override
    protected void onSelect() {
    }
}
