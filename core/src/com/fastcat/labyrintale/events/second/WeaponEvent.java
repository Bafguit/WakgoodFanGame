package com.fastcat.labyrintale.events.second;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class WeaponEvent extends AbstractEvent {

    private static final String ID = "Weapon";
    private static final int SIZE = 3;

    public WeaponEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) {
            a.add(new NextPageEventChoice(data.SELECT[0], this, 1));//TODO 아이템 10개 중 선택으로 변경
            a.add(new NextPageEventChoice(data.SELECT[1], this, 2));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }

    @Override
    public void onSetPage(int page) {
        if (page == 2) {
            SoundHandler.playSfx("HEAL");
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                if (p.isAlive()) p.heal(6);
            }
        }
    }
}
