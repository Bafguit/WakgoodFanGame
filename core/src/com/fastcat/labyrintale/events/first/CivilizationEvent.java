package com.fastcat.labyrintale.events.first;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.*;

public class CivilizationEvent extends AbstractEvent {

    private static final String ID = "Civilization";
    private static final int SIZE = 5;

    public CivilizationEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) {
            a.add(new NextPageEventChoice(data.SELECT[0], this, 1, new EventCondition.True()));
            a.add(new NextPageEventChoice(data.SELECT[1], this, 2, new EventCondition.True()));
            a.add(new NextPageEventChoice(data.SELECT[2], this, 3, new EventCondition.True()));
        } else if (page == 1) {
            a.add(new ItemRewardEventChoice(data.SELECT[3], new EventCondition.True(), this, 4));
        } else if (page == 2) {
            a.add(new SkillRewardEventChoice(data.SELECT[3], new EventCondition.True(), this, 4));
        } else if (page == 3) {
            a.add(new SkillSlotEventChoice(data.SELECT[3], new EventCondition() {
                @Override
                public boolean condition() {
                    return AbstractLabyrinth.hasSlot();
                }

                @Override
                public String cdText() {
                    return data.SELECT[4];
                }
            }, this, 4));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }
}
