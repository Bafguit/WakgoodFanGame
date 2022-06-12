package com.fastcat.labyrintale.events;

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
        choices[0] = new AdvisorChoice(data.SELECT[0]);
        img = getImage(0);
        desc = data.DESC[0];
    }
}
