package com.fastcat.labyrintale.events.choices;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEvent;

public class HealEventChoice extends AbstractEvent.EventChoice {

    private final AbstractEntity[] target;
    private final int amount;

    public HealEventChoice(AbstractEvent e, String t, AbstractEntity[] target, int amount) {
        super(e, t);
        this.target = target;
        this.amount = amount;
    }

    @Override
    protected void onSelect() {
        for(AbstractEntity entity : target) {
            entity.heal(amount);
        }
    }
}
