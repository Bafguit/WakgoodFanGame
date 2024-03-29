package com.fastcat.labyrintale.events.third;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.ItemRewardEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;

public class GotchaEvent extends AbstractEvent {

    private static final String ID = "Gotcha";
    private static final int SIZE = 4;

    public GotchaEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) {
            a.add(new NextPageEventChoice(data.SELECT[0], this, 1, new EventCondition() {
                @Override
                public boolean condition() {
                    return AbstractLabyrinth.advisor.id.equals("jk") || AbstractLabyrinth.advisor.id.equals("mandu");
                }

                @Override
                public String cdText() {
                    return data.SELECT[1];
                }
            }));
            a.add(new NextPageEventChoice(data.SELECT[2], this, 2));
            a.add(new NextPageEventChoice(data.SELECT[3], this, 3));
        } else if (page == 1) {
            a.add(new ItemRewardEventChoice(
                    data.SELECT[4], AbstractItem.ItemRarity.GOLD, new EventCondition.True(), this, 3));
        } else if (page == 2) {
            a.add(new ItemRewardEventChoice(
                    data.SELECT[4], AbstractItem.ItemRarity.BRONZE, new EventCondition.True(), this, 3));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }
}
