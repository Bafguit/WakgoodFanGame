package com.fastcat.labyrintale.events;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.AdvisorChoice;
import com.fastcat.labyrintale.events.choices.HealEventChoice;
import com.fastcat.labyrintale.events.choices.PlaceholderEventChoice;
import com.fastcat.labyrintale.events.choices.SkillRewardEventChoice;

public class StartEvent extends AbstractEvent {

    private static final String ID = "StartEvent";
    private static final int SIZE = 1;

    public StartEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public String getDescription(int page) {
        return data.DESC[0];
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        a.add(new AdvisorChoice(data.SELECT[0]));
        return a;
    }
}
