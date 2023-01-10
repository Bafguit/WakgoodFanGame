package com.fastcat.labyrintale.events.second;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.ItemRewardEventChoice;
import com.fastcat.labyrintale.events.choices.ItemSelectEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.items.special.Amadeus;

public class ConcertEvent extends AbstractEvent {

    private static final String ID = "Concert";
    private static final int SIZE = 3;

    public ConcertEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) {
            a.add(new ItemRewardEventChoice(data.SELECT[0], new Amadeus(null), new EventCondition.True(), this, 1));
            a.add(new NextPageEventChoice(data.SELECT[1], this, 2));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }
}
