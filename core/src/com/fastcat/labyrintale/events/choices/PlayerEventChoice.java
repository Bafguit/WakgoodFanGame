package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;

public class PlayerEventChoice extends AbstractEvent.EventChoice {

    public GetSelectedPlayer event;

    public PlayerEventChoice(String t, GetSelectedPlayer callback) {
        super(t);
        this.event = callback;
    }

    @Override
    protected void onSelect() {
        Labyrintale.addTempScreen(new PlayerSelectScreen(event));
    }
}
