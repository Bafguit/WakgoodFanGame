package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.abstracts.AbstractEvent;

public class PlaceholderEventChoice extends AbstractEvent.EventChoice {

    public PlaceholderEventChoice(String t) {
        super(t, new AbstractEvent.EventCondition.False());
    }

    @Override
    protected void onSelect() {}
}
