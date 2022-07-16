package com.fastcat.labyrintale.events;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.events.choices.AdvisorChoice;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;

public class AdvisorEvent extends AbstractEvent implements AtEndOfTempScreen {

    private static final String ID = "Advisor";
    private static final int SIZE = 2;

    public AdvisorEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if(page == 0) {
            a.add(new AdvisorChoice(data.SELECT[0], this));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }

    @Override
    public void atEndOfTempScreen() {
        setPage(1);
    }
}
