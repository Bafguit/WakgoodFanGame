package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.screens.advisorselect.AdvisorSelectScreen;

public class AdvisorChoice extends AbstractEvent.EventChoice {

    public AdvisorChoice(String t) {
        super(t);
    }

    @Override
    protected void onSelect() {
        Labyrintale.addTempScreen(new AdvisorSelectScreen());
    }
}
