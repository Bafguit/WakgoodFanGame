package com.fastcat.labyrintale.events;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.StartAdvisorChoice;

public class StartEvent extends AbstractEvent {

    private static final String ID = "StartEvent";
    private static final int SIZE = 2;

    public StartEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) a.add(new StartAdvisorChoice(data.SELECT[0], this, 1));
        else a.add(new EndEventChoice());
        return a;
    }
}
