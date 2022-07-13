package com.fastcat.labyrintale.events;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.*;

public class SurvivorEvent extends AbstractEvent {

    private static final String ID = "Survivor";
    private static final int SIZE = 4;

    public SurvivorEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if(page == 0) {
            a.add(new NextPageEventChoice(data.SELECT[0], this, 1, new EventCondition() {
                @Override
                public boolean condition() {
                    return AbstractLabyrinth.gold >= 50;
                }

                @Override
                public String cdText() {
                    return data.SELECT[1];
                }
            }));
            a.add(new NextPageEventChoice(data.SELECT[2], this, 3));
        } else if(page == 1) {
            a.add(new SkillRewardEventChoice(data.SELECT[3], new EventCondition.True(), this, 2));
            a.add(new SkillSlotEventChoice(data.SELECT[4], new EventCondition() {
                @Override
                public boolean condition() {
                    return AbstractLabyrinth.hasSlot();
                }

                @Override
                public String cdText() {
                    return data.SELECT[5];
                }
            }, this, 2));
        } else {
            a.add(new EndEventChoice(data.SELECT[6]));
        }
        return a;
    }
}
