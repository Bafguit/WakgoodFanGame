package com.fastcat.labyrintale.events;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.HealEventChoice;
import com.fastcat.labyrintale.events.choices.PlaceholderEventChoice;
import com.fastcat.labyrintale.events.choices.SkillRewardEventChoice;
import com.fastcat.labyrintale.handlers.FileHandler;

public class TestEvent extends AbstractEvent {

    private static final String ID = "TestEvent";
    private static final int SIZE = 3;

    public TestEvent() {
        super(ID, SIZE);
        img = getImage(0);
        desc = data.DESC[0];
    }

    @Override
    public void onChoose() {
        AbstractLabyrinth.finishRoom();
    }

    @Override
    public String getDescription(int page) {
        return data.DESC[0];
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        a.add(new HealEventChoice(data.SELECT[0], AbstractLabyrinth.players, 3, new EventCondition.True()));
        a.add(new SkillRewardEventChoice(data.SELECT[1], new EventCondition.True()));
        a.add(new PlaceholderEventChoice(data.SELECT[2]));
        return a;
    }
}
