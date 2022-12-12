package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class EndEventChoice extends AbstractEvent.EventChoice {

    public EndEventChoice(String t) {
        super(t, new AbstractEvent.EventCondition.True());
    }

    public EndEventChoice() {
        this("계속");
    }

    @Override
    protected void onSelect() {
        AbstractLabyrinth.endRoom();
    }
}
