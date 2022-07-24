package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;

public class BetEventChoice extends AbstractEvent.EventChoice {

    public AbstractEvent event;
    public int rng, winPage, losePage;

    public BetEventChoice(String t, AbstractEvent event, int rng, int winPage, int losePage) {
        this(t, event, rng, winPage, losePage, new AbstractEvent.EventCondition.True());
    }

    public BetEventChoice(String t, AbstractEvent event, int rng, int winPage, int losePage, AbstractEvent.EventCondition condition) {
        super(t, condition);
        this.event = event;
        this.rng = rng;
        this.winPage = winPage;
        this.losePage = losePage;
    }

    @Override
    protected void onSelect() {
        int r = AbstractLabyrinth.publicRandom.random(100);
        if (r < rng) event.setPage(winPage);
        else event.setPage(losePage);
    }
}
