package com.fastcat.labyrintale.events.first;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractChoice;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.events.choices.*;
import com.fastcat.labyrintale.handlers.GroupHandler;

public class DoorEvent extends AbstractEvent {

    private static final String ID = "Door";
    private static final int SIZE = 4;

    public DoorEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public void endBattle() {
        if(page == 1) setPage(2);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if(page == 0) {
            a.add(new NextPageEventChoice(data.SELECT[0], this, 1));
            a.add(new NextPageEventChoice(data.SELECT[1], this, 3));
        } else if(page == 1) {
            AbstractChoice[] c = AbstractLabyrinth.currentFloor.currentWay.choices;
            boolean b = false;
            for (AbstractChoice ch : c) {
                if (ch.type == AbstractChoice.ChoiceType.BATTLE) {
                    a.add(new BattleEventChoice(data.SELECT[2], ch.room));
                    b = true;
                    break;
                }
            }
            if(!b) a.add(new BattleEventChoice(data.SELECT[2], GroupHandler.RoomGroup.getNextNormal()));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }
}
