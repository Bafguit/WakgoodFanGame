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
        choices[0] = new HealEventChoice(data.SELECT[0], AbstractLabyrinth.players, 3);
        choices[1] = new SkillRewardEventChoice(data.SELECT[1]);
        choices[2] = new PlaceholderEventChoice(data.SELECT[2]);
        img = getImage(0);
        desc = data.DESC[0];
    }

    @Override
    public void onChoose() {
        AbstractLabyrinth.finishRoom();
    }
}
