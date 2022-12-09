package com.fastcat.labyrintale.events.third;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.events.choices.EndEventChoice;
import com.fastcat.labyrintale.events.choices.NextPageEventChoice;
import com.fastcat.labyrintale.events.choices.PlayerEventChoice;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.items.special.DevilIdol;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;

public class CorruptedIdolEvent extends AbstractEvent implements GetSelectedPlayer, AtEndOfTempScreen {

    private static final String ID = "CorruptedIdol";
    private static final int SIZE = 3;

    public CorruptedIdolEvent() {
        super(ID, SIZE);
        img = getImage(0);
    }

    @Override
    public Array<EventChoice> getChoices(int page) {
        Array<EventChoice> a = new Array<>();
        if (page == 0) {
            a.add(new NextPageEventChoice(data.SELECT[0], this, 1));
            a.add(new PlayerEventChoice(data.SELECT[1], this, PlayerEventChoice.SelectType.LIVE));
        } else {
            a.add(new EndEventChoice());
        }
        return a;
    }

    @Override
    public void onSetPage(int page) {
        if (page == 1) {
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                p.stat.moveRes -= 10;
                p.stat.neutRes -= 10;
                p.stat.debuRes -= 10;
            }
        }
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        player.die(null);
        ShopTakeScreen s;
        s = new ShopTakeScreen(new DevilIdol(null));
        s.endTemp.add(this);
        Labyrintale.addTempScreen(s);
    }

    @Override
    public void atEndOfTempScreen() {
        setPage(2);
    }
}
