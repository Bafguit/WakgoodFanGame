package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.abstracts.AbstractEvent;

public class NextPageEventChoice extends AbstractEvent.EventChoice {

    public AbstractEvent event;
    public int page;

    public NextPageEventChoice(String t, AbstractEvent event, int page) {
        this(t, event, page, new AbstractEvent.EventCondition.True());
    }

    public NextPageEventChoice(String t, AbstractEvent event, int page, AbstractEvent.EventCondition condition) {
        super(t, condition);
        this.event = event;
        this.page = page;
    }

    @Override
    protected void onSelect() {
        event.setPage(page);
    }
}
