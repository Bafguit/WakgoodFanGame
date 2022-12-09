package com.fastcat.labyrintale.events.neut;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.events.choices.SkillSlotEventChoice;

public class UpgradeEvent extends AbstractEvent {

    private static final String ID = "Upgrade";
    private static final int SIZE = 3;

    public UpgradeEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) {
            a.add(new SkillSlotEventChoice(
                    data.SELECT[0],
                    new EventCondition() {
                        @Override
                        public boolean condition() {
                            return AbstractLabyrinth.hasSlot();
                        }

                        @Override
                        public String cdText() {
                            return data.SELECT[2];
                        }
                    },
                    this,
                    1));
            a.add(new NextPageEventChoice(data.SELECT[1], this, 2));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }
}
