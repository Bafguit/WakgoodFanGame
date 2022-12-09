package com.fastcat.labyrintale.events.first;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.ItemRewardEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.items.special.LoyalVirus;

public class LoyalistEvent extends AbstractEvent {

    private static final String ID = "Loyalist";
    private static final int SIZE = 4;

    public LoyalistEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) {
            a.add(new NextPageEventChoice(data.SELECT[0], this, 1));
            a.add(new NextPageEventChoice(data.SELECT[1], this, 2));
        } else if (page == 1) {
            a.add(new ItemRewardEventChoice(data.SELECT[2], new LoyalVirus(null), new EventCondition.True(), this, 3));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }

    @Override
    public void onSetPage(int page) {
        if (page == 3) {
            AbstractLabyrinth.modifyGold(150);
        }
    }
}
