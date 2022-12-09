package com.fastcat.labyrintale.events.second;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.ItemRewardEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;

public class StrangerEvent extends AbstractEvent {

    private static final String ID = "Stranger";
    private static final int SIZE = 4;

    public StrangerEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) {
            a.add(new ItemRewardEventChoice(data.SELECT[0], new EventCondition.True(), this, 1));
            a.add(new ItemRewardEventChoice(
                    data.SELECT[1],
                    new EventCondition() {
                        @Override
                        public boolean condition() {
                            return AbstractLabyrinth.gold >= 80;
                        }

                        @Override
                        public String cdText() {
                            return data.SELECT[2];
                        }
                    },
                    this,
                    2));
            a.add(new NextPageEventChoice(data.SELECT[3], this, 3));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }

    @Override
    public void onSetPage(int page) {
        if (page == 1) {
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                p.stat.debuRes -= 5;
                p.stat.moveRes -= 5;
                p.stat.neutRes -= 5;
            }
        } else if (page == 2) {
            AbstractLabyrinth.modifyGold(-80);
        }
    }
}
