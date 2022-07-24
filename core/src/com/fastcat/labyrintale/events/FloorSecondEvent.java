package com.fastcat.labyrintale.events;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.events.choices.AdvisorChoice;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;

public class FloorSecondEvent extends AbstractEvent implements AtEndOfTempScreen {

    private static final String ID = "SecondFloor";
    private static final int SIZE = 2;

    public FloorSecondEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        //TODO 1층 보스 보상 추가
        if (page == 0) a.add(new AdvisorChoice(data.SELECT[0], this));
        else a.add(new EndEventChoice());
        return a;
    }

    @Override
    public void atEndOfTempScreen() {
        setPage(1);
    }
}
