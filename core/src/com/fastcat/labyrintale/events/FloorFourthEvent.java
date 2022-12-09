package com.fastcat.labyrintale.events;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.events.choices.PlayerEventChoice;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;

public class FloorFourthEvent extends AbstractEvent implements GetSelectedPlayer {

    private static final String ID = "FourthFloor";
    private static final int SIZE = 3;

    public FloorFourthEvent() {
        super(ID, SIZE);
        img = getImage(0);
        isEntry = true;
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (AbstractLabyrinth.allAlive()) {
            if (page == 2) a.add(new EndEventChoice());
            else a.add(new NextPageEventChoice(data.SELECT[0], this, 2));
        } else {
            if (page == 0) a.add(new PlayerEventChoice(data.SELECT[1], this, PlayerEventChoice.SelectType.DEAD));
            else if (page == 1) a.add(new NextPageEventChoice(data.SELECT[0], this, 2));
            else a.add(new EndEventChoice());
        }
        return a;
    }

    @Override
    public void onSetPage(int page) {
        if (page == 2) {
            SoundHandler.playSfx("HEAL");
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                if (p.isAlive()) p.heal((int) (p.maxHealth * 0.5f));
            }
        }
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        player.revive();
        setPage(1);
    }
}
