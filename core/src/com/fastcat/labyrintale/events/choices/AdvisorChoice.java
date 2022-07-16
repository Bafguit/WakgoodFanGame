package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;
import com.fastcat.labyrintale.screens.advisorselect.AdvisorSelectScreen;

public class AdvisorChoice extends AbstractEvent.EventChoice {

    AtEndOfTempScreen gets;

    public AdvisorChoice(String t, AtEndOfTempScreen gets) {
        super(t);
        this.gets = gets;
    }

    @Override
    protected void onSelect() {
        AdvisorSelectScreen s = new AdvisorSelectScreen();
        s.endTemp.add(gets);
        Labyrintale.addTempScreen(s);
    }
}
