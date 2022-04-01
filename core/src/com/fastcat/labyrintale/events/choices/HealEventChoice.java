package com.fastcat.labyrintale.events.choices;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEvent;

public class HealEventChoice extends AbstractEvent.EventChoice {

    private final Array<AbstractEntity> target;
    private final int amount;

    public HealEventChoice(String t, Array<AbstractEntity> target, int amount) {
        super(t);
        this.target = target;
        this.amount = amount;
    }

    @Override
    public void onSelect() {
        for(AbstractEntity entity : target) {
            entity.heal(amount);
        }
    }
}
