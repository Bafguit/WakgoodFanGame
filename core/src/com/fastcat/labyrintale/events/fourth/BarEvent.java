package com.fastcat.labyrintale.events.fourth;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.ItemRewardEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.items.special.Sanpellegrino;

public class BarEvent extends AbstractEvent {

    private static final String ID = "Bar";
    private static final int SIZE = 5;

    public BarEvent() {
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
                    return AbstractLabyrinth.advisor.id.equals("rusuk");
                }

                @Override
                public String cdText() {
                    return data.SELECT[1];
                }
            }));
            a.add(new ItemRewardEventChoice(
                    data.SELECT[2], new Sanpellegrino(null), new EventCondition.True(), this, 2));
            a.add(new NextPageEventChoice(data.SELECT[3], this, 3));
            a.add(new NextPageEventChoice(data.SELECT[4], this, 4));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }

    @Override
    public void onSetPage(int page) {
        if (page == 1) {
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                p.modifyMaxHealth(10);
            }
        } else if (page == 3) {
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                p.heal(5);
            }
        }
    }
}
